package com.avg.entity;

import java.time.LocalDate;
import java.util.Objects;

//Entity-Klasse zur Bestellabwicklung im ERP
public class Bestellabwicklung {

    public enum OrderStatus {
        PROCESSED, SHIPPED, CANCELLED
    }

    private String orderID;        // Primärschlüssel
    private String customerID;     // Verweis auf Kunde
    private String productID;      // Verweis auf Produkt
    private OrderStatus orderStatus;
    private LocalDate shippingDate;

    //Leerer Konstruktor
    public Bestellabwicklung() {
        
    }

    // Konstruktor
    public Bestellabwicklung(String orderID, String customerID, String productID, OrderStatus orderStatus, LocalDate shippingDate) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.productID = productID;
        this.orderStatus = orderStatus;
        this.shippingDate = shippingDate;
    }

    // Getter und Setter
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    // equals basierend auf orderID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bestellabwicklung)) return false;
        Bestellabwicklung order = (Bestellabwicklung) o;
        return Objects.equals(orderID, order.orderID);
    }

    // hashCode basierend auf orderID
    @Override
    public int hashCode() {
        return Objects.hash(orderID);
    }

    // String-Repräsentation
    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", productID='" + productID + '\'' +
                ", orderStatus=" + orderStatus +
                ", shippingDate=" + shippingDate +
                '}';
    }
}

