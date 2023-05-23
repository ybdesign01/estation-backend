package com.app.estation.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class PompeUserRequest {

    @NotNull(message = "id_pompe_mandatory")
    private Long idPompe;
    @NotNull(message = "id_user_mandatory")
    private Long idUser;
    @NotNull(message = "date_debut_mandatory")
    private LocalDateTime dateDebut;
    @NotNull(message = "date_fin_mandatory")
    private LocalDateTime dateFin;

    public PompeUserRequest() {
    }

    public PompeUserRequest(Long idPompe, Long idUser, LocalDateTime dateDebut, LocalDateTime dateFin) {
        this.idPompe = idPompe;
        this.idUser = idUser;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Long getIdPompe() {
        return idPompe;
    }

    public void setIdPompe(Long idPompe) {
        this.idPompe = idPompe;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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
