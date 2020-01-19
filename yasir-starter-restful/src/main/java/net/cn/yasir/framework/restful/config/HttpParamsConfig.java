package net.cn.yasir.framework.restful.config;

import net.cn.yasir.framework.restful.handler.HttpParamsFilterHandler;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Http请求参数配置
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
@Configuration
public class HttpParamsConfig {
    @Bean
    public FilterRegistrationBean registerFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpParamsFilterHandler());
        registration.addUrlPatterns("/api/*");
        registration.setName("HttpParamsFilter");
        registration.setOrder(1);
        return registration;
    }
}
