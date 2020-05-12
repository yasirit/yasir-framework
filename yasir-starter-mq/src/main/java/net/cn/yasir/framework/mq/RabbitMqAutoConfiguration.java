package net.cn.yasir.framework.mq;

import net.cn.yasir.framework.mq.config.AfterReceiveMessageGetContextPostProcessor;
import net.cn.yasir.framework.mq.config.BeforePublishMessageSetContextPostProcessor;
import net.cn.yasir.framework.mq.config.ConfirmCallbackHandler;
import net.cn.yasir.framework.mq.config.ConfirmCallbacker;
import net.cn.yasir.framework.mq.config.NoneConfirmCallback;
import net.cn.yasir.framework.mq.config.RabbitMqProducerInitializePostProcessor;
import net.cn.yasir.framework.mq.config.RabbitMqPublisher;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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

    @Bean("setContextPostProcessor")
    public BeforePublishMessageSetContextPostProcessor beforePublishMessageSetContextPostProcessor() {
        return new BeforePublishMessageSetContextPostProcessor();
    }

    @Bean("getContextPostProcessor")
    public AfterReceiveMessageGetContextPostProcessor afterReceiveMessageGetContextPostProcessor() {
        return new AfterReceiveMessageGetContextPostProcessor();
    }

    @Bean(name = "rabbitListenerContainerFactory")
    @ConditionalOnProperty(prefix = "spring.rabbitmq.listener", name = "type", havingValue = "simple", matchIfMissing = true)
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
                                                                                     ConnectionFactory connectionFactory,
                                                                                     AfterReceiveMessageGetContextPostProcessor getContextPostProcessor,
                                                                                     Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setAfterReceivePostProcessors(getContextPostProcessor);
        factory.setMessageConverter(jackson2JsonMessageConverter);
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         ConfirmCallbacker confirmCallbacker,
                                         BeforePublishMessageSetContextPostProcessor setContextPostProcessor,
                                         Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(confirmCallbacker);
        rabbitTemplate.setBeforePublishPostProcessors(setContextPostProcessor);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    public RabbitMqPublisher publisher(RabbitTemplate rabbitTemplate) {
        return new RabbitMqPublisher(rabbitTemplate);
    }

}
