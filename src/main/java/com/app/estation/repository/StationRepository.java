package com.app.estation.repository;

import com.app.estation.entity.Services;
import com.app.estation.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StationRepository extends JpaRepository<Station, Long> {

    List<Station> findStationByServicesId(Long id);





}
