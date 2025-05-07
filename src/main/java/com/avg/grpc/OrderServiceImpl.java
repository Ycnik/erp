package com.avg.grpc;

import com.avg.entity.Bestellabwicklung;
import com.avg.Service.BestellabwicklungService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;


public class OrderServiceImpl  {

    public void newOrder(KaufauftragRequest request, StreamObserver<KaufauftragReply> responseObserver) {
        // Parameter aus Request extrahieren
        String orderId = request.getOrderId();
        String customerId = request.getCustomerId();
        String productId = request.getProductId();
        int quantity = request.getQuantity();

        // Service aufrufen
        BestellabwicklungService service = new BestellabwicklungService();
        // Angenommen, diese Methode gibt ein Objekt mit getLieferdatum() und getLieferstatus()
        Bestellabwicklung ergebnis = null;
        try {
            // Aufruf von processOrder innerhalb eines try-catch Blocks, um Bestellung zu verarbeiten
            ergebnis = service.processOrder(orderId, customerId, productId, quantity);
            
            // Antwort bauen
            KaufauftragReply reply = new KaufauftragReply();
                reply.setLieferdatum(ergebnis.getShippingDate());
                reply.setLieferstatus(ergebnis.getOrderStatus());
            
            // Antwort an den Client senden
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
    
        } catch (Exception e) {
            // Fehlerbehandlung: Den Fehler an den Client zurücksenden
            responseObserver.onError(
                Status.INTERNAL
                    .withDescription("Fehler bei der Bestellung: " + e.getMessage())
                    .withCause(e)
                    .asRuntimeException()
            );
        }
    
    }
}
