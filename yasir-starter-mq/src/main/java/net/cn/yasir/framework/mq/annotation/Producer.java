package net.cn.yasir.framework.mq.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 生产者
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface Producer {

    /**
     * 交换机
     * @return
     */
    String exchange();
}
