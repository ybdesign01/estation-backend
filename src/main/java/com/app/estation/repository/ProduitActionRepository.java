package com.app.estation.repository;

import com.app.estation.entity.ProduitAction;
import com.app.estation.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ProduitActionRepository extends JpaRepository<ProduitAction, Long> {

    @Query("select p from ProduitAction p where p.produit.id_service.station.id = ?1 order by p.date_action desc limit 6")
    public List<ProduitAction> findAllOrderByDate(Long id);

    @Query("select p from ProduitAction p where p.produit.id_produit = ?1 order by p.date_action desc")
    List<ProduitAction> getByProduitId(Long id);

    @Query("select p from ProduitAction p where p.date_action between ?1 and ?2 and p.action = 'ACTION_ENTREE' order by p.date_action desc")
    List<ProduitAction> findEntreesByStation(LocalDateTime dateDebut, LocalDateTime dateFin, Station station);
}
