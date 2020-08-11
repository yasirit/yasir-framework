package net.cn.yasir.framework.restful.properities;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

/**
 * 跨域参数配置
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/5/11
 */
@EnableConfigurationProperties({CorsProperties.class})
@ConfigurationProperties(prefix = "yasir.cros")
public class CorsProperties {

    private String path = "/**";

    private String allowedOrigin = "*";

    private String allowedHeader = "*";

    private String allowedMethod = "*";

    /**
     * 跨域配置
     * @return
     */
    public CorsConfiguration buildCorsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1允许任何域名使用
        corsConfiguration.addAllowedOrigin(allowedOrigin);
        // 2允许任何头
        corsConfiguration.addAllowedHeader(allowedHeader);
        // 3允许任何方法（post、get等）
        corsConfiguration.addAllowedMethod(allowedMethod);
        return corsConfiguration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAllowedOrigin() {
        return allowedOrigin;
    }

    public void setAllowedOrigin(String allowedOrigin) {
        this.allowedOrigin = allowedOrigin;
    }

    public String getAllowedHeader() {
        return allowedHeader;
    }

    public void setAllowedHeader(String allowedHeader) {
        this.allowedHeader = allowedHeader;
    }

    public String getAllowedMethod() {
        return allowedMethod;
    }

    public void setAllowedMethod(String allowedMethod) {
        this.allowedMethod = allowedMethod;
    }
}
