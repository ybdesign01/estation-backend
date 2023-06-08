package com.app.estation.dto;

import java.time.LocalDateTime;

public class ActionDateDto {
    private Double montant;
    private LocalDateTime date;

public ActionDateDto() {
    }

    public Double getMontant() {
        return this.montant;
    }

    public void setMontant(final Double montant) {
        this.montant = montant;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(final LocalDateTime date) {
        this.date = date;
    }
}
