package com.app.estation.service.implementation;

import com.app.estation.advice.ApiRequestException;
import com.app.estation.dto.CiterneDto;
import com.app.estation.dto.PompeDto;
import com.app.estation.entity.Citerne;
import com.app.estation.entity.Pompe;
import com.app.estation.entity.Produit;
import com.app.estation.mappers.CiterneMapper;
import com.app.estation.mappers.PompeMapper;
import com.app.estation.mappers.ProduitMapper;
import com.app.estation.repository.CiterneRepository;
import com.app.estation.repository.PompeRepository;
import com.app.estation.repository.ProduitRepository;
import com.app.estation.service.CiterneService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CiterneServiceImpl implements CiterneService {

    @Autowired
    private CiterneRepository citerneRepository;
    @Autowired
    private PompeServiceImpl pompeService;
    @Autowired
    private ProduitRepository produitRepository;
    @Override
    public CiterneDto addCiterne(CiterneDto dto) {
        Produit produit = produitRepository.findById(dto.getProduit().getId_produit()).orElse(null);
        if (null == produit){
            throw new ApiRequestException("produit_does_not_exist");
        }
        Citerne citerne = CiterneMapper.toEntity(dto);
        citerne.setId_produit(produit);
        citerneRepository.save(citerne);
        System.out.println(citerneRepository.findById(citerne.getId_citerne()).get());
        return CiterneMapper.fromEntity(citerneRepository.findById(citerne.getId_citerne()).orElse(null));
    }

    @Override
    public CiterneDto updateCiterne(CiterneDto dto) {
        Citerne citerne = CiterneMapper.toEntity(dto);
        citerneRepository.save(citerne);
        return CiterneMapper.fromEntity(citerneRepository.findById(citerne.getId_citerne()).orElse(null));
    }

    public CiterneDto setPompes(List<PompeDto> pompeDtos, Long id){
        List<Pompe> pompes = new ArrayList<>();
        PompeDto pompe = null;
        for (PompeDto dto : pompeDtos) {
            pompe = pompeService.getPompe(dto.getId_pompe());
            if (pompe == null){
                return null;
            }
            pompes.add(PompeMapper.toEntity(pompe));
        }
        Citerne citerne = citerneRepository.findById(id).orElse(null);
        if (citerne == null){
            return null;
        }
        citerne.setPompes(pompes);
        citerneRepository.save(citerne);
        return CiterneMapper.fromEntity(citerneRepository.findById(id).orElse(null));
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
