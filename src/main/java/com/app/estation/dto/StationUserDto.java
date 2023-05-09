package com.app.estation.dto;

import com.app.estation.entity.Station;
import com.app.estation.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StationUserDto {
    @NotNull(message = "key_mandatory")
    private StationUserKeyDto stationUserKey;
    private StationDto station;
    private UserDto user;
    private String date_debut;
    private String date_fin;

     public StationUserDto() {

    }

    public StationUserDto(final StationUserKeyDto key, final StationDto station, final UserDto user, final String date_debut, final String date_fin) {
         this.stationUserKey = key;
         this.station = station;
         this.user = user;
         this.date_debut = Date.from(new Date().toInstant()).toString();
         this.date_fin = date_fin;
    }

    public StationUserKeyDto getStationUserKey() {
        return this.stationUserKey;
    }

    public void setStationUserKey(final StationUserKeyDto stationUserKey) {
        this.stationUserKey = stationUserKey;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof StationUserDto that)) return false;
        return Objects.equals(station, that.station) && Objects.equals(user, that.user) && Objects.equals(date_debut, that.date_debut) && Objects.equals(date_fin, that.date_fin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(station, user, date_debut, date_fin);
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

    public String getDate_debut() {
        return this.date_debut;
    }

    public void setDate_debut(final String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return this.date_fin;
    }

    public void setDate_fin(final String date_fin) {
        this.date_fin = date_fin;
    }
}
