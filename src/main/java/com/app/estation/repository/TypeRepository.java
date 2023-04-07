package com.app.estation.repository;

import com.app.estation.entity.TypeProduit;
import com.app.estation.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TypeRepository extends CrudRepository<TypeProduit, Long> {

}
