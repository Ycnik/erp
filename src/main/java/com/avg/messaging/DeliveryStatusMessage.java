package com.avg.messaging;

import com.avg.entity.Bestellabwicklung.OrderStatus;;
public class DeliveryStatusMessage {
    private String orderId;
    private OrderStatus status;

    public DeliveryStatusMessage() {}

    public DeliveryStatusMessage(String orderId, OrderStatus status) {
        this.orderId = orderId;
        this.status = status;
    }

    // Getter & Setter
    public String getOrderId() { return orderId; }

    public void setOrderId(String orderId) { this.orderId = orderId; }

    public OrderStatus getStatus() { return status; }
    
    public void setStatus(OrderStatus status) { this.status = status; }
}
