package net.cn.yasir.framework.restful.config;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import java.util.List;

/**
 * 若是继承WebMvcConfigurationSupport,需指定静态资源
 * 项目中不继承WebMvcConfigurationSupport，继承WebConfig
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/28
 */
public class WebConfig extends WebMvcConfigurationSupport {

    /**
     * 自定义消息转换器
     * @param converters 消息转换器列表
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //调用父类的配置
        super.configureMessageConverters(converters);
        //将FastJson添加到视图消息转换器列表内
        converters.add(FastJsonConfig.formatHttpMessageConverter());
    }

    /**
     * 指定静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("doc.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}
