package com.app.estation.mappers;

import com.app.estation.dto.StationDto;
import com.app.estation.entity.Station;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ServicesMapper.class, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface StationMapper {

    StationMapper INSTANCE = Mappers.getMapper(StationMapper.class);
    StationDto stationToStationDto(Station station);
    Station stationDtoToStation(StationDto stationDto);

    List<StationDto> stationListToStationDtos(List<Station> stations);

    List<Station> stationDtosListToStations(List<StationDto> stationDtos);



}
