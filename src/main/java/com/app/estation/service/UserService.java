package com.app.estation.service;

import com.app.estation.dto.UserDto;
import com.app.estation.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User getUser(Long id);
    User getUserByToken(String token);
    User addUser(UserDto user);
    User updateUser(Long id, UserDto user);
    boolean deleteUser(Long id);

}
