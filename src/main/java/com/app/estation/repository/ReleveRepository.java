package com.app.estation.repository;

import com.app.estation.entity.Releve;
import com.app.estation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReleveRepository extends JpaRepository<Releve, Long> {

    List<Releve> findAllByPompeUserIdPompeUser(Long idPompeUser);

    Optional<List<Releve>> findAllByPompeUserUser(User user);
    List<Releve> findAllByPompeUserIdPompeUserOrderByDateReleveAsc(Long idPompeUser);
    List<Releve> getReleveByPompeUserIdPompeUser(Long idPompeUser);

    @Query("SELECT r FROM Releve r WHERE r.pompeUser.pompe.id_pompe = :idPompe AND r.dateReleve = (SELECT MAX(r2.dateReleve) FROM Releve r2 WHERE r2.pompeUser.pompe.id_pompe = :idPompe)")
    Optional<Releve> getReleveByPompeId(Long idPompe);


}
