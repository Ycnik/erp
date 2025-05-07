package com.avg.Service;

import java.time.LocalDate;

import com.avg.entity.Bestellabwicklung;
import com.avg.entity.Produktverwaltung;
import com.avg.entity.Bestellabwicklung.OrderStatus;
import com.avg.Repository.*;
import com.avg.messaging.ProducervonLieferstatus;

public class BestellabwicklungService {

    private final BestellabwicklungRepository orderRepository;
    private final ProduktRepository productRepository;

    //leerer Konstruktor
    public BestellabwicklungService() {
        this.orderRepository = null;
        this.productRepository = null;
    }

    public BestellabwicklungService(BestellabwicklungRepository orderRepository, ProduktRepository produktRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = produktRepository;
    }

    public Bestellabwicklung processOrder(String orderId, String customerId, String productId, int quantity) throws Exception {
        //Bestellobjekt anlegen
        Bestellabwicklung order = new Bestellabwicklung();
        order.setOrderID(orderId);
        order.setCustomerID(customerId);
        order.setProductID(productId);
        order.setOrderStatus(OrderStatus.PROCESSED);
        order.setShippingDate(LocalDate.now().plusDays(3));              //Versandatum manuell auf Bestelldatum + 3 gesetzt);

        // 2. Bestand anpassen
        Produktverwaltung product = productRepository.findById(productId).orElseThrow();
        productRepository.updateStockLevel(productId, product.getStockLevel() - quantity);

        // 3. Bestellung speichern
        orderRepository.save(order);

        //RabbitMQ
        ProducervonLieferstatus.sendlieferstatus();

        //gRPC


        return order;
    }

}