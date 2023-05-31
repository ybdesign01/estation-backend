package com.app.estation.repository;

import com.app.estation.entity.HistoriquePrix;
import com.app.estation.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface HistoriquePrixRepository extends JpaRepository<HistoriquePrix, Long> {

    Optional<List<HistoriquePrix>> findAllByIdProduit(Produit id_produit);

    @Query("select h from HistoriquePrix h where h.idProduit = ?1 and h.dateDebut = (select max(h1.dateDebut) from HistoriquePrix h1 where h1.idProduit = ?1)")
    Optional<HistoriquePrix> findByMaxDateDebut(Produit produit);
}
