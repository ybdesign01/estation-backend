package com.app.estation.mappers;

import com.app.estation.dto.PompeUserDto;
import com.app.estation.entity.PompeUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {PompeMapper.class, UserMapper.class})
public interface PompeUserMapper {

    PompeUserMapper INSTANCE = Mappers.getMapper(PompeUserMapper.class);
    PompeUser pompeUserDtoToPompeUser(PompeUserDto pompeUser);
    PompeUserDto pompeUserToPompeUserDto(PompeUser pompeUser);
    List<PompeUser> pompeUserDtosToPompeUsers(List<PompeUserDto> pompeUserDtos);
    List<PompeUserDto> pompeUsersToPompeUserDtos(List<PompeUser> pompeUsers);

}
