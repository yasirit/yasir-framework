package net.cn.yasir.framework.mq.config;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.core.Message;

/**
 * 消息接收后置处理器（获取context）
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/5/12
 */
public class AfterReceiveMessageGetContextPostProcessor implements MqMessagePostProcessor {

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        getContext(message);
        return message;
    }

    @Override
    public Message postProcessMessage(Message message, Correlation correlation) {
        getContext(message);
        return message;
    }

    /**
     * 自定义处理
     * @param message
     */
    private void getContext(Message message) {
        System.out.println("获取context");
//        message.getMessageProperties().getHeader("test");
    }
}
