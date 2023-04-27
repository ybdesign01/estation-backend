package com.app.estation.mappers;

import com.app.estation.dto.ProfileDto;
import com.app.estation.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    ProfileDto profileToProfileDto(Profile profile);
    Profile profileDtoToProfile(ProfileDto profileDto);



}
