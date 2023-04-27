package com.app.estation.service;

import com.app.estation.entity.Profile;
import org.springframework.stereotype.Service;

public interface ProfileService{
    Profile findProfileByNom(String nom);

}
