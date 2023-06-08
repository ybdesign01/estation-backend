package com.app.estation.dto;

import java.util.List;

public class DashboardInfoDto {

    private List<CarburantPriceDto> carburants;
    private List<CiterneJaugageDto> citerneJaugage;
    private List<ActionPriceDto> actions;

    private Double entree;
    private Double sortie;
    private List<ActionDateDto> entrees;
    private List<ActionDateDto> sorties;

    public DashboardInfoDto() {
    }

    public DashboardInfoDto(List<CarburantPriceDto> carburants, List<CiterneJaugageDto> citerneJaugage, List<ActionPriceDto> actions, List<ActionDateDto> entrees, List<ActionDateDto> sorties) {
        this.carburants = carburants;
        this.citerneJaugage = citerneJaugage;
        this.actions = actions;
        this.entrees = entrees;
        this.sorties = sorties;
    }

    public Double getEntree() {
        return this.entree;
    }

    public void setEntree(final Double entree) {
        this.entree = entree;
    }

    public Double getSortie() {
        return this.sortie;
    }

    public void setSortie(final Double sortie) {
        this.sortie = sortie;
    }

    public List<CarburantPriceDto> getCarburants() {
        return this.carburants;
    }

    public void setCarburants(final List<CarburantPriceDto> carburants) {
        this.carburants = carburants;
    }

    public List<CiterneJaugageDto> getCiterneJaugage() {
        return this.citerneJaugage;
    }

    public void setCiterneJaugage(final List<CiterneJaugageDto> citerneJaugage) {
        this.citerneJaugage = citerneJaugage;
    }

    public List<ActionPriceDto> getActions() {
        return this.actions;
    }

    public void setActions(final List<ActionPriceDto> actions) {
        this.actions = actions;
    }

    public List<ActionDateDto> getEntrees() {
        return this.entrees;
    }

    public void setEntrees(final List<ActionDateDto> entrees) {
        this.entrees = entrees;
    }

    public List<ActionDateDto> getSorties() {
        return this.sorties;
    }

    public void setSorties(final List<ActionDateDto> sorties) {
        this.sorties = sorties;
    }
}
