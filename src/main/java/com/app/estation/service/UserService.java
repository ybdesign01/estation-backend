package com.app.estation.service;

import com.app.estation.dto.User.UserDto;
import com.app.estation.dto.User.UserPassDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();
    UserDto getUser(Long id);
    UserDto getUserByToken(String token);
    UserDto addUser(UserPassDto user);
    UserDto updateUser(Long id, UserPassDto user);
    boolean deleteUser(Long id);

}
