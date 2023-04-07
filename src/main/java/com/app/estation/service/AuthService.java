package com.app.estation.service;

import com.app.estation.dto.AuthDto;
import com.app.estation.dto.UserDto;
import com.app.estation.entity.Profile;
import com.app.estation.entity.User;
import com.app.estation.repository.ProfileRepository;
import com.app.estation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private JwtService jwtService;

    public AuthDto login(UserDto userDto){
        User user = userRepository.findByEmail(userDto.getEmail()).orElse(null);
        if(user == null){
            return AuthDto.builder(null, "Utilisateur introuvable!");
        }
        if(!user.getPassword().equals(userDto.getPassword())){
            return  AuthDto.builder(null, "Mot de passe incorrect!");
        }else{
            String jwtToken = jwtService.generateToken(user);
            return AuthDto.builder(jwtToken, "Authenticated");
        }


    }


    public AuthDto register(UserDto userDto){
        Profile profile = profileRepository.save(Profile.builder(
                null,
                userDto.getProfile().getNom(),
                userDto.getProfile().getDescription()
        ));
        User user = User.builder(
                null,
                userDto.getNom(),
                userDto.getPrenom(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getMatricule(),
                profile
        );
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthDto.builder(jwtToken, "Saved");
    }

}
