package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.ApiRequestException;
import com.app.estation.advice.exceptions.ApiRequestOkException;
import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.AffectationMontantDto;
import com.app.estation.dto.PompeUserRequest;
import com.app.estation.dto.User.PompeUserDto;
import com.app.estation.entity.*;
import com.app.estation.mappers.PompeUserMapper;
import com.app.estation.repository.*;
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

    @Autowired
    private CiternePompeRepository citernePompeRepository;





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
        return PompeUserMapper.fromEntityList(pompeUserRepository.findAllOrderedByDateDebutDesc().orElseThrow(() -> new EntityNotFoundException("no_affectations_found")));
    }

    public List<PompeUserDto> getAllPompesByUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null){
            throw new EntityNotFoundException("user_not_found");
        }
        return PompeUserMapper.fromEntityList(pompeUserRepository.findAllByUser(user));
    }
    public PompeUserDto setPompeToUser(PompeUserRequest request) {
        User user = userRepository.findById(request.getIdUser()).orElseThrow(() -> new EntityNotFoundException("user_not_found"));
        if (user.getAuthorities().stream()
                .anyMatch(r -> "ADMIN".equals(r.getAuthority()))) {
            throw new ApiRequestException("admin_cannot_have_pompe");
        }
        if (user.getAuthorities().stream()
                .anyMatch(r -> "MANAGER".equals(r.getAuthority()))) {
            throw new ApiRequestException("manager_cannot_have_pompe");
        }

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
        if (pompeUsers.isEmpty()) {
            throw new EntityNotFoundException("no_pompes_assigned_to_user");
        }
        List<PompeUserDto> pompeUserDtos = PompeUserMapper.fromEntityList(pompeUsers);
        pompeUserDtos.forEach(pompeUserDto -> {
            pompeUserDto.setReleve(releveService.getStatusByPompeUser(pompeUserDto.getIdPompeUser()));
        });

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

    public Produit getProduitByPompeUser(final Long idPompeUser) {
        PompeUser pompeUser = pompeUserRepository.findById(idPompeUser).orElseThrow(() -> new EntityNotFoundException("pompe_user_not_found"));
        CiternePompe citernePompe = citernePompeRepository.findByIdPompe(pompeUser.getPompe().getId_pompe());
        if (citernePompe != null) {
            return citernePompe.getCiterne().getId_produit();
        }
        return null;
    }

    public Citerne getCiterneByPompeUser(final Long idPompeUser) {
        PompeUser pompeUser = pompeUserRepository.findById(idPompeUser).orElseThrow(() -> new EntityNotFoundException("pompe_user_not_found"));
        CiternePompe citernePompe = citernePompeRepository.findByIdPompe(pompeUser.getPompe().getId_pompe());
        if (citernePompe != null) {
            return citernePompe.getCiterne();
        }
        return null;
    }

    public List<AffectationMontantDto> getAffectationsMontant(final String email) {
        User user = userRepository.getByEmail(email).orElseThrow(() -> new EntityNotFoundException("user_not_found"));
        List<PompeUser> pompeUsers = pompeUserRepository.countPompesAssignedToUserToday(user.getId_user(), LocalDateTime.now());
        List<Long> ids = new ArrayList<>();
        if (pompeUsers.isEmpty()) {
            throw new EntityNotFoundException("no_pompes_assigned_to_user_today");
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
