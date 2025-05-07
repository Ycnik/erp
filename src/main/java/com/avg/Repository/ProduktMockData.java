package com.avg.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.avg.entity.Produktverwaltung;

public class ProduktMockData {

    public static List<Produktverwaltung> createMockOrders() {
        List<Produktverwaltung> PRODUCTS = new ArrayList<>();

        //Beispiel-Bestellungen hinzufügen
        PRODUCTS.add(new Produktverwaltung("P001", "Laptop", "Samsung", new BigDecimal("500.00"), new BigDecimal("700.00"), 5));
        PRODUCTS.add(new Produktverwaltung("P002", "Handy", "Samsung", new BigDecimal("700.00"), new BigDecimal("900.00"), 15));
        PRODUCTS.add(new Produktverwaltung("P003", "Ladestation", "Unnamed", new BigDecimal("100.00"), new BigDecimal("200.00"), 20));
        PRODUCTS.add(new Produktverwaltung("P004", "Kamera", "Canon", new BigDecimal("50.00"), new BigDecimal("300.00"), 30));


        return PRODUCTS;
    }
}
