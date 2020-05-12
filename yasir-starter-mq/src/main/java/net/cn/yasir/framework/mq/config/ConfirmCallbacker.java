package net.cn.yasir.framework.mq.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;
import java.util.Objects;

/**
 * @author yasir
 * @version 1.0.0
 * @desc
 * @date 2020-05-11
 */
public class ConfirmCallbacker implements RabbitTemplate.ConfirmCallback {

    private List<ConfirmCallbackHandler> confirmCallbackHandlers;

    public ConfirmCallbacker(List<ConfirmCallbackHandler> confirmCallbackHandlers) {
        this.confirmCallbackHandlers = confirmCallbackHandlers;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(Objects.isNull(correlationData)) {
            return;
        }
        String type = correlationData.getId();
        for(int i = 0; i < confirmCallbackHandlers.size(); i++) {
            ConfirmCallbackHandler confirmCallbackHandler = confirmCallbackHandlers.get(i);
            if(confirmCallbackHandler.getType().equals(type)) {
                confirmCallbackHandler.doIt(correlationData, ack, cause);
                break;
            }
        }
    }
}
