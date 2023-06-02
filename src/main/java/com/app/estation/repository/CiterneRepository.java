package com.app.estation.repository;

import com.app.estation.entity.Citerne;
import com.app.estation.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CiterneRepository extends JpaRepository<Citerne, Long> {

    @Query("SELECT c FROM Citerne c LEFT JOIN FETCH c.pompes cp " +
            "WHERE c.station = :station " +
            "AND cp.dateFin IS NULL " +
            "AND cp.dateDebut = (SELECT MAX(cp2.dateDebut) " +
            "                    FROM CiternePompe cp2 " +
            "                    WHERE cp2.citerne = c " +
            "                    AND cp2.dateFin IS NULL)")
    List<Citerne> findAllWithLatestPompesByStation(@Param("station") Station station);

    List<Citerne> findAllByStation(Station station);
}
