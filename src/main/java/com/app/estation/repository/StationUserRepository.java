package com.app.estation.repository;

import com.app.estation.entity.Station;
import com.app.estation.entity.StationUser;
import com.app.estation.entity.User;
import com.app.estation.entity.keys.StationUserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StationUserRepository extends JpaRepository<StationUser, StationUserKey> {

    List<StationUser> findAllByUser(User user);


    List<StationUser> findAllByStation(Station station);



}
