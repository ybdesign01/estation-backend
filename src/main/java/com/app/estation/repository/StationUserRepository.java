package com.app.estation.repository;

import com.app.estation.entity.Station;
import com.app.estation.entity.StationUser;
import com.app.estation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StationUserRepository extends JpaRepository<StationUser, Long> {

    List<StationUser> findAllByUser(User user);

    @Query("SELECT su FROM StationUser su WHERE su.user.id_user = :userId " +
            "AND su.date_debut = (SELECT MAX(su2.date_debut) FROM StationUser su2 WHERE su2.user.id_user = :userId)")
    Optional<StationUser> findLatestStationUserByUserId(Long userId);

    List<StationUser> findAllByStation(Station station);



}
