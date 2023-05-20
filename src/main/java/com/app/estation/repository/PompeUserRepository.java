package com.app.estation.repository;

import com.app.estation.entity.PompeUser;
import com.app.estation.entity.User;
import com.app.estation.entity.keys.PompeUserKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PompeUserRepository extends JpaRepository<PompeUser, PompeUserKey> {
    List<PompeUser> findAllByUser(User user);
}
