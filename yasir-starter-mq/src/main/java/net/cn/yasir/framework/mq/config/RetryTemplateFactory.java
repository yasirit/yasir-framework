package net.cn.yasir.framework.mq.config;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.amqp.RabbitRetryTemplateCustomizer;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.time.Duration;
import java.util.List;

/**
 * 重试容器
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/5/13
 */
public class RetryTemplateFactory {
    private final List<RabbitRetryTemplateCustomizer> customizers;

    public RetryTemplateFactory(List<RabbitRetryTemplateCustomizer> customizers) {
        this.customizers = customizers;
    }

    public RetryTemplate createRetryTemplate(RabbitProperties.Retry properties, RabbitRetryTemplateCustomizer.Target target) {
        PropertyMapper map = PropertyMapper.get();
        RetryTemplate template = new RetryTemplate();
        SimpleRetryPolicy policy = new SimpleRetryPolicy();
        map.from(properties::getMaxAttempts).to(policy::setMaxAttempts);
        template.setRetryPolicy(policy);
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        map.from(properties::getInitialInterval).whenNonNull().as(Duration::toMillis)
                .to(backOffPolicy::setInitialInterval);
        map.from(properties::getMultiplier).to(backOffPolicy::setMultiplier);
        map.from(properties::getMaxInterval).whenNonNull().as(Duration::toMillis).to(backOffPolicy::setMaxInterval);
        template.setBackOffPolicy(backOffPolicy);
        if (this.customizers != null) {
            for (RabbitRetryTemplateCustomizer customizer : this.customizers) {
                customizer.customize(target, template);
            }
        }
        return template;
    }
}
