package com.app.estation.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaction;
    @Enumerated(EnumType.STRING)
    private TypeTransaction typeTransaction;
    @Enumerated(EnumType.STRING)
    private TypePaiement typePaiement;
    private Double montant;
    private LocalDateTime dateTransaction;

    private String note;
    @ManyToOne
    private PompeUser idPompeUser;
    @ManyToOne
    private ProduitAction idProduitAction;
    @ManyToOne
    @JoinColumn(name = "idStation")
    private Station station;

    public Transaction(final Long idTransaction, final TypeTransaction typeTransaction, final TypePaiement typePaiement, final Double montant, final LocalDateTime dateTransaction, final PompeUser idPompeUser, final ProduitAction idProduitAction, final Station station) {
        this.idTransaction = idTransaction;
        this.typeTransaction = typeTransaction;
        this.typePaiement = typePaiement;
        this.montant = montant;
        this.dateTransaction = dateTransaction;
        this.idPompeUser = idPompeUser;
        this.idProduitAction = idProduitAction;
        this.station = station;
    }


    public Transaction() {
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(final String note) {
        this.note = note;
    }

    public Long getIdTransaction() {
        return this.idTransaction;
    }

    public void setIdTransaction(final Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public TypeTransaction getTypeTransaction() {
        return this.typeTransaction;
    }

    public void setTypeTransaction(final TypeTransaction typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

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

    public LocalDateTime getDateTransaction() {
        return this.dateTransaction;
    }

    public void setDateTransaction(final LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public PompeUser getIdPompeUser() {
        return this.idPompeUser;
    }

    public void setIdPompeUser(final PompeUser idPompeUser) {
        this.idPompeUser = idPompeUser;
    }

    public ProduitAction getIdProduitAction() {
        return this.idProduitAction;
    }

    public void setIdProduitAction(final ProduitAction idProduitAction) {
        this.idProduitAction = idProduitAction;
    }

    public Station getStation() {
        return this.station;
    }

    public void setStation(final Station station) {
        this.station = station;
    }
}
