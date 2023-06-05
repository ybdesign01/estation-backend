package com.app.estation.repository;

import com.app.estation.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    /*List<Transaction> findAllByIdPompeUser(PompeUser idPompeUser);
    @Query("SELECT t FROM Transaction t WHERE t.idPompeUser.idPompeUser IN :pompeUserIds")
    List<Transaction> findTransactionsByExcludedPompeUserIds(@Param("pompeUserIds") List<Long> pompeUserIds);

    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.idPompeUser.idPompeUser IN :pompeUserIds")
    Long findTransactionsByIdPompeUsers(@Param("pompeUserIds") List<Long> pompeUserIds);
    @Query("SELECT SUM(t.montant) FROM Transaction t " +
            "WHERE t.typeTransaction = 'ENCAISSEMENT' " +
            "AND DATE(t.dateTransaction) = :today " +
             "AND t.station.id = :idStation")
    Double calculateTotalMontantByTypeAndDate(@Param("today") LocalDate today, @Param("idStation") Long idStation);


    @Query("select count (t) from Transaction t where t.idProduitAction.produit.id_produit IN :idProduit")
    Long findTransactionsByIdProduits(List<Long> idProduit);*/
}
