package com.app.estation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    private Double capacite;
    @NotNull(message = "produit_mandatory")
    private ProduitDto produit;
    private List<CiternePompeDto> pompes;
    @NotNull(message = "station_mandatory")
    private StationDto station;

    public CiterneDto(Long id_citerne, String nom_citerne, Double capacite, ProduitDto produit, List<CiternePompeDto> pompes, StationDto station) {
        this.station = station;
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
        return Objects.equals(id_citerne, that.id_citerne) && Objects.equals(nom_citerne, that.nom_citerne) && Objects.equals(capacite, that.capacite) && Objects.equals(produit, that.produit) && Objects.equals(pompes, that.pompes) && Objects.equals(station, that.station);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_citerne, nom_citerne, capacite, produit, pompes, station);
    }

    public StationDto getStation() {
        return this.station;
    }

    public void setStation(final StationDto station) {
        this.station = station;
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

    public Double getCapacite() {
        return this.capacite;
    }

    public void setCapacite(final Double capacite) {
        this.capacite = capacite;
    }

    public ProduitDto getProduit() {
        return this.produit;
    }

    public void setProduit(final ProduitDto id_produit) {
        this.produit = id_produit;
    }

    public List<CiternePompeDto> getPompes() {
        return this.pompes;
    }

    public void setPompes(final List<CiternePompeDto> pompes) {
        this.pompes = pompes;
    }
}
