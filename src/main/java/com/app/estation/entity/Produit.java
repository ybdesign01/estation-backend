package com.app.estation.entity;


import jakarta.persistence.*;



@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_produit;

    private String nom_produit;
    private String prix_achat;
    private String prix_vente;

    @ManyToOne
    private Services id_service;

    @ManyToOne
    private TypeProduit type;

    public Produit(Long id_produit, String nom_produit, String prix_achat, String prix_vente, Services id_service, TypeProduit type) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.prix_achat = prix_achat;
        this.prix_vente = prix_vente;
        this.id_service = id_service;
        this.type = type;
    }

    public Produit() {
    }

    public Long getId_produit() {
        return id_produit;
    }

    public void setId_produit(Long id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getPrix_achat() {
        return prix_achat;
    }

    public void setPrix_achat(String prix_achat) {
        this.prix_achat = prix_achat;
    }

    public String getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(String prix_vente) {
        this.prix_vente = prix_vente;
    }

    public Services getId_service() {
        return id_service;
    }

    public void setId_service(Services id_service) {
        this.id_service = id_service;
    }

    public TypeProduit getType() {
        return type;
    }

    public void setType(TypeProduit type) {
        this.type = type;
    }
}
