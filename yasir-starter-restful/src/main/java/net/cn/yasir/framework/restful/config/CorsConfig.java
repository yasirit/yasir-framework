package net.cn.yasir.framework.restful.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
public class CorsConfig {

    @Value("${cors.path:/**}")
    private String path;
    @Value("${cors.allowedOrigin:*}")
    private String allowedOrigin;
    @Value("${cors.allowedHeader:*}")
    private String allowedHeader;
    @Value("${cors.allowedMethod:*}")
    private String allowedMethod;

    @Bean
    public CorsFilter registerCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(path, buildCorsConfig());
        return new CorsFilter(source);
    }

    /**
     * 跨域配置
     * @return
     */
    private CorsConfiguration buildCorsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1允许任何域名使用
        corsConfiguration.addAllowedOrigin(allowedOrigin);
        // 2允许任何头
        corsConfiguration.addAllowedHeader(allowedHeader);
        // 3允许任何方法（post、get等）
        corsConfiguration.addAllowedMethod(allowedMethod);
        return corsConfiguration;
    }

}
