package com.avg.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME = "erp-to-ecommerce-queue";
    public static final String EXCHANGE_NAME = "ecommerce-to-erp-exchange";
    public static final String ROUTING_KEY = "ecommerce.routing.key";

    @Bean
    public Queue erpToEcommerceQueue() {
        return new Queue(QUEUE_NAME, true); // true = durable
    }

    @Bean
    public TopicExchange ecommerceExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue erpToEcommerceQueue, TopicExchange ecommerceExchange) {
        return BindingBuilder.bind(erpToEcommerceQueue).to(ecommerceExchange).with(ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        return template;
    }
}
