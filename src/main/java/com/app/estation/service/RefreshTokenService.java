package com.app.estation.service;

import com.app.estation.entity.RefreshToken;
import com.app.estation.entity.User;

import java.util.Optional;

public interface RefreshTokenService {

    Optional<RefreshToken> findByToken(String token);

    RefreshToken createRefreshToken(User user);

    RefreshToken verifyExpiration(RefreshToken token);

    RefreshToken findByUser(Long userId);

    int deleteByUserId(Long userId);

    int deleteAllByUser(User user);

}
