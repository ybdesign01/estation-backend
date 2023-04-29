package com.app.estation.mappers;

import com.app.estation.dto.PompeDto;
import com.app.estation.entity.Pompe;
import com.app.estation.entity.PompeUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {CiterneMapper.class})
public interface PompeMapper {

    PompeMapper INSTANCE = Mappers.getMapper(PompeMapper.class);

    Pompe pompeDtoToPompe(PompeDto pompeDto);
    PompeDto pompeToPompeDto(Pompe pompe);

    List<Pompe> pompeDtosToPompes(List<PompeDto> pompeDtos);

    List<PompeDto> pompesToPompeDtos(List<Pompe> pompes);
}
