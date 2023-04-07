package com.app.estation.service;


import com.app.estation.dto.UserDto;
import com.app.estation.entity.Profile;
import com.app.estation.entity.User;
import com.app.estation.repository.UserRepository;
import com.app.estation.util.PassEncode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl {

    @Autowired
    PassEncode passEncode;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileServiceImpl profileService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    public List<User> getAll(){
        return (List<User>) userRepository.findAll();
    }

    public User getUser(Long id) {
        Optional<User> result = userRepository.findById(id);
        return result.orElse(null);
    }

    public User getUserByToken(String token) {
        String jwt = token.substring(7);
        String userEmail = jwtService.extractEmail(jwt);
        Optional<User> result = userRepository.findByEmail(userEmail);
        return result.orElse(null);
    }

    public User addUser(UserDto userDto){
        System.out.println("userdto:" + userDto);
        Profile profile = profileService.findProfileByNom(userDto.getProfile());
        System.out.println("accessesd");
        User user = User.builder(
                null,
                userDto.getNom(),
                userDto.getPrenom(),
                userDto.getEmail(),
                passEncode.encode(userDto.getPassword()),
                userDto.getMatricule(),
                profile
        );
        System.out.println(user);
        userRepository.save(user);
        if (userRepository.existsByEmail(user.getEmail())){
            return user;
        }else{
            return null;
        }
    }


    public User updateUser(Long id, UserDto userDto) {
        System.out.println("userdto:" + userDto);
        Profile profile = profileService.findProfileByNom(userDto.getProfile());
        System.out.println("accessesd");
        User user = User.builder(
                id,
                userDto.getNom(),
                userDto.getPrenom(),
                userDto.getEmail(),
                passEncode.encode(userDto.getPassword()),
                userDto.getMatricule(),
                profile
        );
        System.out.println(user);
        userRepository.save(user);
        if (userRepository.existsByEmail(user.getEmail())){
            return user;
        }else{
            return null;
        }
    }
}
