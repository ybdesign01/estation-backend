package com.app.estation.service.implementation;


import com.app.estation.dto.UserDto;
import com.app.estation.dto.UserPassDto;
import com.app.estation.entity.Profile;
import com.app.estation.entity.User;
import com.app.estation.mappers.UserMapper;
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
    public List<UserDto> getAll(){
        List<UserDto> users = UserMapper.fromEntityList(userRepository.findAll());
        return users;
    }

    @Override
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        UserDto result = UserMapper.fromEntity(user);
        return result;
    }
    @Override
    public UserDto getUserByToken(String token) {
        String jwt = token.substring(7);
        String userEmail = jwtService.extractEmail(jwt);
        Optional<User> result = userRepository.findByEmail(userEmail);
        return result.map(UserMapper::fromEntity).orElse(null);
    }

    @Override
    public UserDto addUser(UserPassDto userDto){
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
            return UserMapper.fromEntity(user);
        }else{
            return null;
        }
    }

    @Override
    public UserDto updateUser(Long id, UserPassDto userDto) {
        Profile profile = profileService.findProfileByNom(userDto.getProfile().getNom());
        if(null == profile){
            return null;
        }
        User user = userRepository.findById(id).orElse(null);
        if (null == user){
            return null;
        }
        user.setNom(userDto.getNom());
        user.setPrenom(userDto.getPrenom());
        user.setEmail(userDto.getEmail());
        user.setPassword(passEncode.encode(userDto.getPassword()));
        user.setMatricule(userDto.getMatricule());
        user.setProfile(profile);
        userRepository.save(user);
        if (userRepository.existsByEmail(user.getEmail())){
            return UserMapper.fromEntity(user);
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
