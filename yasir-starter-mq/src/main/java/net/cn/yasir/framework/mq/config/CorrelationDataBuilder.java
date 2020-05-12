package net.cn.yasir.framework.mq.config;

import net.cn.yasir.framework.tool.utils.JsonSerializor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;

/**
 * CorrelationData生成器
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/3/24
 */
public class CorrelationDataBuilder<T> {

    private String id;

    private Message message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public CorrelationDataBuilder id(String id) {
        this.id = id;
        return this;
    }

    public CorrelationDataBuilder data(T t) {
        this.message = new Message(JsonSerializor.serial(t), new MessageProperties());
        return this;
    }

    public CorrelationDataBuilder message(Message message) {
        this.message = message;
        return this;
    }

    public CorrelationData builder() {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(this.id);
        correlationData.setReturnedMessage(this.message);
        return correlationData;
    }

}
