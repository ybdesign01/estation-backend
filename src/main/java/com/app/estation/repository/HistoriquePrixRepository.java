package com.app.estation.repository;

import com.app.estation.entity.HistoriquePrix;
import com.app.estation.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface HistoriquePrixRepository extends JpaRepository<HistoriquePrix, Long> {

    @Query("select h from HistoriquePrix h where MONTH(h.dateDebut) = MONTH(CURRENT DATE) and h.idProduit = ?1")
    Optional<List<HistoriquePrix>> getPrixVentesInMonth(Long idProduit);




    Optional<List<HistoriquePrix>> findAllByIdProduit(Produit id_produit);

    @Query("select h from HistoriquePrix h where h.idProduit = ?1 and h.dateDebut = (select max(h1.dateDebut) from HistoriquePrix h1 where h1.idProduit = ?1)")
    Optional<HistoriquePrix> findByMaxDateDebut(Produit produit);

    @Query("SELECT h.prixVente FROM HistoriquePrix h WHERE h.idProduit.id_produit = :idProduit AND h.dateFin IS NOT NULL ORDER BY h.dateFin DESC LIMIT 1")
    List<Double> findPreviousPrixVente(@Param("idProduit") Long idProduit);


    @Query("SELECT h FROM HistoriquePrix h WHERE h.idProduit = :idProduit AND h.dateDebut between :dateDebut AND :dateFin")
    List<HistoriquePrix> findAllByIdProduitAndDateDebut(Produit idProduit, LocalDateTime dateDebut, LocalDateTime dateFin);


}
