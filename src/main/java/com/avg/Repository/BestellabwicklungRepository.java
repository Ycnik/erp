package com.avg.Repository;

import java.util.List;
import java.util.Optional;
import com.avg.entity.Bestellabwicklung;
import com.avg.entity.Bestellabwicklung.OrderStatus;

public class BestellabwicklungRepository {

    //leerer Konstruktor
     public BestellabwicklungRepository() {
     }

    // Mock-Daten aus der OrderMockData laden
    private final List<Bestellabwicklung> orders = OrderMockData.createMockOrders(); 

    // Suche nach einer Bestellung anhand der OrderID
    public Optional<Bestellabwicklung> findById(String orderId) {
        return orders.stream()
                .filter(order -> order.getOrderID().equals(orderId))
                .findFirst();
    }

    // Bestellung speichern 
    public Bestellabwicklung save(Bestellabwicklung order) {

        // Wenn eine Bestellung mit dieser ID schon existiert, wird eine Exception geworfen
        if (orders.stream().anyMatch(o -> o.getOrderID().equals(order.getOrderID()))) {
            throw new IllegalArgumentException("Bestellung mit dieser OrderId existiert bereits: " + order.getOrderID());
        }

        orders.add(order);
        return order;
    }

    //OrderStatus updaten
    public void updateOrderStatus(String orderId, OrderStatus status) {
        findById(orderId).ifPresent(order -> {
            order.setOrderStatus(status);
        });
    }

}