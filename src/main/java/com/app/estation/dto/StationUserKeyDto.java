package com.app.estation.dto;

public class StationUserKeyDto {

    Long id_station;
    Long id_user;

    public StationUserKeyDto(Long id_station, Long id_user) {
        this.id_station = id_station;
        this.id_user = id_user;
    }

    public StationUserKeyDto() {
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

    public void setId_user(Long id) {
        this.id_user = id;
    }
}
