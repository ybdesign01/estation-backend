package com.app.estation.service.implementation;


import com.app.estation.dto.UserDto;
import com.app.estation.entity.Profile;
import com.app.estation.entity.User;
import com.app.estation.repository.ProfileRepository;
import com.app.estation.repository.UserRepository;
import com.app.estation.service.ProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private ProfileRepository profileRepository;

    public Profile findProfileByNom(String nom){
        return profileRepository.findProfileByNom(nom);
    }




}
