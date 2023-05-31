package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.CiternePompeDto;
import com.app.estation.dto.CiternePompeRequest;
import com.app.estation.entity.Citerne;
import com.app.estation.entity.CiternePompe;
import com.app.estation.entity.Pompe;
import com.app.estation.mappers.CiternePompeMapper;
import com.app.estation.repository.CiternePompeRepository;
import com.app.estation.repository.CiterneRepository;
import com.app.estation.repository.PompeRepository;
import com.app.estation.service.EServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CiternePompeServiceImpl implements EServices<CiternePompeDto,CiternePompeDto> {

    @Autowired
    private CiterneRepository citerneRepository;

    @Autowired
    private CiternePompeRepository citernePompeRepository;

    @Autowired
    private PompeRepository pompeRepository;
    @Override
    public CiternePompeDto add(CiternePompeDto request) {
        return null;
    }

    public CiternePompeDto setCiterne(CiternePompeRequest citernePompeRequest){
        final Citerne citerne = citerneRepository.findById(citernePompeRequest.getIdCiterne()).orElseThrow(() -> new EntityNotFoundException("citerne_not_found"));
        final Pompe pompe = pompeRepository.findById(citernePompeRequest.getIdPompe()).orElseThrow(() -> new EntityNotFoundException("pompe_not_found"));
        final CiternePompe citernePompe = citernePompeRepository.findByIdPompe(pompe.getId_pompe());
        if(null != citernePompe){
            citernePompe.setDateFin(LocalDateTime.now());
            citernePompeRepository.save(citernePompe);
        }
            final CiternePompe citernePompe1 = new CiternePompe();
            citernePompe1.setCiterne(citerne);
            citernePompe1.setPompe(pompe);
            citernePompe1.setDateDebut(LocalDateTime.now());
            citernePompe1.setDateFin(null);

        citernePompeRepository.save(citernePompe1);
        return CiternePompeMapper.fromEntity(citernePompeRepository.findById(citernePompe1.getIdCiternePompe()).orElseThrow(() -> new EntityNotFoundException("citerne_pompe_not_added")));
    }

    @Override
    public CiternePompeDto get(Long id) {
        return null;
    }

    @Override
    public CiternePompeDto update(CiternePompeDto request, Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<CiternePompeDto> getAll() {
        return null;
    }
}
