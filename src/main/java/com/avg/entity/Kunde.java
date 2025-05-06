package com.avg.crm.entity;

public class Kunde {
    private String customerID;
    private String name;
    private String email;
    private String phone;
    private String address;
    private PreferredContactMethod preferredContactMethod;

    public enum PreferredContactMethod {
        Email, Telefon
    }

    // Leerer Konstruktor
    public Kunde() {}

    // Konstruktor mit allen Parametern
    public Kunde(String customerID, String name, String email, String phone, String address, PreferredContactMethod preferredContactMethod) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.preferredContactMethod = preferredContactMethod;
    }

    // Getter und Setter
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PreferredContactMethod getPreferredContactMethod() {
        return preferredContactMethod;
    }

    public void setPreferredContactMethod(PreferredContactMethod preferredContactMethod) {
        this.preferredContactMethod = preferredContactMethod;
    }

    // toString()
    @Override
    public String toString() {
        return "Kunde{" +
                "customerID='" + customerID + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", preferredContactMethod=" + preferredContactMethod +
                '}';
    }

    // equals() und hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kunde)) return false;

        Kunde kunde = (Kunde) o;

        if (customerID != null ? !customerID.equals(kunde.customerID) : kunde.customerID != null) return false;
        if (name != null ? !name.equals(kunde.name) : kunde.name != null) return false;
        if (email != null ? !email.equals(kunde.email) : kunde.email != null) return false;
        if (phone != null ? !phone.equals(kunde.phone) : kunde.phone != null) return false;
        if (address != null ? !address.equals(kunde.address) : kunde.address != null) return false;
        return preferredContactMethod == kunde.preferredContactMethod;
    }

    @Override
    public int hashCode() {
        int result = customerID != null ? customerID.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (preferredContactMethod != null ? preferredContactMethod.hashCode() : 0);
        return result;
    }
}
