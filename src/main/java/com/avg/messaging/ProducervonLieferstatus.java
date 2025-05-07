package com.avg.messaging;

import com.avg.entity.Bestellabwicklung.OrderStatus;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class ProducervonLieferstatus {

    private final static String QUEUE_NAME = "erp-to-ecommerce-queue";

    public static void sendlieferstatus() throws Exception {
        System.out.println("Programm gestartet");

        // Beispielhafte alte und neue Statuswerte (kannst du nach Bedarf ändern)
        OrderStatus alterStatus = OrderStatus.SHIPPED;
        OrderStatus neuerStatus = OrderStatus.PROCESSED;
        String orderId = "ORD123";

        if (statusHatSichGeaendert(alterStatus, neuerStatus)) {
            sendeStatusUpdate(orderId, neuerStatus);
        } else {
            System.out.println(" [i] Kein Versand  Status hat sich nicht geändert.");
        }
    }

    private static boolean statusHatSichGeaendert(OrderStatus alterStatus, OrderStatus neuerStatus) {
        return !alterStatus.equals(neuerStatus);
    }

    private static void sendeStatusUpdate(String orderId, OrderStatus neuerStatus) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Lieferstatus für Bestellung " + orderId + " ist jetzt " + neuerStatus;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

            System.out.println(" [x] Sent: '" + message + "'");
        }
    }
}
