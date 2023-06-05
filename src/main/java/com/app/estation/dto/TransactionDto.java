package com.app.estation.dto;

import com.app.estation.entity.TypePaiement;

import java.time.LocalDateTime;

public class TransactionDto {
    private Long idTransaction;
    private TypePaiement typePaiement;
    private Double montant;
    private LocalDateTime dateTransaction;

    private TransactionGroupDto idTransactionGroup;

    public TransactionDto() {
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

    public TransactionGroupDto getIdTransactionGroup() {
        return this.idTransactionGroup;
    }

    public void setIdTransactionGroup(final TransactionGroupDto idTransactionGroup) {
        this.idTransactionGroup = idTransactionGroup;
    }


}
