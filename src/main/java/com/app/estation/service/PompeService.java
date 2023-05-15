package com.app.estation.service;

import com.app.estation.dto.PompeDto;
import com.app.estation.entity.Pompe;

import java.util.List;

public interface PompeService {

    PompeDto addPompe(PompeDto dto);
    PompeDto updatePompe(PompeDto dto);
    boolean deletePompe(Long id);
    List<PompeDto> getAllPompes();

    PompeDto getPompe(Long id);


}
