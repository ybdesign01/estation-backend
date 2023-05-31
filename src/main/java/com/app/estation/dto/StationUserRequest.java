package com.app.estation.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StationUserRequest {

    @NotNull(message = "station_mandatory")
    private Long idStation;

    @NotNull(message = "user_mandatory")
    private Long idUser;

    private LocalDateTime date_debut;

    private LocalDateTime date_fin;

    public StationUserRequest() {
    }

    public StationUserRequest(Long idStation, Long idUser, LocalDateTime date_debut, LocalDateTime date_fin) {
        this.idStation = idStation;
        this.idUser = idUser;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Long getIdStation() {
        return this.idStation;
    }

    public void setIdStation(final Long idStation) {
        this.idStation = idStation;
    }

    public Long getIdUser() {
        return this.idUser;
    }

    public void setIdUser(final Long idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getDate_debut() {
        return this.date_debut;
    }

    public void setDate_debut(final LocalDateTime date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDateTime getDate_fin() {
        return this.date_fin;
    }

    public void setDate_fin(final LocalDateTime date_fin) {
        this.date_fin = date_fin;
    }
}
