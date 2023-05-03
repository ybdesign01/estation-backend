package com.app.estation.mappers;

import com.app.estation.dto.ServicesDto;
import com.app.estation.entity.Services;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ServicesMapper {

    ServicesMapper INSTANCE = Mappers.getMapper(ServicesMapper.class);

    @Mapping(target = "stations.services", ignore = true)
    ServicesDto servicesToServicesDto(Services services);
    @Mapping(target = "stations.services", ignore = true)
    Services servicesDtoToServices(ServicesDto servicesDto);
    @Mapping(target = "stations.services", ignore = true)
    List<ServicesDto> servicesListToServicesDtos(List<Services> services);
    @Mapping(target = "stations.services", ignore = true)
    List<ServicesDto> servicesListToServicesDtosWithStations(List<Services> services);

    @Mapping(target = "stations.services", ignore = true)
    List<Services> servicesDtosListToServices(List<ServicesDto> servicesDtos);



}
