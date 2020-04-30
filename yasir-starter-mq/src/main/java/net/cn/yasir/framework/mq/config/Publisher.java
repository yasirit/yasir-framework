package net.cn.yasir.framework.mq.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 发布器
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/30
 */
public interface Publisher<T> {
    /**
     * 发布
     * @param t
     */
    void publish(T t);

}
