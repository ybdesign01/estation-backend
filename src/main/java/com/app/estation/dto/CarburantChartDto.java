package com.app.estation.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class CarburantChartDto {

    private String nomCarburant;
    private Map<LocalDateTime, Double> prixCarburant;

    public CarburantChartDto() {
    }

    public CarburantChartDto(String nomCarburant, Map<LocalDateTime, Double> prixCarburant) {
        this.nomCarburant = nomCarburant;
        this.prixCarburant = prixCarburant;
    }

    public String getNomCarburant() {
        return this.nomCarburant;
    }

    public void setNomCarburant(final String nomCarburant) {
        this.nomCarburant = nomCarburant;
    }

    public Map<LocalDateTime, Double> getPrixCarburant() {
        return this.prixCarburant;
    }

    public void setPrixCarburant(final Map<LocalDateTime, Double> prixCarburant) {
        this.prixCarburant = prixCarburant;
    }
}
