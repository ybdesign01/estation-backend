package com.app.estation.repository;

import com.app.estation.entity.Citerne;
import com.app.estation.entity.Pompe;
import com.app.estation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CiterneRepository extends JpaRepository<Citerne, Long> {



}
