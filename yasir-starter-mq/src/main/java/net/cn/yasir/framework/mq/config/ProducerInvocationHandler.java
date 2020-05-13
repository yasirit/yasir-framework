package net.cn.yasir.framework.mq.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 代理类处理
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/30
 */
public class ProducerInvocationHandler implements InvocationHandler {

    private static final List<String> exMethods = Arrays.stream(Object.class.getDeclaredMethods()).map(method -> method.getName()).collect(Collectors.toList());

    private Map<Method, ProducerInfo> producerInfoMethods;

    private Publisher<RabbitMessage> publisher;

    public ProducerInvocationHandler(Publisher<RabbitMessage> publisher, Map<Method, ProducerInfo> producerInfoMethods) {
        this.publisher = publisher;
        this.producerInfoMethods = producerInfoMethods;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(!exMethods.contains(method.getName()) && Objects.nonNull(args) && args.length == 1) {
            RabbitMessage message = new RabbitMessage();
            ProducerInfo producerInfo = producerInfoMethods.get(method);
            message.setExchange(producerInfo.getExchange());
            message.setRouteKey(producerInfo.getKey());
            message.setData(args[0]);
            this.publisher.publish(message);
        }
        return null;
    }
}
