package com.app.estation.entity;


import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;


@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_produit;
    private String nom_produit;
    private Double prix_achat;
    private Double prix_vente;
    @ManyToOne
    private Services id_service;
    @ManyToOne
    private TypeProduit type;

    @OneToMany(mappedBy = "produit")
    private List<ProduitAction> actions;


    public Produit(Long id_produit, String nom_produit, Double prix_achat, Double prix_vente, Services id_service, TypeProduit type, List<ProduitAction> actions) {
        this.id_produit = id_produit;
        this.actions = actions;
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

    public Double getPrix_achat() {
        return prix_achat;
    }

    public void setPrix_achat(Double prix_achat) {
        this.prix_achat = prix_achat;
    }

    public Double getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(Double prix_vente) {
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

    public List<ProduitAction> getActions() {
        return this.actions;
    }

    public void setActions(final List<ProduitAction> actions) {
        this.actions = actions;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Produit produit)) return false;
        return Objects.equals(id_produit, produit.id_produit) && Objects.equals(nom_produit, produit.nom_produit) && Objects.equals(prix_achat, produit.prix_achat) && Objects.equals(prix_vente, produit.prix_vente) && Objects.equals(id_service, produit.id_service) && Objects.equals(type, produit.type) && Objects.equals(actions, produit.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_produit, nom_produit, prix_achat, prix_vente, id_service, type, actions);
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id_produit=" + id_produit +
                ", nom_produit='" + nom_produit + '\'' +
                ", prix_achat=" + prix_achat +
                ", prix_vente=" + prix_vente +
                ", id_service=" + id_service +
                ", type=" + type +
                ", actions=" + actions +
                '}';
    }
}
