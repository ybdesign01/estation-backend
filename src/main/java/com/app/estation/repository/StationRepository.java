package com.app.estation.repository;

import com.app.estation.entity.Station;
import com.app.estation.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StationRepository extends CrudRepository<Station, Long> {

}
