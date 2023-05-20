package com.app.estation.service.implementation;

import com.app.estation.dto.User.PompeUserDto;
import com.app.estation.dto.User.PompeUserKeyDto;
import com.app.estation.entity.Pompe;
import com.app.estation.entity.PompeUser;
import com.app.estation.entity.User;
import com.app.estation.entity.keys.PompeUserKey;
import com.app.estation.mappers.PompeUserMapper;
import com.app.estation.repository.PompeRepository;
import com.app.estation.repository.PompeUserRepository;
import com.app.estation.repository.UserRepository;
import com.app.estation.service.PompeUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PompeUserServiceImpl implements PompeUserService {

    @Autowired
    private PompeUserRepository pompeUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PompeRepository pompeRepository;



    @Override
    public PompeUserDto addPompeUser(PompeUserKeyDto dto) {
        PompeUser get = pompeUserRepository.findById(PompeUserMapper.toEntityKey(dto)).orElse(null);
        System.out.println(get);
        if (get != null){
            return null;
        }
        PompeUser pompeUser = new PompeUser();
        pompeUser.setPompeUserKey(PompeUserMapper.toEntityKey(dto));
        User user = userRepository.findById(dto.getId_user()).orElse(null);
        if (user == null) {
            return null;
        }
        pompeUser.setUser(user);
        Pompe pompe = pompeRepository.findById(dto.getId_pompe()).orElse(null);
        if (pompe == null) {
            return null;
        }
        pompeUser.setPompe(pompe);
        System.out.println("date is:" + pompeUser.getDate_debut());
        if (pompeUser.getDate_debut() == null)
        pompeUser.setDate_debut(Date.from(new Date().toInstant()).toString());
        pompeUserRepository.save(pompeUser);
        return PompeUserMapper.fromEntity(pompeUserRepository.findById(pompeUser.getPompeUserKey()).orElse(null));
    }

    @Override
    public PompeUserDto updatePompeUser(PompeUserDto dto) {
        return null;
    }

    @Override
    public PompeUserDto deletePompeUser(PompeUserKeyDto id) {
        return null;
    }

    @Override
    public PompeUserDto getPompeUser(PompeUserKeyDto id) {
        return PompeUserMapper.fromEntity(pompeUserRepository.findById(PompeUserMapper.toEntityKey(id)).orElse(null));
    }

    @Override
    public List<PompeUserDto> getAllPompeUsers() {
        return PompeUserMapper.fromEntityList(pompeUserRepository.findAll());
    }
}
