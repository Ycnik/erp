package com.avg.grpc;


public final class KaufauftragRequest {
    public static Object toString;
    private String orderId;
    private String customerId;
    private String productId;
    private int quantity;

    // Standard-Konstruktor
    public KaufauftragRequest() {}

    // Konstruktor mit Feldern
    public KaufauftragRequest(String orderId, String customerId, String productId, int quantity) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getter & Setter
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
