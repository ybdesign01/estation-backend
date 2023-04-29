package com.app.estation.mappers;


import com.app.estation.dto.CiterneDto;
import com.app.estation.entity.Citerne;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {PompeMapper.class})
public interface CiterneMapper {

    CiterneMapper INSTANCE = Mappers.getMapper(CiterneMapper.class);

    Citerne citerneDtoToCiterne(CiterneDto citerne);
    CiterneDto citerneToCiterneDto(Citerne citerne);

    List<Citerne> citerneDtosToCiternes(List<CiterneDto> citerneDtos);

    List<CiterneDto> citernesToCiterneDtos(List<Citerne> citernes);


}
