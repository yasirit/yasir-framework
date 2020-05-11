package net.cn.yasir.framework.mq;

import net.cn.yasir.framework.mq.config.ConfirmCallbackHandler;
import net.cn.yasir.framework.mq.config.ConfirmCallbacker;
import net.cn.yasir.framework.mq.config.NoneConfirmCallback;
import net.cn.yasir.framework.mq.config.RabbitMqProducerInitializePostProcessor;
import net.cn.yasir.framework.mq.config.RabbitMqPublisher;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * MQ自动配置
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/29
 */
@Configuration
public class RabbitMqAutoConfiguration {

    @Autowired
    private List<ConfirmCallbackHandler> confirmCallbackHandlers;

    @Bean
    public NoneConfirmCallback noneConfirmCallback() {
        return new NoneConfirmCallback();
    }

    @Bean
    public ConfirmCallbacker confirmCallbacker(List<ConfirmCallbackHandler> confirmCallbackHandlers) {
        return new ConfirmCallbacker(confirmCallbackHandlers);
    }

    @Bean
    public RabbitMqProducerInitializePostProcessor rabbitMqProducerInitializePostProcessor() {
        return new RabbitMqProducerInitializePostProcessor();
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, ConfirmCallbacker confirmCallbacker) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(confirmCallbacker);
        return rabbitTemplate;
    }

    @Bean
    public RabbitMqPublisher publisher(Jackson2JsonMessageConverter jackson2JsonMessageConverter, RabbitTemplate rabbitTemplate) {
        return new RabbitMqPublisher(jackson2JsonMessageConverter, rabbitTemplate);
    }


}
