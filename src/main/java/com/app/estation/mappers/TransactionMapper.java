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
        transaction.setIdTransactionGroup(TransactionGroupMapper.toEntityWithoutSubclasses(transactionDto.getIdTransactionGroup()));
        return transaction;
    }

    public static TransactionDto fromEntity(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setIdTransaction(transaction.getIdTransaction());
        transactionDto.setMontant(transaction.getMontant());
        transactionDto.setDateTransaction(transaction.getDateTransaction());
        transactionDto.setTypePaiement(transaction.getTypePaiement());
        transactionDto.setIdTransactionGroup(TransactionGroupMapper.fromEntityWithoutSubclasses(transaction.getIdTransactionGroup()));
        return transactionDto;
    }

    public static TransactionDto fromEntityWithoutSubclasses(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setIdTransaction(transaction.getIdTransaction());
        transactionDto.setMontant(transaction.getMontant());
        transactionDto.setDateTransaction(transaction.getDateTransaction());
        transactionDto.setTypePaiement(transaction.getTypePaiement());
        return transactionDto;
    }

    public static List<TransactionDto> fromEntityList(List<Transaction> transactions){
        return transactions.stream().map(TransactionMapper::fromEntity).collect(java.util.stream.Collectors.toList());
    }

    public static List<TransactionDto> fromEntityListWithoutSubClasses(List<Transaction> transactions){
        return transactions.stream().map(TransactionMapper::fromEntityWithoutSubclasses).collect(java.util.stream.Collectors.toList());
    }



    public static List<Transaction> toEntityList(List<TransactionDto> transactionDtos){
        return transactionDtos.stream().map(TransactionMapper::toEntity).collect(java.util.stream.Collectors.toList());
    }
}
