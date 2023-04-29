package com.app.estation.entity;

import jakarta.persistence.*;


@Entity

public class ProduitAction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_action;
    @ManyToOne
    private Produit produit;
    private String date_action;
    private String quantite;
    @Enumerated(EnumType.STRING)
    private TypeAction action;
    private String fournisseur;

public ProduitAction(Long id_action, Produit produit, String date_action, String quantite, TypeAction action, String fournisseur) {
        this.id_action = id_action;
        this.produit = produit;
        this.date_action = date_action;
        this.quantite = quantite;
        this.action = action;
        this.fournisseur = fournisseur;
    }

    public ProduitAction() {
    }

    public Long getId_action() {
        return id_action;
    }

    public void setId_action(Long id_action) {
        this.id_action = id_action;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public String getDate_action() {
        return date_action;
    }

    public void setDate_action(String date_action) {
        this.date_action = date_action;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public TypeAction getAction() {
        return action;
    }

    public void setAction(TypeAction action) {
        this.action = action;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }
}
