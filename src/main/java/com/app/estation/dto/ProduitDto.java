package com.app.estation.dto;

import com.app.estation.entity.ProduitAction;
import com.app.estation.entity.Services;
import com.app.estation.entity.TypeProduit;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProduitDto {

    private Long id_produit;
    private String nom_produit;
    private String prix_achat;
    private String prix_vente;
    private ServicesDto id_service;
    private TypeProduit type;
    private List<ProduitActionDto> actions;

    public ProduitDto() {
    }

    public ProduitDto(Long id_produit, String nom_produit, String prix_achat, String prix_vente, ServicesDto id_service, TypeProduit type, List<ProduitActionDto> actions) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.prix_achat = prix_achat;
        this.prix_vente = prix_vente;
        this.id_service = id_service;
        this.type = type;
        this.actions = actions;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ProduitDto that)) return false;
        return Objects.equals(id_produit, that.id_produit) && Objects.equals(nom_produit, that.nom_produit) && Objects.equals(prix_achat, that.prix_achat) && Objects.equals(prix_vente, that.prix_vente) && Objects.equals(id_service, that.id_service) && Objects.equals(type, that.type) && Objects.equals(actions, that.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_produit, nom_produit, prix_achat, prix_vente, id_service, type, actions);
    }


    public Long getId_produit() {
        return this.id_produit;
    }

    public void setId_produit(final Long id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return this.nom_produit;
    }

    public void setNom_produit(final String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getPrix_achat() {
        return this.prix_achat;
    }

    public void setPrix_achat(final String prix_achat) {
        this.prix_achat = prix_achat;
    }

    public String getPrix_vente() {
        return this.prix_vente;
    }

    public void setPrix_vente(final String prix_vente) {
        this.prix_vente = prix_vente;
    }

    public ServicesDto getId_service() {
        return this.id_service;
    }

    public void setId_service(final ServicesDto id_service) {
        this.id_service = id_service;
    }

    public TypeProduit getType() {
        return this.type;
    }

    public void setType(final TypeProduit type) {
        this.type = type;
    }

    public List<ProduitActionDto> getActions() {
        return this.actions;
    }

    public void setActions(final List<ProduitActionDto> actions) {
        this.actions = actions;
    }
}
