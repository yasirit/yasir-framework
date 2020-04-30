package net.cn.yasir.framework.mq.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * FactoryBean
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/30
 */
public class ProducerFactoryBean implements FactoryBean<Object>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Map<Method, ProducerInfo> producerInfoMethods;

    private Class<?> type;

    public void setProducerInfoMethods(Map<Method, ProducerInfo> producerInfoMethods) {
        this.producerInfoMethods = producerInfoMethods;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object getObject() throws BeansException {
        RabbitMqPublisher publisher = this.applicationContext.getBean(RabbitMqPublisher.class);
        Object proxy = Proxy.newProxyInstance(ProducerFactoryBean.class.getClassLoader(), new Class[]{this.type}, new ProducerInvocationHandler(publisher, this.producerInfoMethods));
        return proxy;
    }

    @Override
    public Class<?> getObjectType() {
        return type;
    }
}
