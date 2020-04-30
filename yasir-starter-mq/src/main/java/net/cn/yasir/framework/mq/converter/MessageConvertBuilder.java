package net.cn.yasir.framework.mq.converter;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.Jackson2XmlMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SerializerMessageConverter;

/**
 * 消息转换器构造器
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/30
 */
public class MessageConvertBuilder {
    private final static String JSON_CONVERTER_TYPE = "Jackson2Xml";
    private final static String SERI_CONVERTER_TYPE = "Serializer";

    public MessageConvertBuilder() {
    }

    /**
     * 构造消息转换器
     * @param
     * @return
     */
    public static MessageConverter buildMessageConverter() {
        return new Jackson2XmlMessageConverter();
    }

    /**
     * 构造消息转换器
     * @param converterType
     * @return
     */
    public static MessageConverter buildMessageConverter(String converterType) {
        if (JSON_CONVERTER_TYPE.equalsIgnoreCase(converterType)) {
            return new Jackson2XmlMessageConverter();
        } else {
            return SERI_CONVERTER_TYPE.equalsIgnoreCase(converterType) ? new SerializerMessageConverter() : new Jackson2JsonMessageConverter();
        }
    }
}
