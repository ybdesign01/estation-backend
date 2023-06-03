package com.app.estation.dto;

import java.util.List;

public class EncaissementProduitRequest {

    private Long idProduit;

    private Long quantite;

    private String note;

    private List<EncaissementDto> encaissements;

    public EncaissementProduitRequest() {
    }

    public EncaissementProduitRequest(final Long idProduit, final Long quantite, final String note, final List<EncaissementDto> encaissements) {
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.note = note;
        this.encaissements = encaissements;
    }

    public Long getIdProduit() {
        return this.idProduit;
    }

    public void setIdProduit(final Long idProduit) {
        this.idProduit = idProduit;
    }

    public Long getQuantite() {
        return this.quantite;
    }

    public void setQuantite(final Long quantite) {
        this.quantite = quantite;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(final String note) {
        this.note = note;
    }

    public List<EncaissementDto> getEncaissements() {
        return this.encaissements;
    }

    public void setEncaissements(final List<EncaissementDto> encaissements) {
        this.encaissements = encaissements;
    }
}
