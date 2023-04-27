package com.app.estation.mappers;

import com.app.estation.dto.UserDto;
import com.app.estation.dto.UserPassDto;
import com.app.estation.entity.Profile;
import com.app.estation.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ProfileMapper.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    UserPassDto userToUserPassDto(User user);
    User userPassDtoToUser(UserPassDto userPassDto);
    List<UserDto> usersToUserDtos(List<User> users);
    List<User> userDtosToUsers(List<UserDto> userDtos);



}
