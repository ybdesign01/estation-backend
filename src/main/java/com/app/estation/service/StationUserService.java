package com.app.estation.service;

import com.app.estation.dto.User.StationUserDto;
import com.app.estation.dto.User.StationUserKeyDto;

public interface StationUserService extends EServices<StationUserDto,StationUserDto> {


    boolean delete(StationUserDto stationUserDto);

    StationUserDto get(StationUserKeyDto key);




}
