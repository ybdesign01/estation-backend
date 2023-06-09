package com.app.estation.service.implementation;

import com.app.estation.advice.exceptions.EntityNotFoundException;
import com.app.estation.dto.*;
import com.app.estation.entity.*;
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
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Autowired
    private TypeProduitRepository typeProduitRepository;

    @Autowired
    private ProduitActionRepository produitActionRepository;



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

    public DashboardInfoDto getDashboardInfo(Long id, DashboardRequest dashboardRequestDto){
        DashboardInfoDto dashboardInfoDto = new DashboardInfoDto();
        Station station = stationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("station_not_found"));
        Services services = serviceRepository.findByNom("CARBURANTS",station.getId()).orElseThrow(() -> new EntityNotFoundException("service_not_found"));
        List<Produit> produits = produitRepository.findAllByStationAndType(services.getId()).orElseThrow(() -> new EntityNotFoundException("no_carburants_found"));
        List<CarburantPriceDto> carburants = new ArrayList<>();
        for (Produit produit : produits) {
            CarburantPriceDto carburantDto = new CarburantPriceDto();
            List<HistoriquePrix> historiquePrix = historiquePrixRepository.findAllByIdProduitAndDateDebut(produit, dashboardRequestDto.getDateDebut(),dashboardRequestDto.getDateFin());
            carburantDto.setNomCarburant(produit.getNom_produit());
            carburantDto.setPrixActuel(produit.getPrix_vente());
            List<Double> prices = new ArrayList<>();
            List<LocalDateTime> dates = new ArrayList<>();
            if (!historiquePrix.isEmpty()) {
                Double prix = 0.0;
                if (1 < historiquePrix.size()){
                    prix = historiquePrix.get(1).getPrixVente();
                }else {
                    prix = historiquePrix.get(0).getPrixVente();
                }

                System.out.println(prix);
                System.out.println(produit.getPrix_vente());
                if (prix == null) {
                    carburantDto.setPercentChange(String.valueOf(0));
                } else {
                    carburantDto.setPercentChange(PriceCalculator.calculatePercentageDifference(prix, produit.getPrix_vente()));
                }
                historiquePrix.forEach(historiquePrix1 -> {
                    if (null != historiquePrix1.getPrixVente()) {
                        prices.add(historiquePrix1.getPrixVente());
                    }
                });
                historiquePrix.forEach(historiquePrix1 -> {
                    if (null != historiquePrix1.getDateDebut()) {
                        dates.add(historiquePrix1.getDateDebut());
                    }
                });
            }
                carburantDto.setPrixCarburant(prices);
                carburantDto.setDates(dates);
                carburants.add(carburantDto);
        }
        dashboardInfoDto.setCarburants(carburants);
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
        dashboardInfoDto.setCiterneJaugage(citerneJaugageDtos);

        List<ProduitAction> actions = produitActionRepository.findAllOrderByDate(station.getId());
        List<ActionPriceDto> actionPriceDtos = new ArrayList<>();
        for (ProduitAction action : actions) {
            ActionPriceDto actionPriceDto = new ActionPriceDto();
            actionPriceDto.setNomProduit(action.getProduit().getNom_produit());
            actionPriceDto.setDate(action.getDate_action());
            actionPriceDto.setTypeAction(action.getAction());
            actionPriceDto.setQuantite(action.getQuantite());
            actionPriceDtos.add(actionPriceDto);
        }
        dashboardInfoDto.setActions(actionPriceDtos);

        List<TransactionGroup> transactionGroups = transactionGroupRepository.findAllByDateAndStation(dashboardRequestDto.getDateDebut(), dashboardRequestDto.getDateFin(),station);
        transactionGroups.forEach(transactionGroup -> {
            if (transactionGroup.getIdProduitAction() != null){
                System.out.println(transactionGroup.getIdProduitAction().getAction());
            }
        });
        List<ActionDateDto> entrees = new ArrayList<>();
        List<ActionDateDto> sorties = new ArrayList<>();

        transactionGroups.forEach(transactionGroup -> {
            ActionDateDto actionDateDto = new ActionDateDto();
            actionDateDto.setMontant(transactionGroup.getMontantPaye());
            actionDateDto.setDate(transactionGroup.getDateTransaction());
            if (transactionGroup.getIdProduitAction() != null){
                if (TypeAction.ACTION_SORTIE == transactionGroup.getIdProduitAction().getAction()){
                    sorties.add(actionDateDto);
                }else {
                    entrees.add(actionDateDto);
                }
            }
        });
        Pattern pattern = Pattern.compile("\\d+");
        AtomicReference<Matcher> matcher = new AtomicReference<>();
        List<ProduitAction> produitActions = produitActionRepository.findEntreesByStation(dashboardRequestDto.getDateDebut(),dashboardRequestDto.getDateFin(),station);
        AtomicReference<Long> quantite = new AtomicReference<>(0L);
        produitActions.forEach(produitAction -> {
            ActionDateDto actionDateDto = new ActionDateDto();
            matcher.set(pattern.matcher(produitAction.getQuantite()));
            if (matcher.get().find()) {
                quantite.set(Long.parseLong(matcher.get().group()));
            }
            actionDateDto.setMontant(quantite.get() * produitAction.getProduit().getPrix_achat());
            actionDateDto.setDate(produitAction.getDate_action());
            entrees.add(actionDateDto);
        });

        dashboardInfoDto.setEntrees(entrees);
        dashboardInfoDto.setSorties(sorties);
        dashboardInfoDto.setEntree(PriceCalculator.calculateTotal(entrees));
        dashboardInfoDto.setSortie(PriceCalculator.calculateTotal(sorties));
        return dashboardInfoDto;
    }



    public List<CarburantChartDto> getCarburantChart(Long id, DashboardRequest dashboardRequestDto){
        Station station = stationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("station_not_found"));
        Services services = serviceRepository.findByNom("CARBURANTS",station.getId()).orElseThrow(() -> new EntityNotFoundException("service_not_found"));
        List<Produit> produits = produitRepository.findAllByStationAndType(services.getId()).orElseThrow(() -> new EntityNotFoundException("no_carburants_found"));
        List<CarburantChartDto> carburants = new ArrayList<>();
        for (Produit produit : produits) {
            CarburantChartDto carburantDto = new CarburantChartDto();
            List<HistoriquePrix> historiquePrix = historiquePrixRepository.findAllByIdProduitAndDateDebut(produit, dashboardRequestDto.getDateDebut(),dashboardRequestDto.getDateFin());
            carburantDto.setNomCarburant(produit.getNom_produit());
            Map<LocalDateTime, Double> prices = new HashMap<>();
            if (!historiquePrix.isEmpty()) {
                historiquePrix.forEach(historiquePrix1 -> {
                    if (null != historiquePrix1.getPrixVente()) {
                        prices.put(historiquePrix1.getDateDebut(), historiquePrix1.getPrixVente());
                    }
                });
            }
            List<Map.Entry<LocalDateTime, Double>> entryList = new ArrayList<>(prices.entrySet());
            entryList.sort(Map.Entry.comparingByKey());
            Map<LocalDateTime, Double> sortedMap = new LinkedHashMap<>();
            for (Map.Entry<LocalDateTime, Double> entry : entryList) {
                sortedMap.put(entry.getKey(), entry.getValue());
            }
            carburantDto.setPrixCarburant(sortedMap);
            carburants.add(carburantDto);
        }
        return carburants;
    }

    public boolean addCarburants(CarburantsSetupDto dto, Long id){
        Station station = stationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("station_not_found"));
        Services service = serviceRepository.findByNom("CARBURANTS",station.getId()).orElseThrow(() -> new EntityNotFoundException("service_carburants_not_found"));
         TypeProduit typeProduit = typeProduitRepository.save(new TypeProduit(null,"CARBURANT","Metre cube"));
         dto.getCarburants().forEach(carburantDto -> {
             Produit produit = new Produit();
             produit.setNom_produit(carburantDto.getNom_produit());
             produit.setPrix_achat(carburantDto.getPrix_achat());
             produit.setPrix_vente(carburantDto.getPrix_vente());
             produit.setId_service(service);
             produit.setType(typeProduit);
             produitRepository.save(produit);
         });
         return true;
    }


}
