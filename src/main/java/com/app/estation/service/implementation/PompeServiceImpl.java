package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.ApiRequestException;
import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.PompeDto;
import com.app.estation.entity.Pompe;
import com.app.estation.mappers.PompeMapper;
import com.app.estation.repository.CiternePompeRepository;
import com.app.estation.repository.PompeRepository;
import com.app.estation.service.EServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PompeServiceImpl implements EServices<PompeDto, PompeDto> {

    @Autowired
    private PompeRepository pompeRepository;

    @Autowired
    private CiternePompeRepository citernePompeRepository;


    @Override
    public PompeDto add(PompeDto dto) {
        Pompe pompe = PompeMapper.toEntity(dto);
        pompeRepository.save(pompe);
        return PompeMapper.fromEntity(pompeRepository.findById(pompe.getId_pompe()).orElseThrow(() -> new ApiRequestException("pompe_not_added")));
    }

    @Override
    public PompeDto update(PompeDto dto, Long id) {
        Pompe pompe = pompeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("pompe_not_found"));
        pompe.setNom_pompe(dto.getNom_pompe());
        pompe.setId_pompe(id);
        pompeRepository.save(pompe);
        return PompeMapper.fromEntity(pompeRepository.findById(dto.getId_pompe()).orElseThrow(() -> new ApiRequestException("pompe_not_updated")));
    }

    @Override
    public boolean delete(Long id) {
        pompeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("pompe_not_found"));
        pompeRepository.deleteById(id);
        return pompeRepository.existsById(id);
    }

    @Override
    public List<PompeDto> getAll() {
        List<Pompe> pompes =  pompeRepository.findAll();
        if (pompes.isEmpty()) {
            throw new EntityNotFoundException("no_pompe_found");
        }
        return PompeMapper.fromEntityList(pompes);
    }

    @Override
    public PompeDto get(Long id) {
        return PompeMapper.fromEntity(pompeRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("pompe_not_found")));
    }

}
