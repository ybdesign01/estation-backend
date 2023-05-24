package com.app.estation.repository;

import com.app.estation.entity.Releve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReleveRepository extends JpaRepository<Releve, Long> {

    List<Releve> findAllByPompeUserIdPompeUser(Long idPompeUser);

    List<Releve> getReleveByPompeUserIdPompeUser(Long idPompeUser);


}
