package com.avg.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducervonLieferstatus {

    private final RabbitTemplate rabbitTemplate;

    public static final String EXCHANGE_NAME = RabbitConfig.EXCHANGE_NAME;
    public static final String ROUTING_KEY = RabbitConfig.ROUTING_KEY;

    public ProducervonLieferstatus(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendUpdatetoEcommerce(DeliveryStatusMessage message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
        System.out.println("ERP Message sent: " + message.getOrderId());
    }
}
