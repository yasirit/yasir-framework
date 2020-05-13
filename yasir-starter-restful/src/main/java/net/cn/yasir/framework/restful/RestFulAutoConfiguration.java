package net.cn.yasir.framework.restful;

import net.cn.yasir.framework.restful.config.GlobalExceptionConfig;
import net.cn.yasir.framework.restful.config.HttpParamsAspectConfig;
import net.cn.yasir.framework.restful.filter.HttpSetContextFilter;
import net.cn.yasir.framework.restful.properities.CorsPeoperities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;
import java.util.Objects;

import static net.cn.yasir.framework.restful.handler.FastJsonHandler.formatHttpMessageConverter;

/**
 * 自动配置
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/5/11
 */
//@Configuration
public class RestFulAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "yasir.cros")
    public CorsPeoperities corsPeoperities() {
        return new CorsPeoperities();
    }

    @Bean
    public CorsFilter registerCorsFilter(CorsPeoperities corsPeoperities) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(corsPeoperities.getPath(), corsPeoperities.buildCorsConfig());
        return new CorsFilter(source);
    }

    @Bean
    public HttpMessageConverters httpMessageConverters() {
        return new HttpMessageConverters(formatHttpMessageConverter());
    }

    @Bean
    public HttpParamsAspectConfig httpParamsAspectConfig() {
        return new HttpParamsAspectConfig();
    }

    @Bean
    public GlobalExceptionConfig globalExceptionConfig() {
        return new GlobalExceptionConfig();
    }

    @Bean
    public HttpSetContextFilter httpSetContextFilter() {
        return new HttpSetContextFilter();
    }

    @Bean
    public RestTemplate restTemplate(List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors) {
        RestTemplate restTemplate = new RestTemplate();
        if(Objects.nonNull(clientHttpRequestInterceptors) && clientHttpRequestInterceptors.size() > 0) {
            restTemplate.setInterceptors(clientHttpRequestInterceptors);
        }
        return restTemplate;
    }

}
