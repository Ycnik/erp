package com.avg.messaging;

import com.avg.entity.Bestellhistorie;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class OrderMessageConsumer {
    private static final String QUEUE_NAME = "order_updates_crm";
    private static final String LOG_FILE = "bestellhistorie_log.txt";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void startListening() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");  // ggf. anpassen

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            System.out.println(" [CRM] Waiting for messages...");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String messageJson = new String(delivery.getBody(), StandardCharsets.UTF_8);
                try {
                    // JSON-Nachricht in Bestellhistorie-Objekt parsen
                    Bestellhistorie bestellhistorie = objectMapper.readValue(messageJson, Bestellhistorie.class);
                    bestellhistorie.setOrderDate(new Date()); // Datum jetzt setzen

                    // Konsolenausgabe
                    System.out.println(" [CRM] Received: " + bestellhistorie);

                    // In Datei speichern
                    saveToFile(bestellhistorie);

                } catch (Exception e) {
                    System.err.println(" [CRM] Fehler beim Parsen der Nachricht: " + e.getMessage());
                    e.printStackTrace();
                }
            };

            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});

        } catch (IOException | TimeoutException e) {
            System.err.println(" [CRM] Fehler beim Verbindungsaufbau: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveToFile(Bestellhistorie bestellhistorie) {
        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            out.println(bestellhistorie.toString());
        } catch (IOException e) {
            System.err.println(" [CRM] Fehler beim Schreiben in Datei: " + e.getMessage());
        }
    }
}
