package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.CiterneDto;
import com.app.estation.entity.Citerne;
import com.app.estation.entity.Produit;
import com.app.estation.entity.Station;
import com.app.estation.mappers.CiterneMapper;
import com.app.estation.repository.CiterneRepository;
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



    @Override
    public CiterneDto add(CiterneDto dto) {
        Produit produit = produitRepository.findById(dto.getProduit().getId_produit()).orElseThrow(() -> new EntityNotFoundException("produit_not_found"));

        Station station = stationRepository.findById(dto.getStation().getId()).orElseThrow(() -> new EntityNotFoundException("station_not_found"));
        if (produit.getId_service().getStation() != station){
            throw new EntityNotFoundException("station_different_from_produit_station");
        }
        Citerne citerne = CiterneMapper.toEntity(dto);
        citerne.setId_produit(produit);
        citerne.setStation(station);
        if (citerne.getCapaciteActuelle() == null){
            citerne.setCapaciteActuelle(citerne.getCapaciteMaximale());
        } else if (citerne.getCapaciteActuelle() > citerne.getCapaciteMaximale()){
            throw new EntityNotFoundException("capacite_actuelle_greater_than_capacite_maximale");
        }
        citerneRepository.save(citerne);
        return CiterneMapper.fromEntity(citerneRepository.findById(citerne.getId_citerne()).orElseThrow(() -> new EntityNotFoundException("citerne_not_added")));
    }

    @Override
    public boolean delete(Long id) {
        Citerne citerne = citerneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("citerne_not_found"));
        citerneRepository.deleteById(citerne.getId_citerne());
        return citerneRepository.existsById(id);
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
        citerne.setCapaciteMaximale(request.getCapaciteMaximale());
        if(request.getCapaciteActuelle() != null){
             if (request.getCapaciteActuelle() > request.getCapaciteMaximale()){
            throw new EntityNotFoundException("capacite_actuelle_greater_than_capacite_maximale");
        }
        }
        citerne.setId_produit(produit);
        citerne.setNom_citerne(request.getNom_citerne());
        citerneRepository.save(citerne);
        return CiterneMapper.fromEntity(citerneRepository.findById(citerne.getId_citerne()).orElseThrow(() -> new EntityNotFoundException("citerne_not_updated")));
    }

    @Override
    public List<CiterneDto> getAll() {
        List<Citerne> citernes = citerneRepository.findAll();
        if (citernes.isEmpty()){
            throw new EntityNotFoundException("no_citerne_found");
        }
        return CiterneMapper.fromEntityList(citernes);
    }

    public List<CiterneDto> getAllByStation(Long id) {
        Station station = stationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("station_not_found"));
        List<Citerne> citernes = citerneRepository.findAllWithLatestPompesByStation(station);
        if (citernes.isEmpty()){
            throw new EntityNotFoundException("no_citerne_found");
        }
        return CiterneMapper.fromEntityList(citernes);
    }

}
