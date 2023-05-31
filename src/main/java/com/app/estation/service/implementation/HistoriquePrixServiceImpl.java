package com.app.estation.service.implementation;

import com.app.estation.dto.HistoriquePrixDto;
import com.app.estation.entity.HistoriquePrix;
import com.app.estation.entity.Produit;
import com.app.estation.repository.HistoriquePrixRepository;
import com.app.estation.service.EServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoriquePrixServiceImpl implements EServices<HistoriquePrixDto,HistoriquePrixDto> {

    @Autowired
    private HistoriquePrixRepository historiquePrixRepository;

    @Override
    public HistoriquePrixDto add(HistoriquePrixDto request) {
        return null;
    }

    @Override
    public HistoriquePrixDto get(Long id) {
        return null;
    }

    public HistoriquePrix getByIdProduit(Produit produit){
        return historiquePrixRepository.findByMaxDateDebut(produit).orElse(null);
    }




    @Override
    public HistoriquePrixDto update(HistoriquePrixDto request, Long id) {
        return null;
    }

    public boolean updateAndAdd(Produit produit){
        HistoriquePrix hs = getByIdProduit(produit);
        hs.setDateFin(java.time.LocalDateTime.now());
        historiquePrixRepository.save(hs);
        HistoriquePrix historiquePrix = new HistoriquePrix();
        historiquePrix.setPrixAchat(produit.getPrix_achat());
        historiquePrix.setPrixVente(produit.getPrix_vente());
        historiquePrix.setDateDebut(java.time.LocalDateTime.now());
        historiquePrix.setIdProduit(hs.getIdProduit());
        historiquePrixRepository.save(historiquePrix);
        return historiquePrixRepository.existsById(hs.getIdHistoriquePrix());
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<HistoriquePrixDto> getAll() {
        return null;
    }
}
