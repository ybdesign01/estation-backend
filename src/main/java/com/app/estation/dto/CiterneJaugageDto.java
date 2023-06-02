package com.app.estation.dto;

public class CiterneJaugageDto {
    private String nomCiterne;
    private Double jaugage;
    private String percentageLevel;
    private String nomProduit;



    public CiterneJaugageDto() {
    }

    public CiterneJaugageDto(final String nomCiterne, final Double jaugage, final String percentageLevel, final String nomProduit) {
        this.nomCiterne = nomCiterne;
        this.jaugage = jaugage;
        this.percentageLevel = percentageLevel;
        this.nomProduit = nomProduit;
    }

    public String getNomCiterne() {
        return this.nomCiterne;
    }

    public void setNomCiterne(final String nomCiterne) {
        this.nomCiterne = nomCiterne;
    }

    public Double getJaugage() {
        return this.jaugage;
    }

    public void setJaugage(final Double jaugage) {
        this.jaugage = jaugage;
    }

    public String getPercentageLevel() {
        return this.percentageLevel;
    }

    public void setPercentageLevel(final String percentageLevel) {
        this.percentageLevel = percentageLevel;
    }

    public String getNomProduit() {
        return this.nomProduit;
    }

    public void setNomProduit(final String nomProduit) {
        this.nomProduit = nomProduit;
    }
}
