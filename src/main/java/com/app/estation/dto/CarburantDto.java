package com.app.estation.dto;

public class CarburantDto {

    private String nomCarburant;
    private Double prixCarburant;

    private String percentChange;

    private boolean isIncrease;


    public CarburantDto() {
    }

    public CarburantDto(String nomCarburant, Double prixCarburant, String percentChange) {
        this.nomCarburant = nomCarburant;
        this.prixCarburant = prixCarburant;
        this.percentChange = percentChange;
        this.isIncrease = !percentChange.contains("-");
    }

    public String getNomCarburant() {
        return nomCarburant;
    }

    public void setNomCarburant(String nomCarburant) {
        this.nomCarburant = nomCarburant;
    }

    public Double getPrixCarburant() {
        return prixCarburant;
    }

    public void setPrixCarburant(Double prixCarburant) {
        this.prixCarburant = prixCarburant;
    }

    public String getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(String percentChange) {
        this.percentChange = percentChange;
    }

    public boolean isIncrease() {
        return !percentChange.contains("-");
    }

    public void setIncrease(boolean increase) {
        isIncrease = !percentChange.contains("-");
    }

}
