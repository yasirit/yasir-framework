package net.cn.yasir.framework.mq;

import net.cn.yasir.framework.mq.config.AfterReceiveMessageGetContextPostProcessor;
import net.cn.yasir.framework.mq.config.BeforePublishMessageSetContextPostProcessor;
import net.cn.yasir.framework.mq.config.ConfirmCallbackHandler;
import net.cn.yasir.framework.mq.config.ConfirmCallbacker;
import net.cn.yasir.framework.mq.config.NoneConfirmCallback;
import net.cn.yasir.framework.mq.config.RabbitMqProducerInitializePostProcessor;
import net.cn.yasir.framework.mq.config.RabbitMqPublisher;
import net.cn.yasir.framework.mq.config.RetryTemplateFactory;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.amqp.DirectRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.amqp.RabbitRetryTemplateCustomizer;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MQ自动配置
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/29
 */
@Configuration
public class RabbitMqAutoConfiguration {

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

    @Bean(name = "rabbitListenerContainerFactory")
    @ConditionalOnMissingBean(name = "rabbitListenerContainerFactory")
    @ConditionalOnProperty(prefix = "spring.rabbitmq.listener", name = "type", havingValue = "direct")
    DirectRabbitListenerContainerFactory directRabbitListenerContainerFactory(
            DirectRabbitListenerContainerFactoryConfigurer configurer,
            ConnectionFactory connectionFactory,
            AfterReceiveMessageGetContextPostProcessor getContextPostProcessor,
            Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();
        factory.setAfterReceivePostProcessors(getContextPostProcessor);
        factory.setMessageConverter(jackson2JsonMessageConverter);
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(RabbitProperties properties,
                                         ObjectProvider<RabbitRetryTemplateCustomizer> retryTemplateCustomizers,
                                         ConnectionFactory connectionFactory,
                                         ConfirmCallbacker confirmCallbacker,
                                         BeforePublishMessageSetContextPostProcessor setContextPostProcessor,
                                         Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        PropertyMapper map = PropertyMapper.get();
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setConfirmCallback(confirmCallbacker);
        template.setBeforePublishPostProcessors(setContextPostProcessor);
        template.setMessageConverter(jackson2JsonMessageConverter);
        template.setMandatory(determineMandatoryFlag(properties));
        RabbitProperties.Template templateProperties = properties.getTemplate();
        if (templateProperties.getRetry().isEnabled()) {
            RetryTemplateFactory retryTemplateFactory = new RetryTemplateFactory(retryTemplateCustomizers.orderedStream().collect(Collectors.toList()));
            template.setRetryTemplate(retryTemplateFactory.createRetryTemplate(templateProperties.getRetry(),
                                    RabbitRetryTemplateCustomizer.Target.SENDER));
        }
        map.from(templateProperties::getReceiveTimeout).whenNonNull().as(Duration::toMillis)
                .to(template::setReceiveTimeout);
        map.from(templateProperties::getReplyTimeout).whenNonNull().as(Duration::toMillis)
                .to(template::setReplyTimeout);
        map.from(templateProperties::getExchange).to(template::setExchange);
        map.from(templateProperties::getRoutingKey).to(template::setRoutingKey);
        map.from(templateProperties::getDefaultReceiveQueue).whenNonNull().to(template::setDefaultReceiveQueue);
        return template;
    }

    private boolean determineMandatoryFlag(RabbitProperties properties) {
        Boolean mandatory = properties.getTemplate().getMandatory();
        return (mandatory != null) ? mandatory : properties.isPublisherReturns();
    }

    @Bean
    public RabbitMqPublisher publisher(RabbitTemplate rabbitTemplate) {
        return new RabbitMqPublisher(rabbitTemplate);
    }

}
