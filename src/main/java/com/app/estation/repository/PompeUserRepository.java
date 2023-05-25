package com.app.estation.repository;

import com.app.estation.entity.PompeUser;
import com.app.estation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PompeUserRepository extends JpaRepository<PompeUser, Long> {
    List<PompeUser> findAllByUser(User user);

    @Query("SELECT COUNT(pu) FROM PompeUser pu WHERE pu.user.id_user = :userId AND DATE(pu.dateDebut) = DATE(:dateTime)")
    int countPompesAssignedToUserOnDate(@Param("userId") Long userId, @Param("dateTime") LocalDateTime dateTime);


    @Query("SELECT COUNT(pu) FROM PompeUser pu WHERE pu.pompe.id_pompe = :pompeId " +
            "AND ((pu.dateDebut <= :endDateTime AND pu.dateFin >= :startDateTime) " +
            "OR (pu.dateDebut >= :startDateTime AND pu.dateDebut <= :endDateTime) " +
            "OR (pu.dateFin >= :startDateTime AND pu.dateFin <= :endDateTime))")
    int countPompesAssignedDuringTimeRange(Long pompeId, LocalDateTime startDateTime, LocalDateTime endDateTime);


    @Query("SELECT pu FROM PompeUser pu WHERE pu.user.id_user = :userId AND DATE(pu.dateDebut) = DATE(:dateTime)")
    List<PompeUser> countPompesAssignedToUserToday(@Param("userId") Long userId, @Param("dateTime") LocalDateTime dateTime);



    @Query("SELECT pu FROM PompeUser pu WHERE pu.user.id_user = :userId " +
            "AND ((pu.dateDebut <= :startOfDay AND pu.dateFin >= :startOfDay) " +
            "OR (pu.dateDebut >= :startOfDay AND pu.dateFin <= :endOfDay) " +
            "OR (pu.dateDebut <= :endOfDay AND pu.dateFin >= :endOfDay))" +
            "AND pu.dateFin <= :currentTimestamp" )
    List<PompeUser> getPompesAssignedToUserForDay(Long userId, LocalDateTime startOfDay, LocalDateTime endOfDay, LocalDateTime currentTimestamp);

}
