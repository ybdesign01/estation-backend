package com.app.estation.entity;

import com.app.estation.entity.keys.StationUserKey;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class StationUser {

    @EmbeddedId
    private StationUserKey stationUserKey;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id_station")
    Station station;

    @ManyToOne
    @MapsId("id_user")
    @JoinColumn(name = "id_user")
    User user;

    private String date_debut;
    private String date_fin;


    public StationUser() {
    }

    public StationUser(Station station, User user, String date_debut, String date_fin) {
        this.station = station;
        this.user = user;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }



    public StationUserKey getStationUserKey() {
        return stationUserKey;
    }

    public void setStationUserKey(StationUserKey stationUserKey) {
        this.stationUserKey = stationUserKey;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof StationUser that)) return false;
        return Objects.equals(stationUserKey, that.stationUserKey) && Objects.equals(station, that.station) && Objects.equals(user, that.user) && Objects.equals(date_debut, that.date_debut) && Objects.equals(date_fin, that.date_fin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationUserKey, station, user, date_debut, date_fin);
    }

    @Override
    public String toString() {
        return "StationUser{" +
                "stationUserKey=" + stationUserKey +
                ", station=" + station.getId() +
                ", user=" + user.getId_user() +
                ", date_debut='" + date_debut + '\'' +
                ", date_fin='" + date_fin + '\'' +
                '}';
    }
}
