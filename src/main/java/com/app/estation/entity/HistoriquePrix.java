package com.app.estation.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class HistoriquePrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistoriquePrix;
    private Double prixAchat;
    private Double prixVente;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    @ManyToOne
    private Produit idProduit;


    public HistoriquePrix(Long idHistoriquePrix, Double prixAchat, Double prixVente, LocalDateTime dateDebut, LocalDateTime dateFin, Produit idProduit) {
        this.idHistoriquePrix = idHistoriquePrix;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idProduit = idProduit;
    }

    public HistoriquePrix() {
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

    public Produit getIdProduit() {
        return this.idProduit;
    }

    public void setIdProduit(final Produit idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public String toString() {
        return "HistoriquePrix{" +
                "idHistoriquePrix=" + idHistoriquePrix +
                ", prixAchat=" + prixAchat +
                ", prixVente=" + prixVente +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", idProduit=" + idProduit.getId_produit() +
                '}';
    }
}
