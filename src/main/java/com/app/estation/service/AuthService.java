package com.app.estation.service;

import com.app.estation.dto.AuthDto;
import com.app.estation.dto.UserDto;
import com.app.estation.entity.Profile;
import com.app.estation.entity.User;
import com.app.estation.repository.ProfileRepository;
import com.app.estation.repository.UserRepository;
import com.app.estation.util.PassEncode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PassEncode passEncode;

    public AuthDto login(UserDto userDto){
        User user = userRepository.findByEmail(userDto.getEmail()).orElse(null);
        if(user == null){
            return AuthDto.builder(null, "Utilisateur introuvable!");
        }
        if(!passEncode.matches(user.getPassword(),userDto.getPassword())){
            return  AuthDto.builder(null, "Mot de passe incorrect!");
        }else{
            String jwtToken = jwtService.generateToken(user);
            return AuthDto.builder(jwtToken, "Authenticated");
        }


    }

/*    public AuthDto register(UserDto userDto){
        Profile p = profileRepository.findProfileByNom(userDto.getProfile().getNom());
        User user = User.builder(
                null,
                userDto.getNom(),
                userDto.getPrenom(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getMatricule(),
                p
        );
        System.out.println(user);
        userRepository.save(user);
        return AuthDto.builder("jwtToken", "Saved");
    }*/

}
