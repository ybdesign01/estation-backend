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
    public List<ReleveDto> getAll() {
        List<ReleveDto> list = ReleveMapper.fromEntityList(releveRepository.findAll());
        if (list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public ReleveDto getReleve(Long id) {
        Releve releve = releveRepository.findById(id).orElse(null);
        if (releve == null) {
            return null;
        }
        ReleveDto releveDto = ReleveMapper.fromEntity(releve);
        return releveDto;
    }

    @Override
    public boolean addReleve(ReleveDto releve) {
        Releve r = ReleveMapper.toEntity(releve);
        try {
            releveRepository.save(r);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateReleve(Long id, ReleveDto releve) {
        if (releveRepository.findById(id).isEmpty()) {
            return false;
        }
        Releve r = ReleveMapper.toEntity(releve);
        try {
            releveRepository.save(r);
            return true;
        } catch (Exception e) {
            return false;
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

 /*   public List<ReleveDto> getReleveByPompe(Long id) {
        List<ReleveDto> list = ReleveMapper.fromEntityList(releveRepository.findAllById(id));
        if (list.isEmpty()){
            return null;
        }
        return list;
    }*/
}
