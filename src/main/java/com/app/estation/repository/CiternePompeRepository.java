package com.app.estation.repository;

import com.app.estation.entity.CiternePompe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CiternePompeRepository extends JpaRepository<CiternePompe, Long> {

    @Query("select c from CiternePompe c where c.pompe.id_pompe = ?1 and c.dateDebut = (select max(c.dateDebut) from CiternePompe c where c.pompe.id_pompe = ?1) and c.dateFin is null")
    CiternePompe findByIdPompe(Long idPompe);

}
