package com.app.estation.mappers;

import com.app.estation.dto.TransactionDto;
import com.app.estation.entity.Transaction;

import java.util.List;

public class TransactionMapper {

    public static Transaction toEntity(TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        transaction.setIdTransaction(transactionDto.getIdTransaction());
        transaction.setMontant(transactionDto.getMontant());
        transaction.setDateTransaction(transactionDto.getDateTransaction());
        transaction.setTypePaiement(transactionDto.getTypePaiement());
        transaction.setTypeTransaction(transactionDto.getTypeTransaction());
        transaction.setIdPompeUser(PompeUserMapper.toEntity(transactionDto.getIdPompeUser()));
        transaction.setStation(StationMapper.toEntity(transactionDto.getStation()));
        transaction.setNote(transactionDto.getNote());
        transaction.setIdProduitAction(ProduitActionMapper.toEntity(transactionDto.getIdProduitAction()));
        transaction.setIdTransaction(transactionDto.getIdTransaction());
        return transaction;
    }

    public static TransactionDto fromEntity(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setIdTransaction(transaction.getIdTransaction());
        transactionDto.setMontant(transaction.getMontant());
        transactionDto.setDateTransaction(transaction.getDateTransaction());
        transactionDto.setTypePaiement(transaction.getTypePaiement());
        transactionDto.setTypeTransaction(transaction.getTypeTransaction());
        transactionDto.setIdPompeUser(PompeUserMapper.fromEntity(transaction.getIdPompeUser()));
        transactionDto.setStation(StationMapper.fromEntityWithoutServices(transaction.getStation()));
        transactionDto.setNote(transaction.getNote());
        transactionDto.setIdProduitAction(ProduitActionMapper.fromEntity(transaction.getIdProduitAction()));

        return transactionDto;
    }

    public static List<TransactionDto> fromEntityList(List<Transaction> transactions){
        return transactions.stream().map(TransactionMapper::fromEntity).collect(java.util.stream.Collectors.toList());
    }

    public static List<Transaction> toEntityList(List<TransactionDto> transactionDtos){
        return transactionDtos.stream().map(TransactionMapper::toEntity).collect(java.util.stream.Collectors.toList());
    }
}
