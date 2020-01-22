package net.cn.yasir.framework.restful.handler;

import net.cn.yasir.framework.base.util.RequestParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Http请求参数处理器
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
public class HttpParamsFilterHandler implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParamsFilterHandler.class);

    @Override
    public void init(FilterConfig filterConfig) {
        //Do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapper = new HttpServletRequestWrapperHandler((HttpServletRequest) request);
        String method = ((HttpServletRequest) requestWrapper).getMethod();
        String reqUri = ((HttpServletRequest) requestWrapper).getRequestURI();
        if(HttpMethod.POST.name().equals(method.toUpperCase())) {
            LOGGER.info("Acceptance of request, method={}, path={}, param={}", method, reqUri, RequestParamsUtil.postParams((HttpServletRequest) requestWrapper));
        } else {
            LOGGER.info("Acceptance of request, method={}, path={}, param={}", method, reqUri, RequestParamsUtil.getParams((HttpServletRequest) requestWrapper));
        }
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {
        //Do nothing
    }
}
