package com.app.estation.repository;

import com.app.estation.entity.PompeUser;
import com.app.estation.entity.Transaction;
import com.app.estation.entity.TransactionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface TransactionGroupRepository extends JpaRepository<TransactionGroup, Long> {
    List<TransactionGroup> findAllByIdPompeUser(PompeUser idPompeUser);

    @Query("SELECT sum(t.montantRestant) FROM TransactionGroup t WHERE t.idPompeUser.user.id_user = :idUser AND t.typeTransaction = 'ENCAISSEMENT'")
    Double sumMontantPayeByUser(Long idUser);
    @Query("SELECT t FROM TransactionGroup t WHERE t.idPompeUser.idPompeUser IN :pompeUserIds")
    List<Transaction> findTransactionsByExcludedPompeUserIds(@Param("pompeUserIds") List<Long> pompeUserIds);

    @Query("SELECT COUNT(t) FROM TransactionGroup t WHERE t.idPompeUser.idPompeUser IN :pompeUserIds")
    Long findTransactionsByIdPompeUsers(@Param("pompeUserIds") List<Long> pompeUserIds);

    @Query("SELECT SUM(t.montantPaye) FROM TransactionGroup t " +
            "WHERE t.typeTransaction = 'ENCAISSEMENT' " +
            "AND DATE(t.dateTransaction) = :today " +
            "AND t.station.id = :idStation")
    Double calculateTotalMontantByTypeAndDate(@Param("today") LocalDate today, @Param("idStation") Long idStation);

    @Query("select count (t) from TransactionGroup t where t.idProduitAction.produit.id_produit IN :idProduit")
    Long findTransactionsByIdProduits(List<Long> idProduit);

    @Query("select count (t) from TransactionGroup t where t.station.id = ?1")
    List<TransactionGroup> findTransactionGroupsByStation(Long id);
}
