package net.cn.yasir.framework.restful.config;

import net.cn.yasir.framework.dto.enums.ProfilesEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${spring.profiles:dev}")
    private String profiles;
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
    public Docket createRestApi() {
        Docket docket;
        if(ProfilesEnum.PROD.getValue().equals(profiles)) {
            docket = (new Docket(DocumentationType.SWAGGER_2))
                    .select()
                    .paths(PathSelectors.none())
                    .build();
        } else {
            docket = new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .paths(PathSelectors.any())
                    .apis(RequestHandlerSelectors.basePackage(this.apis))
                    .build();
        }
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
