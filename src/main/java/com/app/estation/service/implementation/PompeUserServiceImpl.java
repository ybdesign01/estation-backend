package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.ApiRequestException;
import com.app.estation.advice.exceptions.ApiRequestOkException;
import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.AffectationMontantDto;
import com.app.estation.dto.PompeUserRequest;
import com.app.estation.dto.User.PompeUserDto;
import com.app.estation.entity.Pompe;
import com.app.estation.entity.PompeUser;
import com.app.estation.entity.Transaction;
import com.app.estation.entity.User;
import com.app.estation.mappers.PompeUserMapper;
import com.app.estation.repository.PompeRepository;
import com.app.estation.repository.PompeUserRepository;
import com.app.estation.repository.TransactionRepository;
import com.app.estation.repository.UserRepository;
import com.app.estation.service.EServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    private TransactionRepository transactionRepository;





    @Override
    public PompeUserDto add(PompeUserDto dto) {
        return null;
    }

    @Override
    public PompeUserDto update(PompeUserDto dto, Long id) {
        PompeUser pompeUser = pompeUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("pompe_user_not_found"));
        final Pompe pompe = pompeRepository.findById(dto.getPompe().getId_pompe()).orElseThrow(() -> new EntityNotFoundException("pompe_not_found"));
        pompeUser.setPompe(pompe);
        final User user = userRepository.findById(dto.getUser().getId_user()).orElseThrow(() -> new EntityNotFoundException("user_not_found"));
        pompeUser.setUser(user);
        pompeUser.setDateDebut(dto.getDateDebut());
        pompeUser.setDateFin(dto.getDateFin());
        pompeUserRepository.save(pompeUser);
        return PompeUserMapper.fromEntity(pompeUserRepository.findById(pompeUser.getIdPompeUser()).orElseThrow(() -> new ApiRequestException("pompe_user_not_updated")));
    }

    @Override
    public boolean delete(Long id) {
        PompeUser pompeUser = pompeUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("pompe_user_not_found"));
        pompeUserRepository.delete(pompeUser);
        return pompeUserRepository.existsById(id);
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
        final List<PompeUser> pompeUsers = pompeUserRepository.getPompesAssignedToUserForDay(userId, LocalDateTime.now());
        System.out.println(pompeUsers);
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

    public List<AffectationMontantDto> getAffectationsMontant(final Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("user_not_found"));
        List<PompeUser> pompeUsers = pompeUserRepository.countPompesAssignedToUserToday(userId, LocalDateTime.now());
        List<Long> ids = new ArrayList<>();
        if (pompeUsers.isEmpty()) {
            throw new EntityNotFoundException("no_pompes_assigned_to_user");
        }
        pompeUsers.forEach(pompeUser -> {
            ids.add(pompeUser.getIdPompeUser());
        });
        List<Transaction> count = transactionRepository.findTransactionsByExcludedPompeUserIds(ids);
        if (!count.isEmpty()) {
            throw new ApiRequestOkException("transactions_already_submitted");
        }
        List<AffectationMontantDto> affectationMontantDtos = new ArrayList<>();
        pompeUsers.forEach(pompeUser -> {
            AffectationMontantDto affectationMontantDto = new AffectationMontantDto();
            affectationMontantDto.setPompeUser(PompeUserMapper.fromEntity(pompeUser));
            Double montant = releveService.calculatePrice(pompeUser.getIdPompeUser());
            affectationMontantDto.setMontant(montant);
            affectationMontantDtos.add(affectationMontantDto);
        });
        affectationMontantDtos.removeIf(affectationMontantDto -> affectationMontantDto.getMontant().equals(0.0));
        return affectationMontantDtos;
    }
}
