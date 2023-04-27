package com.app.estation.mappers;

import com.app.estation.dto.ServicesDto;
import com.app.estation.entity.Services;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ServicesMapper {

    ServicesMapper INSTANCE = Mappers.getMapper(ServicesMapper.class);

    ServicesDto servicesToServicesDto(Services services);
    Services servicesDtoToServices(ServicesDto servicesDto);

    List<ServicesDto> servicesListToServicesDtos(List<Services> services);

    List<Services> servicesDtosListToServices(List<ServicesDto> servicesDtos);

}
