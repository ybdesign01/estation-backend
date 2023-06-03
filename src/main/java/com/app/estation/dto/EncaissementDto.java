package com.app.estation.dto;

import com.app.estation.entity.TypePaiement;
import jakarta.validation.constraints.NotNull;

public class EncaissementDto {

    @NotNull(message = "type_paiement_mandatory")
    private TypePaiement typePaiement;
    @NotNull(message = "montant_mandatory")
    private Double montant;


    public TypePaiement getTypePaiement() {
        return this.typePaiement;
    }

    public void setTypePaiement(final TypePaiement typePaiement) {
        this.typePaiement = typePaiement;
    }

    public Double getMontant() {
        return this.montant;
    }

    public void setMontant(final Double montant) {
        this.montant = montant;
    }

}
