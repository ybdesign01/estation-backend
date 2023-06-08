package com.app.estation.repository;

import com.app.estation.entity.ProduitAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProduitActionRepository extends JpaRepository<ProduitAction, Long> {

    @Query("select p from ProduitAction p where p.produit.id_service.station.id = ?1 order by p.date_action desc limit 6")
    public List<ProduitAction> findAllOrderByDate(Long id);

}
