package com.avg.grpc;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class OrderServer {
    public static void gRPCOrderServer() throws Exception {
        Server server = ServerBuilder.forPort(50051)
            .addService((BindableService) new OrderServiceImpl())
            .build()
            .start();

        System.out.println("Server gestartet auf Port 50051");
        server.awaitTermination();
    }
}


