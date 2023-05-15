package com.app.estation.repository;

import com.app.estation.entity.PompeUser;
import com.app.estation.entity.keys.PompeUserKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PompeUserRepository extends JpaRepository<PompeUser, PompeUserKey> {
}
