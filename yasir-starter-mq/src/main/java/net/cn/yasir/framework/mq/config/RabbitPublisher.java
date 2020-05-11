package net.cn.yasir.framework.mq.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.concurrent.TimeUnit;

/**
 * TOPIC模式的发布器
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/30
 */
public interface RabbitPublisher<T, M extends RabbitMessage<T>> extends Publisher<M> {

    /**
     * 发布确认
     * @param message
     * @param correlationData
     */
    void publish(M message, CorrelationData correlationData);

}
