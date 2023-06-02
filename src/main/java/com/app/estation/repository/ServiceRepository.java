package com.app.estation.repository;

import com.app.estation.entity.Produit;
import com.app.estation.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Services, Long> {

    Optional<List<Services>> findServicesByStationId(Long id);

    @Query("select s from Services s where s.nomService = ?1 and s.station.id = ?2")
    Optional<Services> findByNom(String nom_service, Long id_station);

    boolean existsByNomServiceAndStationId(String nom_service, Long id_station);

    @Query("select s.produits from Services s where s.nomService = ?1 and s.station.id = ?2")
    Optional<List<Produit>> findProductsByNomService(String nom_service, Long id_station);
}
