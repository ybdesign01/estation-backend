package com.app.estation.service;

import com.app.estation.dto.ReleveDto;
import com.app.estation.entity.Releve;

import java.util.List;

public interface ReleveService {

    List<ReleveDto> getAll();

    ReleveDto getReleve(Long id);

    boolean addReleve(ReleveDto releve);

    boolean updateReleve(Long id, ReleveDto releve);

    boolean deleteReleve(Long id);


}
