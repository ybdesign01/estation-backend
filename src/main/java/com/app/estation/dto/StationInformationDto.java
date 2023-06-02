package com.app.estation.dto;

import java.util.List;

public class StationInformationDto {

    private List<CarburantDto> carburant;

    private Double chiffreToday;

    private List<CiterneJaugageDto> citerneJaugage;


    public StationInformationDto() {
    }

    public StationInformationDto(final List<CarburantDto> carburant, final Double chiffreToday, final List<CiterneJaugageDto> citerneJaugage) {
        this.carburant = carburant;
        this.chiffreToday = chiffreToday;
        this.citerneJaugage = citerneJaugage;
    }

    public List<CarburantDto> getCarburant() {
        return this.carburant;
    }

    public void setCarburant(final List<CarburantDto> carburant) {
        this.carburant = carburant;
    }

    public Double getChiffreToday() {
        return this.chiffreToday;
    }

    public void setChiffreToday(final Double chiffreToday) {
        this.chiffreToday = chiffreToday;
    }

    public List<CiterneJaugageDto> getCiterneJaugage() {
        return this.citerneJaugage;
    }

    public void setCiterneJaugage(final List<CiterneJaugageDto> citerneJaugage) {
        this.citerneJaugage = citerneJaugage;
    }
}
