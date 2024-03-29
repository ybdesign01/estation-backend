package com.app.estation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TypePaiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypePaiement;

    private String libelle;

    public TypePaiement() {
    }

    public TypePaiement(final Long idTypePaiement, final String libelle) {
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
