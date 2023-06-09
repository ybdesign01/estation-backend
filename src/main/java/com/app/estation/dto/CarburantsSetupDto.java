package com.app.estation.dto;

import java.util.List;

public class CarburantsSetupDto {

    List<CarburantSetupDto> carburants;

    public List<CarburantSetupDto> getCarburants() {
        return this.carburants;
    }

    public void setCarburants(final List<CarburantSetupDto> carburants) {
        this.carburants = carburants;
    }
}
