package com.app.estation.dto;

import com.app.estation.entity.Produit;
import com.app.estation.entity.TypeAction;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.Objects;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProduitActionDto {

    private Long id_action;
    private ProduitDto produit;
    private String date_action;
    private String quantite;
    private TypeAction action;
    private String fournisseur;


    public ProduitActionDto() {

    }

    public ProduitActionDto(Long id_action, ProduitDto produit, String date_action, String quantite, TypeAction action, String fournisseur) {
        this.id_action = id_action;
        this.produit = produit;
        this.date_action = date_action;
        this.quantite = quantite;
        this.action = action;
        this.fournisseur = fournisseur;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ProduitActionDto that)) return false;
        return Objects.equals(id_action, that.id_action) && Objects.equals(produit, that.produit) && Objects.equals(date_action, that.date_action) && Objects.equals(quantite, that.quantite) && action == that.action && Objects.equals(fournisseur, that.fournisseur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_action, produit, date_action, quantite, action, fournisseur);
    }

    public Long getId_action() {
        return this.id_action;
    }

    public void setId_action(final Long id_action) {
        this.id_action = id_action;
    }

    public ProduitDto getProduit() {
        return this.produit;
    }

    public void setProduit(final ProduitDto produit) {
        this.produit = produit;
    }

    public String getDate_action() {
        return this.date_action;
    }

    public void setDate_action(final String date_action) {
        this.date_action = date_action;
    }

    public String getQuantite() {
        return this.quantite;
    }

    public void setQuantite(final String quantite) {
        this.quantite = quantite;
    }

    public TypeAction getAction() {
        return this.action;
    }

    public void setAction(final TypeAction action) {
        this.action = action;
    }

    public String getFournisseur() {
        return this.fournisseur;
    }

    public void setFournisseur(final String fournisseur) {
        this.fournisseur = fournisseur;
    }
}
