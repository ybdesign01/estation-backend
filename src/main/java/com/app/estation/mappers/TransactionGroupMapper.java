package com.app.estation.mappers;

import com.app.estation.dto.TransactionGroupDto;
import com.app.estation.entity.TransactionGroup;

import java.util.List;

public class TransactionGroupMapper {

    public static TransactionGroup toEntity(TransactionGroupDto transactionGroupDto){
        TransactionGroup transactionGroup = new TransactionGroup();
        transactionGroup.setIdTransactionGroup(transactionGroupDto.getIdTransactionGroup());
        transactionGroup.setTypeTransaction(transactionGroupDto.getTypeTransaction());
        transactionGroup.setMontantTotal(transactionGroupDto.getMontantTotal());
        transactionGroup.setMontantPaye(transactionGroupDto.getMontantPaye());
        transactionGroup.setMontantRestant(transactionGroupDto.getMontantRestant());
        transactionGroup.setIdPompeUser(PompeUserMapper.toEntity(transactionGroupDto.getIdPompeUser()));
        transactionGroup.setIdProduitAction(ProduitActionMapper.toEntity(transactionGroupDto.getIdProduitAction()));
        transactionGroup.setStation(StationMapper.toEntityWithoutSubclasses(transactionGroupDto.getStation()));
        return transactionGroup;
    }

    public static TransactionGroup toEntityWithoutSubclasses(TransactionGroupDto transactionGroupDto){
        TransactionGroup transactionGroup = new TransactionGroup();
        transactionGroup.setIdTransactionGroup(transactionGroupDto.getIdTransactionGroup());
        transactionGroup.setTypeTransaction(transactionGroupDto.getTypeTransaction());
        transactionGroup.setMontantTotal(transactionGroupDto.getMontantTotal());
        transactionGroup.setMontantPaye(transactionGroupDto.getMontantPaye());
        transactionGroup.setMontantRestant(transactionGroupDto.getMontantRestant());
        transactionGroup.setIdPompeUser(PompeUserMapper.toEntity(transactionGroupDto.getIdPompeUser()));
        transactionGroup.setIdProduitAction(ProduitActionMapper.toEntity(transactionGroupDto.getIdProduitAction()));
        transactionGroup.setStation(StationMapper.toEntityWithoutSubclasses(transactionGroupDto.getStation()));
        return transactionGroup;
    }

    public static TransactionGroupDto fromEntityWithoutSubclasses(TransactionGroup transactionGroup){
        TransactionGroupDto transactionGroupDto = new TransactionGroupDto();
        transactionGroupDto.setIdTransactionGroup(transactionGroup.getIdTransactionGroup());
        transactionGroupDto.setTypeTransaction(transactionGroup.getTypeTransaction());
        transactionGroupDto.setMontantTotal(transactionGroup.getMontantTotal());
        transactionGroupDto.setMontantPaye(transactionGroup.getMontantPaye());
        transactionGroupDto.setMontantRestant(transactionGroup.getMontantRestant());
        transactionGroupDto.setIdPompeUser(PompeUserMapper.fromEntity(transactionGroup.getIdPompeUser()));
        transactionGroupDto.setIdProduitAction(ProduitActionMapper.fromEntity(transactionGroup.getIdProduitAction()));
        transactionGroupDto.setStation(StationMapper.fromEntityWithoutServices(transactionGroup.getStation()));
        return transactionGroupDto;
    }

    public static TransactionGroupDto fromEntity(TransactionGroup transactionGroup){
        TransactionGroupDto transactionGroupDto = new TransactionGroupDto();
        transactionGroupDto.setIdTransactionGroup(transactionGroup.getIdTransactionGroup());
        transactionGroupDto.setTypeTransaction(transactionGroup.getTypeTransaction());
        transactionGroupDto.setMontantTotal(transactionGroup.getMontantTotal());
        transactionGroupDto.setMontantPaye(transactionGroup.getMontantPaye());
        transactionGroupDto.setMontantRestant(transactionGroup.getMontantRestant());
        transactionGroupDto.setIdPompeUser(PompeUserMapper.fromEntity(transactionGroup.getIdPompeUser()));
        transactionGroupDto.setIdProduitAction(ProduitActionMapper.fromEntity(transactionGroup.getIdProduitAction()));
        transactionGroupDto.setStation(StationMapper.fromEntityWithoutServices(transactionGroup.getStation()));
        transactionGroupDto.setTransactions(TransactionMapper.fromEntityListWithoutSubClasses(transactionGroup.getTransactions()));
        return transactionGroupDto;
    }

    public static List<TransactionGroupDto> fromEntityList(List<TransactionGroup> transactionGroups){
        return transactionGroups.stream().map(TransactionGroupMapper::fromEntity).collect(java.util.stream.Collectors.toList());
    }

    public static List<TransactionGroup> toEntityList(List<TransactionGroupDto> transactionGroupDtos){
        return transactionGroupDtos.stream().map(TransactionGroupMapper::toEntity).collect(java.util.stream.Collectors.toList());
    }
}
