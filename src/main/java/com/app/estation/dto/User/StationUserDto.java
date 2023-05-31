package com.app.estation.dto.User;

import com.app.estation.dto.StationDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StationUserDto {
    @NotNull(message = "key_mandatory")
    private Long idStationUser;
    private StationDto station;
    private UserDto user;
    private LocalDateTime date_debut;
    private LocalDateTime date_fin;

     public StationUserDto() {

    }

    public StationUserDto(final Long idStationUser, final StationDto station, final UserDto user, final LocalDateTime date_debut, final LocalDateTime date_fin) {
         this.idStationUser = idStationUser;
         this.station = station;
         this.user = user;
         this.date_debut = date_debut;
         this.date_fin = date_fin;
    }

    public Long getIdStationUser() {
        return this.idStationUser;
    }

    public void setIdStationUser(final Long idStationUser) {
        this.idStationUser = idStationUser;
    }

    public StationDto getStation() {
        return this.station;
    }

    public void setStation(final StationDto station) {
        this.station = station;
    }

    public UserDto getUser() {
        return this.user;
    }

    public void setUser(final UserDto user) {
        this.user = user;
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
