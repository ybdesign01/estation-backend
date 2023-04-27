package com.app.estation.service.implementation;

import com.app.estation.dto.ReleveDto;
import com.app.estation.entity.Releve;
import com.app.estation.mappers.ReleveMapper;
import com.app.estation.repository.ReleveRepository;
import com.app.estation.service.ReleveService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Transactional
@Service
public class ReleveServiceImpl implements ReleveService {

    @Autowired
    ReleveRepository releveRepository;

    @Override
    public List<Releve> getAll() {
        return (List<Releve>) releveRepository.findAll();
    }

    @Override
    public Releve getReleve(Long id) {
        return releveRepository.findById(id).orElse(null);
    }

    @Override
    public Releve addReleve(ReleveDto releve) {
        Releve r = ReleveMapper.INSTANCE.releveDtoToReleve(releve);
        try {
            releveRepository.save(r);
            return r;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Releve updateReleve(Long id, ReleveDto releve) {
        if (releveRepository.findById(id).isEmpty()) {
            return null;
        }
        Releve r = ReleveMapper.INSTANCE.releveDtoToReleve(releve);
        try {
            releveRepository.save(r);
            return r;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteReleve(Long id) {
        if (releveRepository.findById(id).isPresent()) {
            releveRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
