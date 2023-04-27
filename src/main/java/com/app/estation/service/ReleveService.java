package com.app.estation.service;

import com.app.estation.dto.ReleveDto;
import com.app.estation.entity.Releve;

import java.util.List;

public interface ReleveService {

    List<Releve> getAll();

    Releve getReleve(Long id);

    Releve addReleve(ReleveDto releve);

    Releve updateReleve(Long id, ReleveDto releve);

    boolean deleteReleve(Long id);


}
