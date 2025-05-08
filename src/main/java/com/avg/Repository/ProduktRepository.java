package com.avg.Repository;

import java.util.List;
import java.util.Optional;
import com.avg.entity.Produktverwaltung;

public class ProduktRepository {

    //leerer Konstruktor
     public ProduktRepository() {
     }

    // Mock-Daten aus der OrderMockData laden
    private final List<Produktverwaltung> products = ProduktMockData.createMockOrders(); 

    // Suche nach einer Bestellung anhand der OrderID
    public Optional<Produktverwaltung> findById(String productId) {
        return products.stream()
                .filter(product -> product.getProductID().equals(productId))
                .findFirst();
    }

    // Produkt speichern/updaten wenn Bestandsänderung
    public Produktverwaltung save(Produktverwaltung product) {

        // Wenn eine Bestellung mit dieser ID schon existiert, werfen wir eine Exception
        if (products.stream().anyMatch(o -> o.getProductID().equals(product.getProductID()))) {
            throw new IllegalArgumentException("Produkt mit dieser ProduktId existiert bereits: " + product.getProductID());
        }

        products.add(product);
        return product;
    }

    //Bestand des Produkts updaten
    public void updateStockLevel(String productId, int newStockLevel) {
        findById(productId).ifPresent(product -> product.setStockLevel(newStockLevel));
    }

}
