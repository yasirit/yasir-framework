package net.cn.yasir.framework.mq;

import net.cn.yasir.framework.mq.config.RabbitMqProducerInitializePostProcessor;
import net.cn.yasir.framework.mq.config.RabbitMqPublisher;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public RabbitMqProducerInitializePostProcessor rabbitMqProducerInitializePostProcessor() {
        return new RabbitMqProducerInitializePostProcessor();
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitMqPublisher mqPublisher(ApplicationContext applicationContext, Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        return new RabbitMqPublisher(applicationContext, jackson2JsonMessageConverter);
    }
}
