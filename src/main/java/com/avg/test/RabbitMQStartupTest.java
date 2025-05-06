package com.avg.test;

import com.avg.entity.Bestellhistorie;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RabbitMQStartupTest {

    private static final String QUEUE_NAME = "order_updates_crm";

    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    @EventListener(ApplicationReadyEvent.class)
    public void runTestOnStartup() {
        System.out.println("🔄 [STARTUP TEST] RabbitMQ-Systemtests werden ausgeführt...");

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");

            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {

                channel.queueDeclare(QUEUE_NAME, true, false, false, null);

                ObjectMapper mapper = new ObjectMapper();

                // ✅ 1. Gültige Nachricht
                Bestellhistorie order = new Bestellhistorie();
                order.setOrderID("TEST_VALID");
                order.setTotalAmount(new BigDecimal("55.55"));
                order.setStatus(Bestellhistorie.Status.Completed);
                String validMessage = mapper.writeValueAsString(order);
                publish(channel, validMessage, "Gültige Nachricht");

                // ❌ 2. Ungültiges JSON
                String invalidJson = "{ this is not json!!! }";
                publish(channel, invalidJson, "Ungültiger JSON-String");

                // ⚠ 3. Fehlende Felder (nur orderID)
                String missingFields = "{\"orderID\":\"TEST_PARTIAL\"}";
                publish(channel, missingFields, "Teilweise fehlende Felder");

                // ❌ 4. Ungültiger Enum-Wert
                String wrongStatus = "{\"orderID\":\"TEST_WRONGSTATUS\", \"totalAmount\":123.45, \"status\":\"Shipped\"}";
                publish(channel, wrongStatus, "Ungültiger Order-Status");

            }

        } catch (Exception e) {
            System.err.println(RED + "❌ Allgemeiner Fehler bei den Tests: " + e.getMessage() + RESET);
            e.printStackTrace();
        }
    }

    private void publish(Channel channel, String message, String description) {
        try {
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(GREEN + "✔ [" + description + "] gesendet: " + message + RESET);
        } catch (Exception e) {
            System.err.println(RED + "❌ Fehler bei [" + description + "]: " + e.getMessage() + RESET);
        }
    }
}
