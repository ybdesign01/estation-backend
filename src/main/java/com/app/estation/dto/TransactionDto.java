package com.app.estation.dto;

import com.app.estation.dto.User.PompeUserDto;
import com.app.estation.entity.TypePaiement;
import com.app.estation.entity.TypeTransaction;

import java.time.LocalDateTime;

public class TransactionDto {
    private Long idTransaction;
    private TypeTransaction typeTransaction;
    private TypePaiement typePaiement;
    private Double montant;
    private LocalDateTime dateTransaction;
    private PompeUserDto idPompeUser;
    private ProduitActionDto idProduitAction;

    private StationDto station;

    public TransactionDto(Long idTransaction, TypeTransaction typeTransaction, TypePaiement typePaiement, Double montant, LocalDateTime dateTransaction, PompeUserDto idPompeUser, ProduitActionDto idProduitAction, StationDto station) {
        this.idTransaction = idTransaction;
        this.typeTransaction = typeTransaction;
        this.typePaiement = typePaiement;
        this.montant = montant;
        this.dateTransaction = dateTransaction;
        this.idPompeUser = idPompeUser;
        this.idProduitAction = idProduitAction;
        this.station = station;
    }

    public StationDto getStation() {
        return this.station;
    }

    public void setStation(final StationDto station) {
        this.station = station;
    }

    public TransactionDto() {
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
}
