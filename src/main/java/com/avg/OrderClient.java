package com.avg;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class OrderClient {
    public static void sendOrderRequest() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        OrderServiceGrpc.OrderServiceBlockingStub stub = OrderServiceGrpc.newBlockingStub(channel);

        GRPC.KaufauftragRequest request = GRPC.KaufauftragRequest.newBuilder()
                .setOrderId("order123")
                .setCustomerId("cust001")
                .setProductId("prod001")
                .setQuantity("5")
                .build();

        GRPC.KaufauftragReply reply = stub.newOrder(request);

        System.out.println("Lieferdatum: " + reply.getLieferdatum());
        System.out.println("Lieferstatus: " + reply.getLieferstatus());

        channel.shutdown();
    }
}