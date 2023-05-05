package com.app.estation.entity.keys;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StationUserKey implements Serializable {

    @Column
    Long id_station;

    @Column
    Long id_user;

    public StationUserKey(Long idUser, Long id) {
        this.id_station = id;
        this.id_user = idUser;
    }

    public StationUserKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StationUserKey that)) return false;
        return Objects.equals(this.id_station, that.id_station) && Objects.equals(this.id_user, that.id_user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id_station, this.id_user);
    }

    public Long getId_station() {
        return id_station;
    }

    public void setId_station(Long id_station) {
        this.id_station = id_station;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "StationUserKey{" +
                "id_station=" + id_station +
                ", id_user=" + id_user +
                '}';
    }
}
