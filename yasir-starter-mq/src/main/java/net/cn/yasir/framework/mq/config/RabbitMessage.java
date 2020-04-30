package net.cn.yasir.framework.mq.config;

import java.io.Serializable;

/**
 * RabbitMessage
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/30
 */
public class RabbitMessage<T> {

    private String exchange;

    private String routeKey;

    private T data;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
