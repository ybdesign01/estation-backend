package com.app.estation.mappers;

import com.app.estation.dto.ProfileDto;
import com.app.estation.dto.UserDto;
import com.app.estation.dto.UserPassDto;
import com.app.estation.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto fromEntity(User user) {
        if (user == null) return null;
        final UserDto userDto = new UserDto();
        userDto.setId_user(user.getId_user());
        userDto.setNom(user.getNom());
        userDto.setPrenom(user.getPrenom());
        userDto.setEmail(user.getEmail());
        userDto.setMatricule(user.getMatricule());
        userDto.setProfile(ProfileMapper.fromEntity(user.getProfile()));
        return userDto;
    }

    public static User toEntity(UserDto userDto) {
        if (userDto == null) return null;
        final User user = new User();
        user.setId_user(userDto.getId_user());
        user.setNom(userDto.getNom());
        user.setPrenom(userDto.getPrenom());
        user.setEmail(userDto.getEmail());
        user.setMatricule(userDto.getMatricule());
        user.setProfile(ProfileMapper.toEntity(userDto.getProfile()));
        return user;
    }

    public static UserPassDto fromEntityPass(User user) {
        if (user == null) return null;
        final UserPassDto userDto = new UserPassDto();
        userDto.setId_user(user.getId_user());
        userDto.setNom(user.getNom());
        userDto.setPrenom(user.getPrenom());
        userDto.setEmail(user.getEmail());
        userDto.setMatricule(user.getMatricule());
        userDto.setProfile(ProfileMapper.fromEntity(user.getProfile()));
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    public static User toEntityPass(UserPassDto userDto) {
        if (userDto == null) return null;
        final User user = new User();
        user.setId_user(userDto.getId_user());
        user.setNom(userDto.getNom());
        user.setPrenom(userDto.getPrenom());
        user.setEmail(userDto.getEmail());
        user.setMatricule(userDto.getMatricule());
        user.setProfile(ProfileMapper.toEntity(userDto.getProfile()));
        user.setPassword(userDto.getPassword());
        return user;
    }

    public static UserDto fromUserPassDto(UserPassDto userPassDto) {
        if (userPassDto == null) return null;
        final UserDto userDto = new UserDto();
        userDto.setId_user(userPassDto.getId_user());
        userDto.setNom(userPassDto.getNom());
        userDto.setPrenom(userPassDto.getPrenom());
        userDto.setEmail(userPassDto.getEmail());
        userDto.setMatricule(userPassDto.getMatricule());
        userDto.setProfile(userPassDto.getProfile());
        return userDto;
    }


    public static List<UserDto> fromEntityList(List<User> users) {
        if (users == null) return null;
        return users.stream().map(UserMapper::fromEntity).collect(Collectors.toList());
    }

    public static List<User> toEntityList(List<UserDto> userDtos) {
        if (userDtos == null) return null;
        return userDtos.stream().map(UserMapper::toEntity).collect(Collectors.toList());
    }


}
