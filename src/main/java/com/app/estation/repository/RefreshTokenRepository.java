package com.app.estation.repository;

import com.app.estation.entity.RefreshToken;
import com.app.estation.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findRefreshTokenByToken(String token);
    RefreshToken findByUser(User user);
    int deleteByUser(User user);

    @Transactional
    int deleteAllByUser(User user);

    @Modifying
    @Query("delete from RefreshToken t where t.id = ?1")
    void deleteById(Long id);




}
