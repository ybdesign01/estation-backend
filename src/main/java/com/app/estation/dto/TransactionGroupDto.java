package com.app.estation.dto;

import com.app.estation.dto.User.PompeUserDto;
import com.app.estation.entity.TypeTransaction;

import java.util.List;

public class TransactionGroupDto {
    private Long idTransactionGroup;
    private TypeTransaction typeTransaction;
    private Double montantTotal;
    private Double montantPaye;
    private Double montantRestant;
    private PompeUserDto idPompeUser;
    private ProduitActionDto idProduitAction;
    private StationDto station;
    private String note;

    private List<TransactionDto> transactions;

    public TransactionGroupDto() {
    }

    public TransactionGroupDto(final Long idTransactionGroup, final TypeTransaction typeTransaction, final Double montantTotal, final Double montantPaye, final Double montantRestant, final PompeUserDto idPompeUser, final ProduitActionDto idProduitAction, final StationDto station, final String note, final List<TransactionDto> transactions) {
        this.idTransactionGroup = idTransactionGroup;
        this.typeTransaction = typeTransaction;
        this.montantTotal = montantTotal;
        this.montantPaye = montantPaye;
        this.montantRestant = montantRestant;
        this.idPompeUser = idPompeUser;
        this.idProduitAction = idProduitAction;
        this.station = station;
        this.note = note;
        this.transactions = transactions;
    }

    public Long getIdTransactionGroup() {
        return this.idTransactionGroup;
    }

    public void setIdTransactionGroup(final Long idTransactionGroup) {
        this.idTransactionGroup = idTransactionGroup;
    }

    public TypeTransaction getTypeTransaction() {
        return this.typeTransaction;
    }

    public void setTypeTransaction(final TypeTransaction typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public Double getMontantTotal() {
        return this.montantTotal;
    }

    public void setMontantTotal(final Double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Double getMontantPaye() {
        return this.montantPaye;
    }

    public void setMontantPaye(final Double montantPaye) {
        this.montantPaye = montantPaye;
    }

    public Double getMontantRestant() {
        return this.montantRestant;
    }

    public void setMontantRestant(final Double montantRestant) {
        this.montantRestant = montantRestant;
    }

    public PompeUserDto getIdPompeUser() {
        return this.idPompeUser;
    }

    public void setIdPompeUser(final PompeUserDto idPompeUser) {
        this.idPompeUser = idPompeUser;
    }

    public ProduitActionDto getIdProduitAction() {
        return this.idProduitAction;
    }

    public void setIdProduitAction(final ProduitActionDto idProduitAction) {
        this.idProduitAction = idProduitAction;
    }

    public StationDto getStation() {
        return this.station;
    }

    public void setStation(final StationDto station) {
        this.station = station;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(final String note) {
        this.note = note;
    }

    public List<TransactionDto> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(final List<TransactionDto> transactions) {
        this.transactions = transactions;
    }
}
