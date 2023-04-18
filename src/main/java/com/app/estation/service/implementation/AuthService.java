package com.app.estation.service.implementation;

import com.app.estation.dto.AuthDto;
import com.app.estation.dto.UserDto;
import com.app.estation.dto.UserPassDto;
import com.app.estation.entity.User;
import com.app.estation.repository.UserRepository;
import com.app.estation.util.PassEncode;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    public AuthDto login(UserDto userDto){
        User user = userRepository.findByEmail(userDto.getEmail()).orElse(null);
        if(user == null){
            return AuthDto.builder(null, "user_not_found", null);
        }
        if(!passEncode.matches(userDto.getPassword(),user.getPassword())){
            return  AuthDto.builder(null, "password_not_match",modelMapper.map(user, UserPassDto.class));
        }else{
            String jwtToken = jwtService.generateToken(user);
            return AuthDto.builder(jwtToken, "authentication_success",modelMapper.map(user, UserPassDto.class));
        }
    }

}
