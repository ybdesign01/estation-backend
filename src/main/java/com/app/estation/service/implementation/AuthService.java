package com.app.estation.service.implementation;

import com.app.estation.advice.TokenRefreshException;
import com.app.estation.dto.*;
import com.app.estation.entity.RefreshToken;
import com.app.estation.entity.User;
import com.app.estation.mappers.UserMapper;
import com.app.estation.repository.UserRepository;
import com.app.estation.service.RefreshTokenService;
import com.app.estation.util.PassEncode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PassEncode passEncode;
    @Autowired
    private RefreshTokenService refreshTokenService;

    public AuthDto login(LoginDto dto){
        final User user = userRepository.findByEmail(dto.getEmail()).orElse(null);
        if(null == user){
            return new AuthDto(null,null, "user_not_found", null);
        }
        if(!passEncode.matches(dto.getPassword(),user.getPassword())){
            return  new AuthDto(null,null, "password_not_match", null);
        }else{
            final String jwtToken = jwtService.generateToken(user);
            final RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
            return new AuthDto(jwtToken,refreshToken.getToken(), "authentication_success", UserMapper.fromEntity(user));
        }
    }

    public RefreshTokenDto refreshToken(RefreshTokenRequest dto){
        String token = dto.getRefreshToken();
        return refreshTokenService.findByToken(token)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String jwtToken = jwtService.generateToken(user);
                    return new RefreshTokenDto(jwtToken, token);
                }).orElseThrow(() -> new TokenRefreshException("refresh_token_invalid"));
    }

}
