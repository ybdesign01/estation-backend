package com.app.estation.repository;

import com.app.estation.entity.Produit;
import com.app.estation.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProduitRepository extends CrudRepository<Produit, Long> {

}
