package com.avg.crm.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Bestellhistorie {
    private String orderID;
    private Date orderDate;
    private BigDecimal totalAmount;
    private Status status;

    public enum Status {
        Completed, Pending, Cancelled
    }

    // Konstruktor mit allen Parametern
    public Bestellhistorie(String orderID, Date orderDate, BigDecimal totalAmount, Status status) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Bestellhistorie() {}
    
    // Getter und Setter
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // toString()
    @Override
    public String toString() {
        return "Bestellhistorie{" +
                "orderID='" + orderID + '\'' +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                '}';
    }

    // equals() und hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bestellhistorie)) return false;

        Bestellhistorie that = (Bestellhistorie) o;

        if (orderID != null ? !orderID.equals(that.orderID) : that.orderID != null) return false;
        if (orderDate != null ? !orderDate.equals(that.orderDate) : that.orderDate != null) return false;
        if (totalAmount != null ? !totalAmount.equals(that.totalAmount) : that.totalAmount != null) return false;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = orderID != null ? orderID.hashCode() : 0;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
