package com.app.estation.service.implementation;
import com.app.estation.dto.PompeDto;
import com.app.estation.entity.Pompe;
import com.app.estation.mappers.PompeMapper;
import com.app.estation.repository.PompeRepository;
import com.app.estation.service.PompeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PompeServiceImpl implements PompeService {

    @Autowired
    private PompeRepository pompeRepository;

    @Override
    public PompeDto addPompe(PompeDto dto) {
        Pompe pompe = PompeMapper.toEntity(dto);

        pompeRepository.save(pompe);
        return null;
    }

    @Override
    public PompeDto updatePompe(PompeDto dto) {
        Pompe pompe = PompeMapper.toEntity(dto);
        pompeRepository.save(pompe);
        return null;
    }

    @Override
    public boolean deletePompe(Long id) {
        pompeRepository.deleteById(id);
        return pompeRepository.existsById(id);
    }

    @Override
    public List<PompeDto> getAllPompes() {
        List<Pompe> pompes =  pompeRepository.findAll();
        if (pompes.isEmpty()) {
            return null;
        }
        return PompeMapper.fromEntityList(pompes);
    }

    @Override
    public PompeDto getPompe(Long id) {
        return PompeMapper.fromEntity(pompeRepository.findById(id).orElse(null));
    }
}
