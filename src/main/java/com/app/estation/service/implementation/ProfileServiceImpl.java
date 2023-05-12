package com.app.estation.service.implementation;


import com.app.estation.entity.Profile;
import com.app.estation.repository.ProfileRepository;
import com.app.estation.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {


    @Autowired
    private ProfileRepository profileRepository;

    public Profile findProfileByNom(String nom){
        return profileRepository.findProfileByNom(nom);
    }




}
