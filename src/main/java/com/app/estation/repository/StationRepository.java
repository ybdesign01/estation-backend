package com.app.estation.repository;

import com.app.estation.entity.Services;
import com.app.estation.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StationRepository extends JpaRepository<Station, Long> {

    @Query("select s from Station s left join fetch s.services")
    List<Station> findAllStations();

/*    @Query("select s from Station s left join fetch s.services where s.id_station = ?1")
    List<Services> findStationServices(Long id);*/





}
