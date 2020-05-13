package net.cn.yasir.framework.mq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;

/**
 * @author yasir
 * @version 1.0.0
 * @desc
 * @date 2020-05-11
 */
public class NoneConfirmCallback implements ConfirmCallbackHandler {

    private Logger LOGGER = LoggerFactory.getLogger(NoneConfirmCallback.class);

    @Override
    public String getType() {
        return "none";
    }

    @Override
    public void doIt(CorrelationData correlationData, boolean ack, String cause) {
        LOGGER.info("nothing to do");
    }
}
