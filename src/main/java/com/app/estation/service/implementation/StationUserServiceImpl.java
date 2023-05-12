package com.app.estation.service.implementation;

import com.app.estation.dto.StationDto;
import com.app.estation.dto.User.StationUserDto;
import com.app.estation.dto.User.StationUserKeyDto;
import com.app.estation.dto.User.UserDto;
import com.app.estation.entity.Station;
import com.app.estation.entity.StationUser;
import com.app.estation.entity.User;
import com.app.estation.entity.keys.StationUserKey;
import com.app.estation.mappers.StationMapper;
import com.app.estation.mappers.StationUserMapper;
import com.app.estation.mappers.UserMapper;
import com.app.estation.repository.StationRepository;
import com.app.estation.repository.UserRepository;
import com.app.estation.service.StationUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import com.app.estation.repository.StationUserRepository;
import org.springframework.stereotype.Service;

@Service
public class StationUserServiceImpl implements StationUserService {

    @Autowired
    private StationUserRepository stationUserRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StationServiceImpl stationService;

    @Autowired
    StationRepository stationRepository;

    @Autowired
    UserRepository userRepository;


    public List<StationUserDto> getAll() {
        List<StationUser> stationUsers = stationUserRepository.findAll();
        return StationUserMapper.fromEntityList(stationUsers);
    }

    @Override
    public StationUserDto addStationUser(StationUserKeyDto stationUserKeyDto) {
        if (stationUserKeyDto == null){
            return null;
        }
        final StationUserKey key = StationUserMapper.toEntityKey(stationUserKeyDto);
        final StationUser stationUser = new StationUser();
        stationUser.setStationUserKey(key);
        final StationDto stationDto = stationService.getStation(stationUserKeyDto.getId_station());
        if (stationDto == null){
            return null;
        }
        stationUser.setStation(StationMapper.toEntity(stationDto));
        final UserDto user = userService.getUser(stationUserKeyDto.getId_user());
        if (user == null){
            return null;
        }
        stationUser.setUser(UserMapper.toEntity(user));
        stationUser.setDate_debut(Date.from(new Date().toInstant()).toString());
        stationUserRepository.save(stationUser);
        System.out.println(stationUserRepository.findById(key).orElse(null));
        return StationUserMapper.fromEntity(stationUserRepository.findById(key).orElse(null));
    }

    @Override
    public StationUserDto updateStationUser(StationUserDto stationUserDto) {
        if (stationUserDto == null){
            return null;
        }
        StationUser stationUser = StationUserMapper.toEntity(stationUserDto);
        Station st = stationRepository.findById(stationUser.getStationUserKey().getId_station()).orElse(null);
        User us = userRepository.findById(stationUser.getStationUserKey().getId_user()).orElse(null);
        stationUser.setStation(st);
        stationUser.setUser(us);
        stationUserRepository.save(stationUser);
        StationUserKey key = new StationUserKey(stationUserDto.getStationUserKey().getId_user(),stationUserDto.getStationUserKey().getId_station());
        StationUser dto = stationUserRepository.findById(key).orElse(null);
        return StationUserMapper.fromEntity(dto);
    }

    @Override
    public void deleteStationUser(StationUserDto stationUserDto) {
        if (stationUserDto == null){
            return;
        }
        StationUser stationUser = StationUserMapper.toEntity(stationUserDto);
        stationUserRepository.delete(stationUser);
    }

    @Override
    public StationUserDto getStationUserById(Long id_user, Long id_station) {
        StationUserKey key = new StationUserKey(id_user, id_station);
        StationUser stationUser = stationUserRepository.findById(key).orElse(null);
        if(stationUser == null){
            return null;
        }
        return StationUserMapper.fromEntity(stationUser);
    }

    @Override
    public List<StationUserDto> getAllStationsByUser(Long id_user) {
        if (id_user == null){
            return null;
        }
        User user = UserMapper.toEntity(userService.getUser(id_user));
        List<StationUser> stationUsers = stationUserRepository.findAllByUser(user);
        return StationUserMapper.fromEntityList(stationUsers);
    }

    @Override
    public List<StationUserDto> getAllUsersByStation(Long id_station) {
        if (id_station == null){
            return null;
        }
        Station station = StationMapper.toEntity(stationService.getStation(id_station));
        List<StationUser> stationUsers = stationUserRepository.findAllByStation(station);
        return StationUserMapper.fromEntityList(stationUsers);
    }
}
