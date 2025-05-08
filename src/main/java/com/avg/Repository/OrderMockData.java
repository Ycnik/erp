package com.avg.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.avg.entity.Bestellabwicklung;
import com.avg.entity.Bestellabwicklung.OrderStatus;

public class OrderMockData {

    public static List<Bestellabwicklung> createMockOrders() {
        List<Bestellabwicklung> ORDERS = new ArrayList<>();

        //Beispiel-Bestellungen hinzufügen
        ORDERS.add(new Bestellabwicklung("1", "C001", "P001", OrderStatus.PROCESSED, LocalDate.now().plusDays(2)));
        ORDERS.add(new Bestellabwicklung("2", "C002", "P002", OrderStatus.SHIPPED, LocalDate.now().plusDays(3)));
        ORDERS.add(new Bestellabwicklung("3", "C003", "P003", OrderStatus.CANCELLED, LocalDate.now().plusDays(1)));
        ORDERS.add(new Bestellabwicklung("4", "C004", "P004", OrderStatus.PROCESSED, LocalDate.now().plusDays(4)));

        return ORDERS;
    }
}