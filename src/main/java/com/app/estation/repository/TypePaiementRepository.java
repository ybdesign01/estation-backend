package com.app.estation.repository;

import com.app.estation.entity.TypePaiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypePaiementRepository extends JpaRepository<TypePaiement, Long> {
}
