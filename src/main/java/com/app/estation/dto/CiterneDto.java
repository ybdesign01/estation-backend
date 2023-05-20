package com.app.estation.dto;

import com.app.estation.entity.Pompe;
import com.app.estation.entity.Produit;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CiterneDto {

    private Long id_citerne;
    @NotBlank(message = "nom_citerne_mandatory")
    private String nom_citerne;
    @NotBlank(message = "capacite_mandatory")
    private String capacite;
    @NotNull(message = "produit_mandatory")
    private ProduitDto produit;
    private List<PompeDto> pompes;

    public CiterneDto(Long id_citerne, String nom_citerne, String capacite, ProduitDto produit, List<PompeDto> pompes) {
        this.id_citerne = id_citerne;
        this.nom_citerne = nom_citerne;
        this.capacite = capacite;
        this.produit = produit;
        this.pompes = pompes;
    }

    public CiterneDto() {
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof CiterneDto that)) return false;
        return Objects.equals(id_citerne, that.id_citerne) && Objects.equals(nom_citerne, that.nom_citerne) && Objects.equals(capacite, that.capacite) && Objects.equals(produit, that.produit) && Objects.equals(pompes, that.pompes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_citerne, nom_citerne, capacite, produit, pompes);
    }

    public Long getId_citerne() {
        return this.id_citerne;
    }

    public void setId_citerne(final Long id_citerne) {
        this.id_citerne = id_citerne;
    }

    public String getNom_citerne() {
        return this.nom_citerne;
    }

    public void setNom_citerne(final String nom_citerne) {
        this.nom_citerne = nom_citerne;
    }

    public String getCapacite() {
        return this.capacite;
    }

    public void setCapacite(final String capacite) {
        this.capacite = capacite;
    }

    public ProduitDto getProduit() {
        return this.produit;
    }

    public void setProduit(final ProduitDto id_produit) {
        this.produit = id_produit;
    }

    public List<PompeDto> getPompes() {
        return this.pompes;
    }

    public void setPompes(final List<PompeDto> pompes) {
        this.pompes = pompes;
    }
}
