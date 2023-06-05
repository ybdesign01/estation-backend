package com.app.estation.dto;

import jakarta.validation.constraints.NotNull;

public class EncaissementDto {

    @NotNull(message = "type_paiement_mandatory")
    private TypePaiementDto typePaiement;
    @NotNull(message = "montant_mandatory")
    private Double montant;


    public TypePaiementDto getTypePaiement() {
        return this.typePaiement;
    }

    public void setTypePaiement(final TypePaiementDto typePaiement) {
        this.typePaiement = typePaiement;
    }

    public Double getMontant() {
        return this.montant;
    }

    public void setMontant(final Double montant) {
        this.montant = montant;
    }

}
