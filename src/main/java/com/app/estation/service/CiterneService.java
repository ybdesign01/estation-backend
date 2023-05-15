package com.app.estation.service;

import com.app.estation.dto.CiterneDto;
import com.app.estation.entity.Citerne;

import java.util.List;

public interface CiterneService {

    CiterneDto addCiterne(CiterneDto dto);
    CiterneDto updateCiterne(CiterneDto dto);
    CiterneDto deleteCiterne(Long id);
    CiterneDto getCiterne(Long id);

    List<CiterneDto> getAllCiterne();
}
