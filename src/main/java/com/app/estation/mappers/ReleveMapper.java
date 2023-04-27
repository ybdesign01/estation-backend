package com.app.estation.mappers;

import com.app.estation.dto.ReleveDto;
import com.app.estation.entity.Releve;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface ReleveMapper {

    ReleveMapper INSTANCE = Mappers.getMapper(ReleveMapper.class);

    ReleveDto releveToReleveDto(Releve releve);

    Releve releveDtoToReleve(ReleveDto releveDto);

    List<Releve> releveDtoListToReleveList(List<ReleveDto> releveDtoList);

    List<ReleveDto> releveListToReleveDtoList(List<Releve> releveList);




}
