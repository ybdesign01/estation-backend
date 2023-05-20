package com.app.estation.repository;

import com.app.estation.entity.Releve;
import com.app.estation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReleveRepository extends JpaRepository<Releve, Long> {

}
