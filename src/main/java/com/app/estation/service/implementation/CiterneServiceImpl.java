package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.ApiRequestException;
import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.CiterneDto;
import com.app.estation.entity.Citerne;
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


/*    @Transactional
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
        citerneRepository.save(citerne);
        return CiterneMapper.fromEntity(citerneRepository.findById(id).orElseThrow(() -> new ApiRequestException("pompes_not_set")));
    }*/

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public CiterneDto get(Long id) {
        return CiterneMapper.fromEntity(citerneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("citerne_not_found")));
    }

    @Override
    public CiterneDto update(CiterneDto request, Long id) {
        Citerne citerne = citerneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("citerne_not_found"));
        Produit produit = produitRepository.findById(request.getProduit().getId_produit()).orElseThrow(() -> new EntityNotFoundException("produit_not_found"));
        Station st = stationRepository.findById(request.getStation().getId()).orElseThrow(() -> new EntityNotFoundException("station_not_found"));
        citerne.setStation(st);
        citerne.setCapacite(request.getCapacite());
        citerne.setId_produit(produit);
        citerne.setNom_citerne(request.getNom_citerne());
        citerneRepository.save(citerne);
        return CiterneMapper.fromEntity(citerneRepository.findById(citerne.getId_citerne()).orElse(null));
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
