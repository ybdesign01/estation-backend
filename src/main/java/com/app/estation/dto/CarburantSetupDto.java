package com.app.estation.dto;

public class CarburantSetupDto {
    private String nom_produit;
    private Double prix_achat;
    private Double prix_vente;

    public String getNom_produit() {
        return this.nom_produit;
    }

    public void setNom_produit(final String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public Double getPrix_achat() {
        return this.prix_achat;
    }

    public void setPrix_achat(final Double prix_achat) {
        this.prix_achat = prix_achat;
    }

    public Double getPrix_vente() {
        return this.prix_vente;
    }

    public void setPrix_vente(final Double prix_vente) {
        this.prix_vente = prix_vente;
    }
}
