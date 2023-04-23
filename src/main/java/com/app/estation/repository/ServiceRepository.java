package com.app.estation.repository;

import com.app.estation.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Services, Long> {

    List<Services> findServicesByStationsId(Long id);
}
