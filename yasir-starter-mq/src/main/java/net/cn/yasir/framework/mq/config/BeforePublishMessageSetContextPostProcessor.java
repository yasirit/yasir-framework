package net.cn.yasir.framework.mq.config;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.core.Message;

/**
 * 消息发送前置处理器（设置context）
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/5/12
 */
public class BeforePublishMessageSetContextPostProcessor implements MqMessagePostProcessor {

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        setContext(message);
        return message;
    }

    @Override
    public Message postProcessMessage(Message message, Correlation correlation) {
        setContext(message);
        return message;
    }

    private void setContext(Message message) {
        System.out.println("设置context");
//        message.getMessageProperties().setHeader();
    }
}
