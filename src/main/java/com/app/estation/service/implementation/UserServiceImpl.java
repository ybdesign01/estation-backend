package com.app.estation.service.implementation;


import com.app.estation.dto.UserDto;
import com.app.estation.entity.Profile;
import com.app.estation.entity.User;
import com.app.estation.repository.UserRepository;
import com.app.estation.service.UserService;
import com.app.estation.util.PassEncode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PassEncode passEncode;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileServiceImpl profileService;

    @Autowired
    private JwtService jwtService;

    @Override
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        Optional<User> result = userRepository.findById(id);
        return result.orElse(null);
    }
    @Override
    public User getUserByToken(String token) {
        String jwt = token.substring(7);
        String userEmail = jwtService.extractEmail(jwt);
        Optional<User> result = userRepository.findByEmail(userEmail);
        return result.orElse(null);
    }

    @Override
    public User addUser(UserDto userDto){
        Profile profile = profileService.findProfileByNom(userDto.getProfile().getNom());
        User user = userRepository.findByEmail(userDto.getEmail()).orElse(null);
        if (null != user){
            return null;
        }
         user = new User( userDto.getNom(),
                userDto.getPrenom(),
                userDto.getEmail(),
                 passEncode.encode(userDto.getPassword()),
                userDto.getMatricule(),
                profile);
        userRepository.save(user);
        if (userRepository.existsByEmail(user.getEmail())){
            return user;
        }else{
            return null;
        }
    }

    @Override
    public User updateUser(Long id, UserDto userDto) {
        Profile profile = profileService.findProfileByNom(userDto.getProfile().getNom());
        if(null == profile){
            return null;
        }
        User user = new User(
                id,
                userDto.getNom(),
                userDto.getPrenom(),
                userDto.getEmail(),
                passEncode.encode(userDto.getPassword()),
                userDto.getMatricule(),
                profile
        );
        userRepository.save(user);
        if (userRepository.existsByEmail(user.getEmail())){
            return user;
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
