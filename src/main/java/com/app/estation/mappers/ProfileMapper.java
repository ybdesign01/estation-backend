package com.app.estation.mappers;

import com.app.estation.dto.ProfileDto;
import com.app.estation.entity.Profile;

import java.util.List;

public class ProfileMapper {

    public static ProfileDto fromEntity(Profile profile){
        if (profile == null) return null;
        ProfileDto profileDto = new ProfileDto();
        profileDto.setId_profile(profile.getId_profile());
        profileDto.setNom(profile.getNom());
        profileDto.setDescription(profile.getDescription());
        return profileDto;
    }

    public static Profile toEntity(ProfileDto profileDto){
        if (profileDto == null) return null;
        Profile profile = new Profile();
        profile.setId_profile(profileDto.getId_profile());
        profile.setNom(profileDto.getNom());
        profile.setDescription(profileDto.getDescription());
        return profile;
    }

    public static List<ProfileDto> fromEntityList(List<Profile> profiles){
        if (profiles == null) return null;
        return profiles.stream().map(ProfileMapper::fromEntity).collect(java.util.stream.Collectors.toList());
    }

    public static List<Profile> toEntityList(List<ProfileDto> profileDtos){
        if (profileDtos == null) return null;
        return profileDtos.stream().map(ProfileMapper::toEntity).collect(java.util.stream.Collectors.toList());
    }
}
