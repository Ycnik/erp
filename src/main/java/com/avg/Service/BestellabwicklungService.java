package com.avg.Service;

import java.time.LocalDate;
import com.avg.entity.Bestellabwicklung;
import com.avg.entity.Produktverwaltung;
import com.avg.entity.Bestellabwicklung.OrderStatus;
import com.avg.Repository.*;
import com.avg.messaging.DeliveryStatusMessage;
import com.avg.messaging.ProducervonLieferstatus;

public class BestellabwicklungService {

    private final BestellabwicklungRepository orderRepository;
    private final ProduktRepository productRepository;
    private final ProducervonLieferstatus producervonLieferstatus;

    //leerer Konstruktor
    public BestellabwicklungService() {
        this.orderRepository = new BestellabwicklungRepository();
        this.productRepository = new ProduktRepository();
        this.producervonLieferstatus = null;
    }

    public BestellabwicklungService(BestellabwicklungRepository orderRepository, ProduktRepository produktRepository, ProducervonLieferstatus producervonLieferstatus) {
        this.orderRepository = orderRepository;
        this.productRepository = produktRepository;
        this.producervonLieferstatus = producervonLieferstatus;
    }

    public Bestellabwicklung processOrder(
        String orderId,
        String customerId,
        String productId,
        String quantityStr
) throws InvalidOrderException, ProductNotFoundException, InsufficientStockException {
    // 1. Menge parsen und validieren
    final int quantity;
    try {
        quantity = Integer.parseInt(quantityStr);
    } catch (NumberFormatException e) {
        throw new InvalidOrderException("Ungültiges Mengen-Format: " + quantityStr, e);
    }
    if (quantity <= 0) {
        throw new InvalidOrderException("Menge muss größer als 0 sein, war: " + quantity);
    }

    // 2. Produkt laden oder gezielt fehlern
    Produktverwaltung product = productRepository.findById(productId)
        .orElseThrow(() -> new ProductNotFoundException("Produkt nicht gefunden: " + productId));

    // 3. Lagerbestand prüfen
    int currentStock = product.getStockLevel();
    if (currentStock < quantity) {
        throw new InsufficientStockException(
            String.format("Nicht genügend Lagerbestand für Produkt %s: aktuell %d, benötigt %d",
                          productId, currentStock, quantity));
    }

    // 4. Bestellobjekt anlegen
    Bestellabwicklung order = new Bestellabwicklung();
    order.setOrderID(orderId);
    order.setCustomerID(customerId);
    order.setProductID(productId);
    order.setOrderStatus(OrderStatus.SHIPPED);
    order.setShippingDate(LocalDate.now().plusDays(3));

    // 5. Lagerbestand aktualisieren und Bestellung speichern
    productRepository.updateStockLevel(productId, currentStock - quantity);
    orderRepository.save(order);

    // 6. Status-Nachricht verschicken
    DeliveryStatusMessage msg = new DeliveryStatusMessage(
        order.getOrderID(),
        order.getOrderStatus()
    );
    producervonLieferstatus.sendUpdatetoEcommerce(msg);

    return order;
    }

}

    

