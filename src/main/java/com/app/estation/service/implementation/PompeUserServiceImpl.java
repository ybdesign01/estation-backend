package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.ApiRequestException;
import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.PompeUserRequest;
import com.app.estation.dto.User.PompeUserDto;
import com.app.estation.entity.Pompe;
import com.app.estation.entity.PompeUser;
import com.app.estation.entity.User;
import com.app.estation.mappers.PompeUserMapper;
import com.app.estation.repository.PompeRepository;
import com.app.estation.repository.PompeUserRepository;
import com.app.estation.repository.UserRepository;
import com.app.estation.service.EServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
public class PompeUserServiceImpl implements EServices<PompeUserDto,PompeUserDto> {

    @Autowired
    private PompeUserRepository pompeUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PompeRepository pompeRepository;

    @Autowired
    private ReleveServiceImpl releveService;





    @Override
    public PompeUserDto add(PompeUserDto dto) {
        PompeUser get = pompeUserRepository.findById(dto.getIdPompeUser()).orElse(null);
        System.out.println(get);
        if (null != get){
            throw new ApiRequestException("pompe_already_used");
        }

        PompeUser pompeUser = new PompeUser();
        pompeUser.setIdPompeUser(dto.getIdPompeUser());
        User user = userRepository.findById(dto.getUser().getId_user()).orElse(null);
        if (user == null) {
            throw new ApiRequestException("user_does_not_exist");
        }
        pompeUser.setUser(user);
        Pompe pompe = pompeRepository.findById(dto.getPompe().getId_pompe()).orElse(null);
        if (pompe == null) {
            throw new ApiRequestException("pompe_does_not_exist");
        }
        pompeUser.setPompe(pompe);
        pompeUser.setDateDebut(LocalDateTime.now());
        pompeUserRepository.save(pompeUser);
        return PompeUserMapper.fromEntity(pompeUserRepository.findById(pompeUser.getIdPompeUser()).orElse(null));
    }

    @Override
    public PompeUserDto update(PompeUserDto dto) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public PompeUserDto get(Long id) {
        return PompeUserMapper.fromEntity(pompeUserRepository.findById(id).orElse(null));
    }

    @Override
    public List<PompeUserDto> getAll() {
        return PompeUserMapper.fromEntityList(pompeUserRepository.findAll());
    }

    public List<PompeUserDto> getAllPompesByUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null){
            throw new EntityNotFoundException("user_not_found");
        }
        return PompeUserMapper.fromEntityList(pompeUserRepository.findAllByUser(user));
    }
    public PompeUserDto setPompeToUser(PompeUserRequest request) {

        if (request.getDateDebut().isAfter(request.getDateFin()) || request.getDateDebut().isEqual(request.getDateFin())) {
            throw new ApiRequestException("invalid_date");
        }
        final int pompesAssignedToUserOnDate = pompeUserRepository.countPompesAssignedToUserOnDate(request.getIdUser(), request.getDateDebut());
        if (pompesAssignedToUserOnDate >= 2) {
            throw new ApiRequestException("user_already_has_pompes");
        }

        final int countPumpsAssignedToOtherUserDuringTimeRange = pompeUserRepository.countPompesAssignedDuringTimeRange(request.getIdPompe(), request.getDateDebut(), request.getDateFin());
        if (countPumpsAssignedToOtherUserDuringTimeRange > 0) {
            throw new ApiRequestException("pompe_unavailable_during_time_range");
        }
        User user = userRepository.findById(request.getIdUser()).orElseThrow(() -> new EntityNotFoundException("user_not_found"));
        Pompe pompe = pompeRepository.findById(request.getIdPompe()).orElseThrow(() -> new EntityNotFoundException("pompe_not_found"));
        PompeUser pompeUser = new PompeUser(pompe, user, request.getDateDebut(), request.getDateFin());
        pompeUserRepository.save(pompeUser);

        return PompeUserMapper.fromEntity(pompeUser);
    }

    public List<PompeUserDto> getPompsAssignedToUserForToday(final Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("user_not_found"));
        final LocalDate currentDate = LocalDate.now();
        final LocalDateTime startOfDay = currentDate.atStartOfDay();
        final LocalDateTime endOfDay = startOfDay.plusHours(23).plusMinutes(59).plusSeconds(59);
        final List<PompeUser> pompeUsers = pompeUserRepository.getPompesAssignedToUserForDay(userId, startOfDay, endOfDay, LocalDateTime.now());
        List<PompeUserDto> pompeUserDtos = PompeUserMapper.fromEntityList(pompeUsers);
        pompeUserDtos.forEach(pompeUserDto -> {
            pompeUserDto.setReleve(releveService.getStatusByPompeUser(pompeUserDto.getIdPompeUser()));
        });
        if (pompeUsers.isEmpty()) {
            throw new EntityNotFoundException("no_pompes_assigned_to_user");
        }
        return pompeUserDtos;
    }

    public List<PompeUserDto> getPompsAssignedToUser(final Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("user_not_found"));
        List<PompeUser> pompeUsers = pompeUserRepository.findAllByUser(user);
        if (pompeUsers.isEmpty()) {
            throw new EntityNotFoundException("no_pompes_assigned_to_user");
        }
        return PompeUserMapper.fromEntityList(pompeUsers);
    }
}
