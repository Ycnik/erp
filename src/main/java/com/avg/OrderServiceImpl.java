package com.avg;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import com.avg.Service.BestellabwicklungService;
import com.avg.entity.Bestellabwicklung;

@Service
public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

    private Server server;

    @PostConstruct
    public void startServer() throws Exception {
        server = ServerBuilder.forPort(50052)
                .addService(this)
                .build()
                .start();
        System.out.println("GRPC: OrderService läuft auf Port 50052");
    }

    @PreDestroy
    public void stopServer() {
        if (server != null) {
            server.shutdown();
            System.out.println("GRPC: OrderService gestoppt");
        }
    }

    @Override
    public void newOrder(GRPC.KaufauftragRequest reqeust, StreamObserver<GRPC.KaufauftragReply> responseObserver) {
        // Simulierte Logik zur Verarbeitung des Kaufauftrags
        System.out.println("GRPC: Verarbeite Kaufauftrag: OrderID=" + reqeust.getOrderId() + ", CustomerID=" + reqeust.getCustomerId());


        //Order vearbeiten
        BestellabwicklungService bestellabwicklungService = new BestellabwicklungService();
        Bestellabwicklung bestellabwicklung = new Bestellabwicklung();
        try {
            bestellabwicklung = bestellabwicklungService.processOrder((reqeust.getOrderId()), reqeust.getCustomerId(),  reqeust.getProductId(), reqeust.getQuantity());
            // Erstelle die Antwort
            GRPC.KaufauftragReply reply = GRPC.KaufauftragReply.newBuilder()
            .setLieferdatum(bestellabwicklung.getShippingDate().toString())
            .setLieferstatus(bestellabwicklung.getOrderStatus().toString())
            .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("GRPC: Kaufauftrag: OrderID=" + reqeust.getOrderId() + ", CustomerID=" + reqeust.getCustomerId() + " wurde verarbeitet");

    }
}