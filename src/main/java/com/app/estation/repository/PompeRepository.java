package com.app.estation.repository;

import com.app.estation.entity.Pompe;
import com.app.estation.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PompeRepository extends CrudRepository<Pompe, Long> {

}
