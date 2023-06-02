package com.app.estation.repository;

import com.app.estation.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    @Query("select p from Produit p where p.id_service.id = ?1")
    Optional<List<Produit>> findAllByStationAndType(Long id_service);

    @Query("select p from Produit p where p.id_service.station.id = ?1")
    List<Produit> findByStation(Long id);
}
