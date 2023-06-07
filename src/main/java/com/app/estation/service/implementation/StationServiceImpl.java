package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.CarburantDto;
import com.app.estation.dto.CiterneJaugageDto;
import com.app.estation.dto.StationDto;
import com.app.estation.dto.StationInformationDto;
import com.app.estation.entity.Citerne;
import com.app.estation.entity.Produit;
import com.app.estation.entity.Services;
import com.app.estation.entity.Station;
import com.app.estation.mappers.ServicesMapper;
import com.app.estation.mappers.StationMapper;
import com.app.estation.repository.*;
import com.app.estation.service.EServices;
import com.app.estation.util.FullnessCalculator;
import com.app.estation.util.PriceCalculator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class StationServiceImpl implements EServices<StationDto, StationDto> {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private HistoriquePrixRepository historiquePrixRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private TransactionGroupRepository transactionGroupRepository;

    @Autowired
    private CiterneRepository citerneRepository;



    @Override
    public List<StationDto> getAll() {
        List<Station> stations = stationRepository.findAll();
        if (stations.isEmpty()) {
            throw new EntityNotFoundException("no_station_found");
        }else{
            return StationMapper.fromEntityList(stations);
        }
    }

    @Override
    public StationDto get(Long id) {
        return StationMapper.fromEntity(stationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("station_not_found")));
    }

    @Override
    public StationDto add(StationDto station) {
            Station station1 = StationMapper.toEntity(station);
            Set<Services> servicesSet = new HashSet<>();
            Set<Services> servs = station1.getServices();
            stationRepository.save(station1);
            if (servs != null){
            for (Services serv : servs) {
                serv.setId(null);
                serv.setStation(station1);
                Services s = serviceRepository.save(serv);
                s.setStation(station1);
                servicesSet.add(s);
            }
            station1.setServices(servicesSet);
        }
            return StationMapper.fromEntity(stationRepository.findById(station1.getId()).orElseThrow(() -> new EntityNotFoundException("station_not_found")));
    }



    @Override
    public StationDto update(StationDto dto, Long id) {
        Station station = stationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("station_not_found"));
        station.setId(dto.getId());
        station.setNom_station(dto.getNom_station());
        station.setAdresse(dto.getAdresse());
        station.setAdresse(dto.getAdresse());
        station.setServices(ServicesMapper.toEntitySet(dto.getServices()));
        stationRepository.save(station);
        return StationMapper.fromEntity(stationRepository.findById(station.getId()).orElseThrow(() -> new EntityNotFoundException("station_not_updated")));
    }

    @Override
    public boolean delete(Long id) {
        StationDto station = StationMapper.fromEntity(stationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("station_not_found")));
            stationRepository.deleteById(id);
            return stationRepository.existsById(id);
    }

    public StationInformationDto getStationInformation(Long id){
    Station station = stationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("station_not_found"));
    Services services = serviceRepository.findByNom("CARBURANTS",station.getId()).orElseThrow(() -> new EntityNotFoundException("service_not_found"));
    List<Produit> produits = produitRepository.findAllByStationAndType(services.getId()).orElseThrow(() -> new EntityNotFoundException("no_carburants_found"));
    List<CarburantDto> carburants = new ArrayList<>();
    List<Double> prix = new ArrayList<>();

    for (Produit produit : produits) {
        CarburantDto carburantDto = new CarburantDto();
        carburantDto.setNomCarburant(produit.getNom_produit());
        carburantDto.setPrixCarburant(produit.getPrix_vente());
        prix = historiquePrixRepository.findPreviousPrixVente(produit.getId_produit());
        if(prix != null && !prix.isEmpty()){
            carburantDto.setPercentChange(PriceCalculator.calculatePercentageDifference(prix.get(0),produit.getPrix_vente()));
        }else {
            carburantDto.setPercentChange(String.valueOf(0));
        }
        carburants.add(carburantDto);
    }
    StationInformationDto stationInformationDto = new StationInformationDto();
    stationInformationDto.setCarburant(carburants);
    Double chiffre = transactionGroupRepository.calculateTotalMontantByTypeAndDate(LocalDate.now(),station.getId());
    if (chiffre == null){
        chiffre = 0.0;
    }
    stationInformationDto.setChiffreToday(chiffre);
        List<Citerne> citernes = citerneRepository.findAllByStation(station);
        List<CiterneJaugageDto> citerneJaugageDtos = new ArrayList<>();
        for (Citerne citerne : citernes) {
            Double fullnessPercentage = FullnessCalculator.calculateFullnessPercentage(
                    citerne.getCapaciteActuelle(), citerne.getCapaciteMaximale());
            CiterneJaugageDto citerneJaugageDto = new CiterneJaugageDto();
            citerneJaugageDto.setNomCiterne(citerne.getNom_citerne());
            citerneJaugageDto.setJaugage(citerne.getCapaciteActuelle());
            citerneJaugageDto.setNomProduit(citerne.getId_produit().getNom_produit());
            citerneJaugageDto.setPercentageLevel(fullnessPercentage.toString());
            citerneJaugageDtos.add(citerneJaugageDto);
        }
        stationInformationDto.setCiterneJaugage(citerneJaugageDtos);
    return stationInformationDto;
    }


}
