package com.app.estation.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class TransactionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransactionGroup;

    @Enumerated(EnumType.STRING)
    private TypeTransaction typeTransaction;
    private Double montantTotal;
    private Double montantPaye;
    private Double montantRestant;

    private LocalDateTime dateTransaction;

    @ManyToOne
    private PompeUser idPompeUser;
    @ManyToOne
    private ProduitAction idProduitAction;
    @ManyToOne
    @JoinColumn(name = "idStation")
    private Station station;
    private String note;

    @OneToMany(mappedBy = "idTransactionGroup")
    private List<Transaction> transactions;


    public TransactionGroup() {
    }

    public TransactionGroup(final Long idTransactionGroup, final TypeTransaction typeTransaction, final Double montantTotal, final Double montantPaye, final Double montantRestant, final LocalDateTime dateTransaction, final PompeUser idPompeUser, final ProduitAction idProduitAction, final Station station, final String note, final List<Transaction> transactions) {
        this.idTransactionGroup = idTransactionGroup;
        this.typeTransaction = typeTransaction;
        this.montantTotal = montantTotal;
        this.montantPaye = montantPaye;
        this.montantRestant = montantRestant;
        this.dateTransaction = dateTransaction;
        this.idPompeUser = idPompeUser;
        this.idProduitAction = idProduitAction;
        this.station = station;
        this.note = note;
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public LocalDateTime getDateTransaction() {
        return this.dateTransaction;
    }

    public void setDateTransaction(final LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public void setTransactions(final List<Transaction> transactions) {
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

    public String getNote() {
        return this.note;
    }

    public void setNote(final String note) {
        this.note = note;
    }
}
