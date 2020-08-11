package net.cn.yasir.framework.restful.properities;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * JWT验证参数配置
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/8/11
 */
@Data
@ConfigurationProperties("yasir.jwt")
@EnableConfigurationProperties(JwtProperties.class)
public class JwtProperties {
    /**
     * 是否启用
     */
    private boolean enable = false;
    /**
     * jwt秘钥
     */
    private String jwtSecret = "VcZjX8dTYI4pAIxH";
    /**
     * jwt过期时间
     */
    private long jwtExpire = 60L;
    /**
     * jwt过滤路径集合
     */
    private String[] jwtPaths = {"/api/**"};

}
