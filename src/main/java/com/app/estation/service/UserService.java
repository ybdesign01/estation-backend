package com.app.estation.service;

import com.app.estation.dto.UserDto;
import com.app.estation.dto.UserPassDto;
import com.app.estation.entity.User;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();
    UserDto getUser(Long id);
    UserDto getUserByToken(String token);
    UserDto addUser(UserPassDto user);
    UserDto updateUser(Long id, UserPassDto user);


    boolean deleteUser(Long id);

}
