package com.app.estation.service;


import com.app.estation.entity.User;
import com.app.estation.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return (List<User>) userRepository.findAll();
    }

    public User getUser(Long id) {
        Optional<User> result = userRepository.findById(id);
        return result.orElse(null);

    }
}
