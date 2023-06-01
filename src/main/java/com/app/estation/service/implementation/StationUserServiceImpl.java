package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.ApiRequestException;
import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.StationDto;
import com.app.estation.dto.StationUserRequest;
import com.app.estation.dto.User.StationUserDto;
import com.app.estation.entity.Station;
import com.app.estation.entity.StationUser;
import com.app.estation.entity.User;
import com.app.estation.mappers.StationMapper;
import com.app.estation.mappers.StationUserMapper;
import com.app.estation.mappers.UserMapper;
import com.app.estation.repository.StationRepository;
import com.app.estation.repository.StationUserRepository;
import com.app.estation.repository.UserRepository;
import com.app.estation.service.EServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StationUserServiceImpl implements EServices<StationUserDto, StationUserDto> {

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


    @Override
    public List<StationUserDto> getAll() {
        List<StationUser> stationUsers = stationUserRepository.findAll();
        return StationUserMapper.fromEntityList(stationUsers);
    }

    @Override
    public StationUserDto add(StationUserDto stationUserDto) {
        return null;
    }

    public StationUserDto setStationtoUser(StationUserRequest request){
        final StationUser last = stationUserRepository.findLatestStationUserByUserId(request.getIdUser()).orElse(null);
        if (last != null){
            last.setDate_fin(LocalDateTime.now());
            stationUserRepository.save(last);
        }
        final StationUser stationUser = new StationUser();
        final Station station = stationRepository.findById(request.getIdStation()).orElseThrow(() -> new EntityNotFoundException("station_not_found"));
        stationUser.setStation(station);
        final User user = userRepository.findById(request.getIdUser()).orElseThrow(() -> new EntityNotFoundException("user_not_found"));
        stationUser.setUser(user);
        if (stationUser.getDate_debut() == null){
            stationUser.setDate_debut(LocalDateTime.now());
        }
        stationUserRepository.save(stationUser);
        return StationUserMapper.fromEntity(stationUserRepository.findById(stationUser.getIdStationUser()).orElseThrow(() -> new ApiRequestException("station_user_not_added")));
    }


    @Override
    public StationUserDto update(StationUserDto stationUserDto, Long id) {
        StationUser stationUser = StationUserMapper.toEntity(stationUserDto);
        Station st = stationRepository.findById(stationUser.getStation().getId()).orElseThrow(() -> new EntityNotFoundException("station_not_found"));
        User us = userRepository.findById(stationUser.getUser().getId_user()).orElseThrow(() -> new EntityNotFoundException("user_not_found"));
        stationUser.setStation(st);
        stationUser.setUser(us);
        stationUserRepository.save(stationUser);
        return StationUserMapper.fromEntity(stationUserRepository.findById(stationUser.getIdStationUser()).orElseThrow(() -> new ApiRequestException("station_user_not_updated")));
    }

    @Override
    public StationUserDto get(Long id){
        return StationUserMapper.fromEntity(stationUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("station_user_not_found")));
    }

    @Override
    public boolean delete(Long id) {
        StationUser stationUser = stationUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("station_user_not_found"));
        stationUserRepository.delete(stationUser);
        return stationUserRepository.existsById(id);
    }

    public StationDto getCurrentStation(Long id_user) {
        userRepository.findById(id_user).orElseThrow(() -> new EntityNotFoundException("user_not_found"));
        StationUser stationUser = stationUserRepository.findLatestStationUserByUserId(id_user).orElseThrow(() -> new EntityNotFoundException("no_station_assigned_to_user"));
        return StationMapper.fromEntityWithoutServices(stationUser.getStation());
    }


    public List<StationUserDto> getAllStationsByUser(Long id_user) {
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
