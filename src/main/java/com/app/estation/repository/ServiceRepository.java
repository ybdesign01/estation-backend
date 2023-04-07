package com.app.estation.repository;

import com.app.estation.entity.Service;
import com.app.estation.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ServiceRepository extends CrudRepository<Service, Long> {

}
