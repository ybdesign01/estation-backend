package com.app.estation.dto;

import java.time.LocalDateTime;

public class HistoriquePrixDto {

    private Long idHistoriquePrix;
    private Double prixAchat;
    private Double prixVente;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private ProduitDto idProduit;

    public HistoriquePrixDto() {
    }

    public HistoriquePrixDto(Long idHistoriquePrix, Double prixAchat, Double prixVente, LocalDateTime dateDebut, LocalDateTime dateFin, ProduitDto idProduit) {
        this.idHistoriquePrix = idHistoriquePrix;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idProduit = idProduit;
    }

    public Long getIdHistoriquePrix() {
        return this.idHistoriquePrix;
    }

    public void setIdHistoriquePrix(final Long idHistoriquePrix) {
        this.idHistoriquePrix = idHistoriquePrix;
    }

    public Double getPrixAchat() {
        return this.prixAchat;
    }

    public void setPrixAchat(final Double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public Double getPrixVente() {
        return this.prixVente;
    }

    public void setPrixVente(final Double prixVente) {
        this.prixVente = prixVente;
    }

    public LocalDateTime getDateDebut() {
        return this.dateDebut;
    }

    public void setDateDebut(final LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return this.dateFin;
    }

    public void setDateFin(final LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public ProduitDto getIdProduit() {
        return this.idProduit;
    }

    public void setIdProduit(final ProduitDto idProduit) {
        this.idProduit = idProduit;
    }
}
