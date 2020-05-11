package net.cn.yasir.framework.mq.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;

/**
 * @author yasir
 * @version 1.0.0
 * @desc 确认回调处理器
 * @date 2020-05-11
 */
public interface ConfirmCallbackHandler {

    /**
     * 获取类型
     * @return
     */
    String getType();

    /**
     * 执行逻辑
     * @param correlationData
     * @param ack
     * @param cause
     */
    void doIt(CorrelationData correlationData, boolean ack, String cause);
}
