package com.app.estation.repository;

import com.app.estation.entity.Services;
import com.app.estation.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StationRepository extends JpaRepository<Station, Long> {

    List<Station> findStationByServicesId(Long id);


    @Query("select s.services from Station s where s = ?1")
    List<Services> findServicesByStation(Station station);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Station s JOIN s.services srv WHERE srv.nomService = :nomService AND s = :station")
    boolean existsByNomServiceAndStation(@Param("nomService") String nomService, @Param("station") Station station);


}
