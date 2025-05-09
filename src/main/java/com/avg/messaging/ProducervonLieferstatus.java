package com.avg.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProducervonLieferstatus {

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public static final String EXCHANGE_NAME = RabbitConfig.EXCHANGE_NAME;
    public static final String ROUTING_KEY = RabbitConfig.ROUTING_KEY;

    public ProducervonLieferstatus(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendUpdatetoEcommerce(DeliveryStatusMessage message) {
        try {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
            System.out.println("ERP Message sent: " + message.getOrderId());

        } catch (Exception e) {
            System.out.println("Error sending message: " + e.getMessage());
        }
    }
}
