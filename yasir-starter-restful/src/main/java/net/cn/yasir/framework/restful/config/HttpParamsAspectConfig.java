package net.cn.yasir.framework.restful.config;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String httpUUID = UUID.randomUUID().toString();
        long httpStart = System.currentTimeMillis();
        String httpIP = request.getRemoteAddr();
        String httpMethod = request.getMethod();
        String httpUri = request.getRequestURI();
        Object[] objs = joinPoint.getArgs();
        String httpParams = Objects.isNull(objs) ? "" : JSONObject.toJSONString(objs);

//        ServletRequest requestWrapper = new HttpServletRequestWrapperHandler(request);
//        if(HttpMethod.POST.name().equals(httpMethod.toUpperCase())) {
//            httpParams = RequestParamsUtil.postParams((HttpServletRequest) requestWrapper);
//        } else {
//            httpParams = RequestParamsUtil.getParams((HttpServletRequest) requestWrapper);
//        }
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
        String httpUUID = response.getHeader("http_uuid");
        long httpStart = Long.valueOf(response.getHeader("http_start"));
        long httpEnd = System.currentTimeMillis();
        long time = httpEnd - httpStart;
        response.setHeader("http_end", String.valueOf(httpEnd));
        LOGGER.info("Acceptance of response, uuid={}, result={}, time={}ms", httpUUID, JSONObject.toJSONString(ret), time);
    }


}
