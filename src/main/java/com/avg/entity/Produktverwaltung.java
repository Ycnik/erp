package com.avg.entity;

import java.math.BigDecimal;
import java.util.Objects;

//Entity-Klasse für Produktverwaltung des ERP-Systems
public class Produktverwaltung {

    private String productID;       // Primärschlüssel
    private String productName;
    private String supplier;
    private BigDecimal costPrice;       // Einkaufspreis
    private BigDecimal retailPrice;     // Verkaufspreis
    private int stockLevel;         // Lagerbestand

    // Konstruktor
    public Produktverwaltung(String productID, String productName, String supplier, BigDecimal costPrice, BigDecimal retailPrice, int stockLevel) {
        this.productID = productID;
        this.productName = productName;
        this.supplier = supplier;
        this.costPrice = costPrice;
        this.retailPrice = retailPrice;
        this.stockLevel = stockLevel;
    }

    // Getter und Setter
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    // equals basierend auf productID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produktverwaltung)) return false;
        Produktverwaltung product = (Produktverwaltung) o;
        return Objects.equals(productID, product.productID);
    }

    // hashCode basierend auf productID
    @Override
    public int hashCode() {
        return Objects.hash(productID);
    }

    // String-Repräsentation
    @Override
    public String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", supplier='" + supplier + '\'' +
                ", costPrice=" + costPrice +
                ", retailPrice=" + retailPrice +
                ", stockLevel=" + stockLevel +
                '}';
    }
}
