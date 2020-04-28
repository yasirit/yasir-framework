package net.cn.yasir.framework.swagger.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/28
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Value("${swagger.title:微服务接口}")
    private String title;
    @Value("${swagger.description:微服务接口文档}")
    private String description;
    @Value("${swagger.termsOfServiceUrl:www.home.yasirit.top}")
    private String termsOfServiceUrl;
    @Value("${swagger.version:1.0}")
    private String version;
    @Value("${swagger.apis:net.cn.yasir}")
    private String apis;

    @Bean
    @Profile({"dev", "sit", "uat"})
    public Docket enableRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage(this.apis))
                .build();
        return docket;
    }

    @Bean
    @Profile({"prod"})
    public Docket disableRestApi() {
        Docket docket = (new Docket(DocumentationType.SWAGGER_2))
                .select()
                .paths(PathSelectors.none())
                .build();
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(this.title)
                .description(this.description)
                .termsOfServiceUrl(this.termsOfServiceUrl)
                .version(this.version)
                .build();
    }

}
