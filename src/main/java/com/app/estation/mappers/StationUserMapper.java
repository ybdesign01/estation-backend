package com.app.estation.mappers;

import com.app.estation.dto.StationUserDto;
import com.app.estation.entity.StationUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {StationMapper.class, UserMapper.class})
public interface StationUserMapper {

    StationUserMapper INSTANCE = Mappers.getMapper(StationUserMapper.class);

    StationUserDto stationUserToStationUserDto(StationUser stationUser);
    StationUser stationUserDtoToStationUser(StationUserDto stationUserDto);
    List<StationUser> stationUserDtosToStationUsers(List<StationUserDto> stationUserDtos);
    List<StationUserDto> stationUsersToStationUserDtos(List<StationUser> stationUsers);
}
