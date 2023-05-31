package com.app.estation.dto;

import com.app.estation.dto.User.PompeUserDto;

public class AffectationMontantDto {

    private PompeUserDto pompeUser;
    private Double montant;


    public AffectationMontantDto(PompeUserDto pompeUser, Double montant, Long quantite_carburant) {
        this.pompeUser = pompeUser;
        this.montant = montant;
    }

    public AffectationMontantDto() {
    }

    public PompeUserDto getPompeUser() {
        return this.pompeUser;
    }

    public void setPompeUser(final PompeUserDto pompeUser) {
        this.pompeUser = pompeUser;
    }

    public Double getMontant() {
        return this.montant;
    }

    public void setMontant(final Double montant) {
        this.montant = montant;
    }


}
