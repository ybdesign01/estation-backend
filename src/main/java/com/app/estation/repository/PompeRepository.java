package com.app.estation.repository;

import com.app.estation.entity.Pompe;
import com.app.estation.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PompeRepository extends JpaRepository<Pompe, Long> {

    @Query("SELECT p FROM Pompe p LEFT JOIN FETCH p.citernes ct where ct.citerne.station = ?1")
    Optional<List<Pompe>> findAllPompesByStation(Station station);

}
