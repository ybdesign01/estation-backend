package com.app.estation.service.implementation;


import com.app.estation.advice.exceptions.ApiRequestException;
import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.User.UserDto;
import com.app.estation.dto.User.UserPassDto;
import com.app.estation.entity.Profile;
import com.app.estation.entity.User;
import com.app.estation.mappers.UserMapper;
import com.app.estation.repository.ProfileRepository;
import com.app.estation.repository.UserRepository;
import com.app.estation.service.EServices;
import com.app.estation.util.PassEncode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements EServices<UserDto,UserPassDto> {

    @Autowired
    PassEncode passEncode;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public List<UserDto> getAll(){
        List<UserDto> users = UserMapper.fromEntityList(userRepository.findAll());
        if (users.isEmpty()){
            throw new EntityNotFoundException("no_users_found");
        }
        return users;
    }

    @Override
    public UserDto get(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user_not_found"));
        return UserMapper.fromEntity(user);
    }

    public UserDto getUserByToken(String token) {
        String jwt = token.substring(7);
        String userEmail = jwtService.extractEmail(jwt);
        Optional<User> result = userRepository.findByEmail(userEmail);
        return result.map(UserMapper::fromEntity).orElseThrow(() -> new EntityNotFoundException("user_not_found"));
    }

    @Override
    public UserDto add(UserPassDto userDto){
        Profile profile = profileRepository.findProfileByNom(userDto.getProfile().getNom());
        User user = userRepository.findByEmail(userDto.getEmail()).orElse(null);
        if (null != user){
            throw new ApiRequestException("email_already_used");
        }
         user = new User( userDto.getNom(),
                userDto.getPrenom(),
                userDto.getEmail(),
                 passEncode.encode(userDto.getPassword()),
                userDto.getMatricule(),
                profile);
        userRepository.save(user);
        return UserMapper.fromEntity(userRepository.findById(user.getId_user()).orElseThrow(() -> new ApiRequestException("user_not_added")));
    }

    @Override
    public UserDto update(UserPassDto userDto, Long id) {
        Profile profile = profileRepository.findProfileByNom(userDto.getProfile().getNom());
        if(null == profile){
            throw new EntityNotFoundException("profile_not_found");
        }
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user_not_found"));
        user.setNom(userDto.getNom());
        user.setPrenom(userDto.getPrenom());
        user.setEmail(userDto.getEmail());
        user.setPassword(passEncode.encode(userDto.getPassword()));
        user.setMatricule(userDto.getMatricule());
        user.setProfile(profile);
        userRepository.save(user);
        return UserMapper.fromEntity(userRepository.findById(userDto.getId_user()).orElseThrow(() -> new ApiRequestException("user_not_updated")));
    }


    @Override
    public boolean delete(Long id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
