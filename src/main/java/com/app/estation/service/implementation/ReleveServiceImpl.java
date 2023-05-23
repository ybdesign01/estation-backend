package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.ApiRequestException;
import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.ReleveDto;
import com.app.estation.dto.ReleveResponse;
import com.app.estation.entity.PompeUser;
import com.app.estation.entity.Releve;
import com.app.estation.mappers.ReleveMapper;
import com.app.estation.repository.PompeUserRepository;
import com.app.estation.repository.ReleveRepository;
import com.app.estation.service.EServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class ReleveServiceImpl implements EServices<ReleveDto,ReleveDto> {

    @Autowired
    private ReleveRepository releveRepository;

    @Autowired
    PompeUserRepository pompeUserRepository;

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

    public ReleveResponse getStatusByPompeUser(Long idPompeUser){
        pompeUserRepository.findById(idPompeUser).orElseThrow(() -> new EntityNotFoundException("pompe_user_not_found"));
        List<Releve> r = releveRepository.findAllByPompeUserIdPompeUser(idPompeUser);
        if (r.isEmpty()){
            return new ReleveResponse(false,false);
        }
        boolean sortie = false;
        boolean entree = false;
        for (Releve releve : r) {
            if (releve.isSortie()){
                sortie = true;
            }
            if (releve.isEntree()){
                entree = true;
            }
        }
        return new ReleveResponse(entree,sortie);
    }

    @Override
    public ReleveDto add(ReleveDto releve) {
        PompeUser pompeUser = pompeUserRepository.findById(releve.getPompeUser().getIdPompeUser()).orElseThrow(() -> new EntityNotFoundException("pompe_user_not_found"));
       if (LocalDateTime.now().isBefore(pompeUser.getDateDebut()) || LocalDateTime.now().isAfter(pompeUser.getDateFin())){
           throw new ApiRequestException("date_not_in_range");
       }
        Releve r = ReleveMapper.toEntity(releve);
        r.setDate_releve(LocalDateTime.now());
        r.setPompeUser(pompeUser);
        releveRepository.save(r);
        return ReleveMapper.fromEntity(releveRepository.findById(r.getId_releve()).orElseThrow(()-> new ApiRequestException("releve_not_added")));
    }

    @Override
    public ReleveDto update(ReleveDto releve) {
        Releve re = releveRepository.findById(releve.getId_releve()).orElseThrow(() -> new EntityNotFoundException("releve_not_found"));
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

/*    public List<ReleveDto> getReleveByPompe(Long id) {
        Pompe pompe = pompeRepository.findById(id).orElse(null);
        if (pompe == null) {
           throw new ApiRequestException("pompe_does_not_exist");
        }
        List<ReleveDto> list = ReleveMapper.fromEntityList(releveRepository.findAllByPompe(id));
        if (list.isEmpty()){
            throw new ApiRequestException("no_releves_found");
        }

        return list;
    }*/
}
