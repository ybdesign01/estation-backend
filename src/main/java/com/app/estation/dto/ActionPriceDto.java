package com.app.estation.dto;

import com.app.estation.entity.TypeAction;

import java.time.LocalDateTime;

public class ActionPriceDto {

    private String nomProduit;
    private String quantite;
    private LocalDateTime date;
    private TypeAction typeAction;


    public ActionPriceDto(String nomProduit, String quantite, LocalDateTime date, TypeAction typeAction) {
        this.nomProduit = nomProduit;
        this.quantite = quantite;
        this.date = date;
        this.typeAction = typeAction;
    }

    public ActionPriceDto() {
    }

    public String getNomProduit() {
        return this.nomProduit;
    }

    public void setNomProduit(final String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getQuantite() {
        return this.quantite;
    }

    public void setQuantite(final String quantite) {
        this.quantite = quantite;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(final LocalDateTime date) {
        this.date = date;
    }

    public TypeAction getTypeAction() {
        return this.typeAction;
    }

    public void setTypeAction(final TypeAction typeAction) {
        this.typeAction = typeAction;
    }
}
