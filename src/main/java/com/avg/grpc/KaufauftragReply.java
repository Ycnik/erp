package com.avg.grpc;

import java.time.LocalDate;
import com.avg.entity.Bestellabwicklung.OrderStatus;
import com.google.protobuf.Message;

public final class KaufauftragReply {
    public static Message toString;
    private LocalDate lieferdatum;
    private OrderStatus lieferstatus;

    // Standard-Konstruktor
    public KaufauftragReply() {}

    // Konstruktor mit Feldern
    public KaufauftragReply(LocalDate lieferdatum, OrderStatus lieferstatus) {
        this.lieferdatum = lieferdatum;
        this.lieferstatus = lieferstatus;
    }

    // Getter & Setter
    public LocalDate getLieferdatum() {
        return lieferdatum;
    }

    public void setLieferdatum(LocalDate lieferdatum) {
        this.lieferdatum = lieferdatum;
    }

    public OrderStatus getLieferstatus() {
        return lieferstatus;
    }

    public void setLieferstatus(OrderStatus lieferstatus) {
        this.lieferstatus = lieferstatus;
    }


}
