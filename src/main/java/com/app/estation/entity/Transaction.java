package com.app.estation.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaction;
    @ManyToOne
    private TypePaiement typePaiement;
    private Double montant;
    private LocalDateTime dateTransaction;
    @ManyToOne
    private TransactionGroup idTransactionGroup;

    public Transaction() {
    }

    public Transaction(final Long idTransaction, final TypePaiement typePaiement, final Double montant, final LocalDateTime dateTransaction, final TransactionGroup idTransactionGroup) {
        this.idTransaction = idTransaction;
        this.typePaiement = typePaiement;
        this.montant = montant;
        this.dateTransaction = dateTransaction;
        this.idTransactionGroup = idTransactionGroup;
    }

    public Long getIdTransaction() {
        return this.idTransaction;
    }

    public void setIdTransaction(final Long idTransaction) {
        this.idTransaction = idTransaction;
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

    public TransactionGroup getIdTransactionGroup() {
        return this.idTransactionGroup;
    }

    public void setIdTransactionGroup(final TransactionGroup idTransactionGroup) {
        this.idTransactionGroup = idTransactionGroup;
    }
}
