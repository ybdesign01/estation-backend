package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.ApiRequestException;
import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.CiterneDto;
import com.app.estation.entity.Citerne;
import com.app.estation.entity.Pompe;
import com.app.estation.entity.Produit;
import com.app.estation.entity.Station;
import com.app.estation.mappers.CiterneMapper;
import com.app.estation.repository.CiterneRepository;
import com.app.estation.repository.PompeRepository;
import com.app.estation.repository.ProduitRepository;
import com.app.estation.repository.StationRepository;
import com.app.estation.service.EServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CiterneServiceImpl implements EServices<CiterneDto, CiterneDto> {

    @Autowired
    private CiterneRepository citerneRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private PompeRepository pompeRepository;

    @Override
    public CiterneDto add(CiterneDto dto) {
        Produit produit = produitRepository.findById(dto.getProduit().getId_produit()).orElse(null);
        if (null == produit){
            throw new ApiRequestException("produit_does_not_exist");
        }
        Station station = stationRepository.findById(dto.getStation().getId()).orElse(null);
        if (null == station){
            throw new ApiRequestException("station_does_not_exist");
        }
        Citerne citerne = CiterneMapper.toEntity(dto);
        citerne.setId_produit(produit);
        citerne.setStation(station);
        citerneRepository.save(citerne);
        return CiterneMapper.fromEntity(citerneRepository.findById(citerne.getId_citerne()).orElse(null));
    }


    @Override
    public CiterneDto update(CiterneDto dto) {
        Citerne citerne = CiterneMapper.toEntity(dto);
        citerneRepository.save(citerne);
        return CiterneMapper.fromEntity(citerneRepository.findById(citerne.getId_citerne()).orElse(null));
    }

    @Transactional
    public CiterneDto setPompes(List<Long> pompeDtos, Long id){
        List<Pompe> pompes = new ArrayList<>();
        Pompe pompe = null;
        Citerne citerne = citerneRepository.findById(id).orElse(null);

        for (Long pompeId : pompeDtos) {
            pompe = pompeRepository.findById(pompeId).orElse(null);
            if (pompe == null){
                throw new EntityNotFoundException("pompe_not_found");
            }
            pompe.addCiternetoList(citerne);
            pompes.add(pompe);
        }

        if (citerne == null){
            throw new EntityNotFoundException("citerne_not_found");
        }
        citerne.setPompes(pompes);
        System.out.println("citerne pompes" + citerne.getPompes());

        citerneRepository.save(citerne);
        System.out.println("citerne pompes" + citerneRepository.findById(id));
        return CiterneMapper.fromEntity(citerneRepository.findById(id).orElseThrow(() -> new ApiRequestException("pompes_not_set")));
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public CiterneDto get(Long id) {
        return CiterneMapper.fromEntity(citerneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("citerne_not_found")));
    }

    @Override
    public List<CiterneDto> getAll() {
        List<Citerne> citernes = citerneRepository.findAll();
        if (citernes.isEmpty()){
            throw new EntityNotFoundException("no_citerne_found");
        }
        return CiterneMapper.fromEntityList(citernes);
    }
}
