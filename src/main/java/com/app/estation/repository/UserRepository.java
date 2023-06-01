package com.app.estation.repository;

import com.app.estation.entity.Station;
import com.app.estation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);


    @Query("SELECT u FROM User u JOIN u.stations su " +
            "WHERE su.station = :station " +
            "AND su.date_debut = (" +
            "   SELECT MAX(su2.date_debut) FROM StationUser su2 " +
            "   WHERE su2.user = u " +
            "   AND su2.station = :station" +
            ")")
    List<User> findUsersByStation(@Param("station") Station station);
}
