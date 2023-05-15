package com.app.estation.service.implementation;

import com.app.estation.dto.CiterneDto;
import com.app.estation.entity.Citerne;
import com.app.estation.mappers.CiterneMapper;
import com.app.estation.repository.CiterneRepository;
import com.app.estation.service.CiterneService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CiterneServiceImpl implements CiterneService {

    @Autowired
    private CiterneRepository citerneRepository;
    @Override
    public CiterneDto addCiterne(CiterneDto dto) {
        Citerne citerne = CiterneMapper.toEntity(dto);
        citerneRepository.save(citerne);
        return CiterneMapper.fromEntity(citerneRepository.findById(citerne.getId_citerne()).orElse(null));
    }

    @Override
    public CiterneDto updateCiterne(CiterneDto dto) {
        Citerne citerne = CiterneMapper.toEntity(dto);
        citerneRepository.save(citerne);
        return CiterneMapper.fromEntity(citerneRepository.findById(citerne.getId_citerne()).orElse(null));
    }

    @Override
    public CiterneDto deleteCiterne(Long id) {
        return null;
    }

    @Override
    public CiterneDto getCiterne(Long id) {
        return CiterneMapper.fromEntity(citerneRepository.findById(id).orElse(null));
    }

    @Override
    public List<CiterneDto> getAllCiterne() {
        List<Citerne> citernes = citerneRepository.findAll();
        return CiterneMapper.fromEntityList(citernes);
    }
}
