package com.app.estation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CiterneDto {

    private Long id_citerne;
    @NotBlank(message = "nom_citerne_mandatory")
    private String nom_citerne;
    @NotNull(message = "capacite_mandatory")
    private Double capaciteMaximale;
    private Double capaciteActuelle;
    @NotNull(message = "produit_mandatory")
    private ProduitDto produit;
    private List<CiternePompeDto> pompes;
    @NotNull(message = "station_mandatory")
    private StationDto station;

    public CiterneDto(final Long id_citerne, final String nom_citerne, final Double capaciteMaximale, final Double capaciteActuelle, final ProduitDto id_produit, final List<CiternePompeDto> pompes, final StationDto station) {
        this.id_citerne = id_citerne;
        this.nom_citerne = nom_citerne;
        this.capaciteMaximale = capaciteMaximale;
        this.capaciteActuelle = capaciteActuelle;
        this.produit = id_produit;
        this.pompes = pompes;
        this.station = station;
    }

    public CiterneDto() {
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

    public Double getCapaciteMaximale() {
        return this.capaciteMaximale;
    }

    public void setCapaciteMaximale(final Double capaciteMaximale) {
        this.capaciteMaximale = capaciteMaximale;
    }

    public Double getCapaciteActuelle() {
        return this.capaciteActuelle;
    }

    public void setCapaciteActuelle(final Double capaciteActuelle) {
        this.capaciteActuelle = capaciteActuelle;
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
