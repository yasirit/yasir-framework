package net.cn.yasir.framework.mq.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
public class RabbitMqPublisher<T, M extends RabbitMessage<T>> implements Publisher<M> {

    private RabbitTemplate rabbitTemplate;

    public RabbitMqPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(M m, CorrelationData correlationData) {
        this.checkMessage(m);
        this.doPublish(m, correlationData);
    }

    @Override
    public void publish(M m) {
        this.checkMessage(m);
        this.doPublish(m, null);
    }

    private void checkMessage(M message) {
        if(StringUtils.isEmpty(message.getExchange())) {
            throw new RuntimeException("请配置exchange");
        }
        if(StringUtils.isEmpty(message.getRouteKey())) {
            throw new RuntimeException("请配置routeKey");
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
     */
    private void doPublish(M m, CorrelationData correlationData) {
        String exchange = m.getExchange();
        String routeKey = m.getRouteKey();
        T data = m.getData();
        rabbitTemplate.convertAndSend(exchange, routeKey, data, correlationData);
    }
}
