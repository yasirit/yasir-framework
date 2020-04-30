package net.cn.yasir.framework.mq.config;

/**
 * 生产者
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/30
 */
public class ProducerInfo {

    private String exchange;

    private String key;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
