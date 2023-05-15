package com.app.estation.repository;

import com.app.estation.entity.ProduitAction;
import com.app.estation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ActionRepository extends JpaRepository<ProduitAction, Long> {

}
