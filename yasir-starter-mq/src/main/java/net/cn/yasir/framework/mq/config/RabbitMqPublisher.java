package net.cn.yasir.framework.mq.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * rabbitmq发布器
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/30
 */
public class RabbitMqPublisher<T, M extends RabbitMessage<T>> implements RabbitPublisher<T, M> {

    private ApplicationContext applicationContext;

    private MessageConverter messageConverter;

    MessageProperties messageProperties = new MessageProperties();

    public RabbitMqPublisher(ApplicationContext applicationContext, MessageConverter messageConverter) {
        this.applicationContext = applicationContext;
        this.messageConverter = messageConverter;
    }

    @Override
    public void publish(M m, CorrelationData correlationData, RabbitTemplate.ConfirmCallback confirmCallback) {
        this.checkMessage(m);
        this.doPublish(m, correlationData, confirmCallback);
    }

    @Override
    public void publish(M m) {
        this.checkMessage(m);
        this.doPublish(m, null, null);
    }

    private void checkMessage(M message) {
        if(StringUtils.isEmpty(message.getExchange())) {
            throw new RuntimeException("请配置exchange");
        }
        if(StringUtils.isEmpty(message.getRouteKey())) {
            throw new RuntimeException("请配置exchange");
        }
        if(Objects.isNull(message.getData())) {
            throw new RuntimeException("消息不能为空");
        } else if(!(message.getData() instanceof Serializable)) {
            throw new RuntimeException("消息必须实现序列化接口");
        }
    }

    /**
     * 发布消息
     * @param m
     * @param correlationData
     * @param confirmCallback
     */
    private void doPublish(M m, CorrelationData correlationData, RabbitTemplate.ConfirmCallback confirmCallback) {
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        String exchange = m.getExchange();
        String routeKey = m.getRouteKey();
        Message message = this.messageConverter.toMessage(m.getData(), messageProperties);
        rabbitTemplate.convertAndSend(exchange, routeKey, message, correlationData);
    }
}
