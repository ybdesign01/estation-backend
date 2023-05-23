package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.ApiRequestException;
import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.StationDto;
import com.app.estation.dto.User.StationUserDto;
import com.app.estation.dto.User.StationUserKeyDto;
import com.app.estation.entity.Station;
import com.app.estation.entity.StationUser;
import com.app.estation.entity.User;
import com.app.estation.entity.keys.StationUserKey;
import com.app.estation.mappers.StationMapper;
import com.app.estation.mappers.StationUserMapper;
import com.app.estation.mappers.UserMapper;
import com.app.estation.repository.StationRepository;
import com.app.estation.repository.StationUserRepository;
import com.app.estation.repository.UserRepository;
import com.app.estation.service.StationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public StationUserDto add(StationUserDto stationUserDto) {
        StationUser get = stationUserRepository.findById(StationUserMapper.toEntityKey(stationUserDto.getStationUserKey())).orElse(null);
        if (get != null){
           throw new ApiRequestException("station_user_already_exist");
        }
        final StationUserKey key = StationUserMapper.toEntityKey(stationUserDto.getStationUserKey());
        final StationUser stationUser = new StationUser();
        stationUser.setStationUserKey(key);
        final Station station = stationRepository.findById(key.getId_station()).orElse(null);
        if (station == null){
        throw new EntityNotFoundException("station_not_found");
        }
        stationUser.setStation(station);
        final User user = userRepository.findById(key.getId_user()).orElse(null);
        if (user == null){
            throw new EntityNotFoundException("user_not_found");
        }
        stationUser.setUser(user);
        if (stationUser.getDate_debut() == null){
            stationUser.setDate_debut(Date.from(new Date().toInstant()).toString());
        }
        stationUserRepository.save(stationUser);
        return StationUserMapper.fromEntity(stationUserRepository.findById(key).orElseThrow(() -> new EntityNotFoundException("station_user_not_found")));
    }

    @Override
    public StationUserDto get(Long id) {
        return null;
    }

    @Override
    public StationUserDto update(StationUserDto stationUserDto) {
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
    public boolean delete(Long id) {
        return false;
    }

    public StationUserDto get(StationUserKeyDto key){
        StationUserKey key1 = StationUserMapper.toEntityKey(key);
        StationUser stationUser = stationUserRepository.findById(key1).orElse(null);
        if (stationUser == null){
            throw new EntityNotFoundException("station_user_not_found");
        }
        return StationUserMapper.fromEntity(stationUser);
    }

    @Override
    public boolean delete(StationUserDto stationUserDto) {
        if (stationUserDto == null){
            return false;
        }
        StationUser stationUser = StationUserMapper.toEntity(stationUserDto);
        stationUserRepository.delete(stationUser);
        return true;
    }

    public StationDto getCurrentStation(Long id_user) {
        userRepository.findById(id_user).orElseThrow(() -> new EntityNotFoundException("user_not_found"));
        StationUser stationUser = stationUserRepository.findLatestStationUserByUserId(id_user).orElse(null);
        if(stationUser == null){
            throw new EntityNotFoundException("no_station_assigned_to_user");
        }
        return StationMapper.fromEntityWithoutServices(stationUser.getStation());
    }


    public List<StationUserDto> getAllStationsByUser(Long id_user) {
        if (id_user == null){
            return null;
        }
        User user = UserMapper.toEntity(userService.get(id_user));
        List<StationUser> stationUsers = stationUserRepository.findAllByUser(user);
        if (stationUsers == null){
            throw new EntityNotFoundException("station_user_not_found");
        }
        return StationUserMapper.fromEntityList(stationUsers);
    }


    public List<StationUserDto> getAllUsersByStation(Long id_station) {
        if (id_station == null){
            return null;
        }
        Station station = StationMapper.toEntity(stationService.get(id_station));
        List<StationUser> stationUsers = stationUserRepository.findAllByStation(station);
        return StationUserMapper.fromEntityList(stationUsers);
    }
}
