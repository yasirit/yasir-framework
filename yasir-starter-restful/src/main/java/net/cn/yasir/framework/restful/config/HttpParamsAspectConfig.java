package net.cn.yasir.framework.restful.config;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @desc: HTTP请求日志切面
 * @author: yasir
 * @date: 2020/3/8 13:15
 */
@Aspect
@Component
public class HttpParamsAspectConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(HttpParamsAspectConfig.class);

    private static List<String> exUris = Lists.newArrayList("/doc.html", "/api-docs", "/swagger", "/error");

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void webLog() {}

    @Before(value = "webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String httpUri = request.getRequestURI();
        if(checkExUri(httpUri)) {
            return;
        }
        String httpIP = request.getRemoteAddr();
        String httpUUID = UUID.randomUUID().toString();
        long httpStart = System.currentTimeMillis();
        String httpMethod = request.getMethod();
        Object[] objs = joinPoint.getArgs();
        String httpParams = Objects.isNull(objs) ? "" : JSONObject.toJSONString(objs);
        LOGGER.info("Acceptance of request, uuid={}, ip={}, method={}, path={}, params={}", httpUUID, httpIP, httpMethod, httpUri, httpParams);
        HttpServletResponse response = attributes.getResponse();
        response.setHeader("http_uuid", httpUUID);
        response.setHeader("http_start", String.valueOf(httpStart));
    }

    @AfterReturning(value = "webLog()", returning = "ret")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) throws Throwable {
        // 处理完请求，返回内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        HttpServletRequest request = attributes.getRequest();
        String httpUri = request.getRequestURI();
        if(checkExUri(httpUri)) {
            return;
        }
        String httpUUID = response.getHeader("http_uuid");
        long httpStart = Long.valueOf(response.getHeader("http_start"));
        long httpEnd = System.currentTimeMillis();
        long time = httpEnd - httpStart;
        response.setHeader("http_end", String.valueOf(httpEnd));
        LOGGER.info("Acceptance of response, uuid={}, result={}, time={}ms", httpUUID, JSONObject.toJSONString(ret), time);
    }

    /**
     * 校验uri
     * @param uri
     * @return
     */
    private boolean checkExUri(String uri) {
        for(int i = 0; i < exUris.size(); i++) {
            if(uri.contains(exUris.get(i))) {
                return true;
            }
        }
        return false;
    }

}
