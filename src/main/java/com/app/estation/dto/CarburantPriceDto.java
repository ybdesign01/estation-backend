package com.app.estation.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CarburantPriceDto {

    private String nomCarburant;

    private Double prixActuel;
    private List<Double> prixCarburant;
    private List<LocalDateTime> dates;
    private String percentChange;
    private boolean isIncrease;


    public CarburantPriceDto() {
    }

    public CarburantPriceDto(String nomCarburant, Double prixActuel, List<Double> prixCarburant, List<LocalDateTime> dates, String percentChange, boolean isIncrease) {
        this.nomCarburant = nomCarburant;
        this.prixActuel = prixActuel;
        this.prixCarburant = prixCarburant;
        this.dates = dates;
        this.percentChange = percentChange;
        this.isIncrease = isIncrease;
    }

    public String getNomCarburant() {
        return this.nomCarburant;
    }

    public void setNomCarburant(final String nomCarburant) {
        this.nomCarburant = nomCarburant;
    }

    public Double getPrixActuel() {
        return this.prixActuel;
    }

    public void setPrixActuel(final Double prixActuel) {
        this.prixActuel = prixActuel;
    }

    public List<Double> getPrixCarburant() {
        return this.prixCarburant;
    }

    public void setPrixCarburant(final List<Double> prixCarburant) {
        this.prixCarburant = prixCarburant;
    }

    public List<LocalDateTime> getDates() {
        return this.dates;
    }

    public void setDates(final List<LocalDateTime> dates) {
        this.dates = dates;
    }

    public String getPercentChange() {
        return this.percentChange;
    }

    public void setPercentChange(final String percentChange) {
        this.percentChange = percentChange;
    }

    public boolean isIncrease() {
        return !percentChange.contains("-");
    }

    public void setIncrease(final boolean increase) {
        this.isIncrease = !percentChange.contains("-");;
    }
}
