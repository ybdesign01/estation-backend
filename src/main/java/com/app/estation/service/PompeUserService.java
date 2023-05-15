package com.app.estation.service;

import com.app.estation.dto.User.PompeUserDto;
import com.app.estation.dto.User.PompeUserKeyDto;

import java.util.List;

public interface PompeUserService {

    PompeUserDto addPompeUser(PompeUserDto dto);
    PompeUserDto updatePompeUser(PompeUserDto dto);
    PompeUserDto deletePompeUser(PompeUserKeyDto id);
    PompeUserDto getPompeUser(PompeUserKeyDto id);
    List<PompeUserDto> getAllPompeUsers();

}
