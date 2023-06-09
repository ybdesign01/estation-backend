package com.app.estation.repository;

import com.app.estation.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Query("select t from TransactionGroup t where t.typeTransaction = ?1")
    List<TransactionGroup> findTransactionGroupsByTypeTransaction(TypeTransaction typeTransaction);

    @Query("select t from TransactionGroup t where t.dateTransaction between ?1 and ?2 and t.station = ?3")
    List<TransactionGroup> findAllByDateAndStation(LocalDateTime dateDebut, LocalDateTime dateFin, Station station);

    @Query("SELECT SUM(CASE WHEN t.typeTransaction = 'ENCAISSEMENT' THEN t.montantPaye ELSE -t.montantTotal END) " +
            "FROM TransactionGroup t where t.station = ?1")
    Double amountByStation(Station station);
}
