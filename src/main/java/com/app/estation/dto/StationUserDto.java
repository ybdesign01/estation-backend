package com.app.estation.dto;

import com.app.estation.entity.Station;
import com.app.estation.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import java.util.Objects;

public class StationUserDto {
    Station station;
    User user;
    private String date_debut;
    private String date_fin;

     public StationUserDto() {
    }

    public StationUserDto(final Station station, final User user, final String date_debut, final String date_fin) {
        this.station = station;
        this.user = user;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
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

    public Station getStation() {
        return this.station;
    }

    public void setStation(final Station station) {
        this.station = station;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
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
