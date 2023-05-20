package com.app.estation.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProduitDto {

    private Long id_produit;

    @NotBlank(message = "nom_produit_mandatory")
    private String nom_produit;
    @NotNull(message = "prix_achat_mandatory")
    private Double prix_achat;
    @NotNull(message = "prix_vente_mandatory")
    private Double prix_vente;

    @NotNull(message = "service_mandatory")
    private ServicesDto service;
    private TypeProduitDto type;
    private List<ProduitActionDto> actions;

    public ProduitDto() {
        this.prix_achat = 0.0;
        this.prix_vente = 0.0;
    }

    public ProduitDto(Long id_produit, String nom_produit, Double prix_achat, Double prix_vente, ServicesDto service, TypeProduitDto type, List<ProduitActionDto> actions) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.prix_achat = prix_achat;
        this.prix_vente = prix_vente;
        this.service = service;
        this.type = type;
        this.actions = actions;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ProduitDto that)) return false;
        return Objects.equals(id_produit, that.id_produit) && Objects.equals(nom_produit, that.nom_produit) && Objects.equals(prix_achat, that.prix_achat) && Objects.equals(prix_vente, that.prix_vente) && Objects.equals(service, that.service) && Objects.equals(type, that.type) && Objects.equals(actions, that.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_produit, nom_produit, prix_achat, prix_vente, service, type, actions);
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

    public ServicesDto getService() {
        return this.service;
    }

    public void setService(final ServicesDto service) {
        this.service = service;
    }

    public TypeProduitDto getType() {
        return this.type;
    }

    public void setType(final TypeProduitDto type) {
        this.type = type;
    }

    public List<ProduitActionDto> getActions() {
        return this.actions;
    }

    public void setActions(final List<ProduitActionDto> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "ProduitDto{" +
                "id_produit=" + id_produit +
                ", nom_produit='" + nom_produit + '\'' +
                ", prix_achat=" + prix_achat +
                ", prix_vente=" + prix_vente +
                ", service=" + service +
                ", type=" + type +
                ", actions=" + actions +
                '}';
    }
}
