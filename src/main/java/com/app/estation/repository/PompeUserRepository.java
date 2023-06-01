package com.app.estation.repository;

import com.app.estation.entity.PompeUser;
import com.app.estation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PompeUserRepository extends JpaRepository<PompeUser, Long> {
    List<PompeUser> findAllByUser(User user);

    @Query("SELECT pu FROM PompeUser pu WHERE pu.user.id_user = :userId order by pu.dateDebut desc")
    Optional<List<PompeUser>> findAllOrderedByDateDebutDesc();

    @Query("SELECT COUNT(pu) FROM PompeUser pu WHERE pu.user.id_user = :userId AND DATE(pu.dateDebut) = DATE(:dateTime)")
    int countPompesAssignedToUserOnDate(@Param("userId") Long userId, @Param("dateTime") LocalDateTime dateTime);


    @Query("SELECT COUNT(pu) FROM PompeUser pu WHERE pu.pompe.id_pompe = :pompeId " +
            "AND ((:startDateTime >= pu.dateDebut AND :startDateTime < pu.dateFin) " +
            "OR (:endDateTime > pu.dateDebut AND :endDateTime <= pu.dateFin) " +
            "OR (:startDateTime <= pu.dateDebut AND :endDateTime >= pu.dateFin))")
    int countPompesAssignedDuringTimeRange(Long pompeId, LocalDateTime startDateTime, LocalDateTime endDateTime);


    @Query("SELECT pu FROM PompeUser pu WHERE pu.user.id_user = :userId AND DATE(pu.dateDebut) = DATE(:dateTime)")
    List<PompeUser> countPompesAssignedToUserToday(@Param("userId") Long userId, @Param("dateTime") LocalDateTime dateTime);



    @Query("SELECT pu FROM PompeUser pu WHERE pu.user.id_user = :userId " +
            "AND :currentTimestamp between pu.dateDebut AND pu.dateFin ")
    List<PompeUser> getPompesAssignedToUserForDay(Long userId, LocalDateTime currentTimestamp);

}
