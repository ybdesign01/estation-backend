package com.app.estation.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class DashboardRequest {

    @NotNull(message = "dateDebut_mandatory")
    private LocalDateTime dateDebut;
    @NotNull(message = "dateFin_mandatory")
    private LocalDateTime dateFin;

    public DashboardRequest() {
    }

    public DashboardRequest( LocalDateTime dateDebut, LocalDateTime dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

}
