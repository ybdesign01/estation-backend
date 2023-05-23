package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.TokenRefreshException;
import com.app.estation.entity.RefreshToken;
import com.app.estation.entity.User;
import com.app.estation.repository.RefreshTokenRepository;
import com.app.estation.repository.UserRepository;
import com.app.estation.service.RefreshTokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${jwt.refreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findRefreshTokenByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(User u) {
        RefreshToken refreshToken = new RefreshToken();
        final boolean isExpired = deleteRefreshToken(u);
        if (null != u.getRefreshToken() && !isExpired) {
            return u.getRefreshToken();
        }
        refreshToken.setUser(u);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public boolean deleteRefreshToken(User u) {
        RefreshToken r = u.getRefreshToken();
        if(r != null) {
            if (checkExpiration(r)) {
                refreshTokenRepository.deleteById(r.getId());
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException("refresh_token_expired");
        }
        return token;
    }

    public boolean checkExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            return true;
        }
        return false;
    }

    @Override
    public RefreshToken findByUser(Long userId) {
        return refreshTokenRepository.findByUser(userRepository.findById(userId).get());
    }

    @Override
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }



    @Override
    public int deleteAllByUser(User user) {
        return refreshTokenRepository.deleteAllByUser(user);
    }

}
