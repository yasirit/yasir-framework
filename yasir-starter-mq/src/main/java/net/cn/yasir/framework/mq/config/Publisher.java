package net.cn.yasir.framework.mq.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;

/**
 * 发布器
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/30
 */
public interface Publisher<M> {
    /**
     * 发布
     * @param m
     */
    void publish(M m);

    /**
     * 发布确认
     * @param message
     * @param correlationData
     */
    void publish(M message, CorrelationData correlationData);

}
