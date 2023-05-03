package com.app.estation.mappers;

import com.app.estation.dto.StationDto;
import com.app.estation.entity.Station;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(uses = ServicesMapper.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface StationMapper {

    StationMapper INSTANCE = Mappers.getMapper(StationMapper.class);
    @Mapping(target = "services.stations", ignore = true)
    StationDto stationToStationDto(Station station);
    @Mapping(target = "services.stations", ignore = true)
    Station stationDtoToStation(StationDto stationDto);
    @Mapping(target = "services.stations", ignore = true)
    Set<StationDto> stationSetToStationDtoSet(List<Station> stations);
    @Mapping(target = "services.stations", ignore = true)
    List<Station> stationDtosListToStations(List<StationDto> stationDtos);



}
