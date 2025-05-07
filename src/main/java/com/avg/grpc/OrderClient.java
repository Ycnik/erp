package com.avg.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class OrderClient {
    
    public static void sendOrder() {
        
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
        .usePlaintext()  // keine Verschlüsselung, nur für Entwicklungszwecke
        .build();

        OrderServiceGrpc.OrderServiceBlockingStub stub = OrderServiceGrpc.newBlockingStub(channel);

        //Bestellung an den Server senden
        KaufauftragRequest request = new KaufauftragRequest();
        request.setOrderId("12345");
        request.setProductId("9876");
        request.setQuantity(3);

        //Server aufrufen
        KaufauftragReply reply = stub.newOrder(request);

        //Antwort ausgeben
        System.out.println("Lieferdatum" + reply.getLieferdatum());
        System.out.println("Lieferstatus" + reply.getLieferstatus());
    }
}
