package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.ApiRequestException;
import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.ReleveDto;
import com.app.estation.entity.*;
import com.app.estation.mappers.ReleveMapper;
import com.app.estation.repository.CiternePompeRepository;
import com.app.estation.repository.PompeUserRepository;
import com.app.estation.repository.ReleveRepository;
import com.app.estation.service.EServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.estation.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import static java.lang.Math.abs;

@Transactional
@Service
public class ReleveServiceImpl implements EServices<ReleveDto,ReleveDto> {

    @Autowired
    private ReleveRepository releveRepository;

    @Autowired
    PompeUserRepository pompeUserRepository;

    @Autowired
    CiternePompeRepository citernePompeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ReleveDto> getAll() {
        List<ReleveDto> list = ReleveMapper.fromEntityList(releveRepository.findAll());
        if (list.isEmpty()){
            throw new EntityNotFoundException("no_releves_found");
        }
        return list;
    }

    @Override
    public ReleveDto get(Long id) {
        return ReleveMapper.fromEntity(releveRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("releve_not_found")));
    }

    public List<ReleveDto> getByUser(Long idUser) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("user_not_found"));
        return ReleveMapper.fromEntityList(releveRepository.findAllByPompeUserUser(user).orElseThrow(() -> new EntityNotFoundException("no_releves_found")));
    }


    public boolean getStatusByPompeUser(Long idPompeUser){
        pompeUserRepository.findById(idPompeUser).orElseThrow(() -> new EntityNotFoundException("pompe_user_not_found"));
        List<Releve> r = releveRepository.findAllByPompeUserIdPompeUser(idPompeUser);
        if (r.isEmpty()){
            return false;
        }
        return true;
    }



    @Override
    public ReleveDto add(ReleveDto releve) {
        PompeUser pompeUser = pompeUserRepository.findById(releve.getPompeUser().getIdPompeUser()).orElseThrow(() -> new EntityNotFoundException("pompe_user_not_found"));
        Releve releve1 = releveRepository.getReleveByPompeId(pompeUser.getPompe().getId_pompe()).orElse(null);
        if (LocalDateTime.now().isBefore(pompeUser.getDateDebut()) || LocalDateTime.now().isAfter(pompeUser.getDateFin())){
            throw new ApiRequestException("date_not_in_range");
        }
        if (null != releve1 && releve1.isEntree()) {
            Releve releve3 = ReleveMapper.toEntity(releve);
            releve3.setType_releve(TypeReleve.RELEVE_SORTIE);
            releve3.setPompeUser(releve1.getPompeUser());
            releve3.setDateReleve(LocalDateTime.now());
            releveRepository.save(releve3);
        }
            Releve releve2 = ReleveMapper.toEntity(releve);
            releve2.setType_releve(TypeReleve.RELEVE_ENTREE);
            releve2.setDateReleve(LocalDateTime.now());
            releve2.setPompeUser(pompeUser);
            releveRepository.save(releve2);
            return ReleveMapper.fromEntity(releveRepository.findById(releve2.getId_releve()).orElseThrow(()-> new ApiRequestException("releve_not_added")));
    }

    @Override
    public ReleveDto update(ReleveDto releve, Long id) {
        Releve re = releveRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("releve_not_found"));
        Releve r = ReleveMapper.toEntity(releve);
        releveRepository.save(r);
        return ReleveMapper.fromEntity(releveRepository.findById(releve.getId_releve()).orElseThrow(()-> new ApiRequestException("releve_not_updated")));
    }

    @Override
    public boolean delete(Long id) {
        if (releveRepository.findById(id).isPresent()) {
            releveRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    public Long getCompteurByIdPompeUser(Long id){
        PompeUser p = pompeUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("pompe_user_not_found"));
        List<Releve> r = releveRepository.findAllByPompeUserIdPompeUserOrderByDateReleveAsc(id);
        if (r.isEmpty() || r.size() < 2){
            return 0L;
        }
        if (p.getPompe().getCompteurInitial() != 0){
            return abs((r.get(1).getCompteur() - r.get(0).getCompteur()) - p.getPompe().getCompteurInitial());
        }else{
            return abs(r.get(1).getCompteur() - r.get(0).getCompteur());
        }
    }

    public Double calculatePrice(Long idPompeUser){
        PompeUser p = pompeUserRepository.findById(idPompeUser).orElseThrow(() -> new EntityNotFoundException("pompe_user_not_found"));
        List<Releve> r = releveRepository.findAllByPompeUserIdPompeUserOrderByDateReleveAsc(idPompeUser);
        if (r.isEmpty() || r.size() < 2){
            return 0.0;
        }
        double price = 0.0;
        CiternePompe cp = citernePompeRepository.findByIdPompe(p.getPompe().getId_pompe());
        Double prix_vente = cp.getCiterne().getId_produit().getPrix_vente();
        price = abs(r.get(0).getCompteur() - r.get(1).getCompteur()) * prix_vente;
        return Math.round(price * 100.0) / 100.0;
    }


}
