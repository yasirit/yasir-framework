package net.cn.yasir.framework.swagger.annotation;

import net.cn.yasir.framework.swagger.config.SwaggerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用swagger配置
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({SwaggerConfig.class})
public @interface EnableApiDoc {
}
