package com.app.estation.dto;

public class TypePaiementDto {

    private Long idTypePaiement;

    private String libelle;

    public TypePaiementDto() {
    }

    public TypePaiementDto(final Long idTypePaiement, final String libelle) {
        this.idTypePaiement = idTypePaiement;
        this.libelle = libelle;
    }

    public Long getIdTypePaiement() {
        return this.idTypePaiement;
    }

    public void setIdTypePaiement(final Long idTypePaiement) {
        this.idTypePaiement = idTypePaiement;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(final String libelle) {
        this.libelle = libelle;
    }


}
