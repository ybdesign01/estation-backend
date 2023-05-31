package com.app.estation.repository;

import com.app.estation.entity.PompeUser;
import com.app.estation.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByIdPompeUser(PompeUser idPompeUser);
    @Query("SELECT t FROM Transaction t WHERE t.idPompeUser.idPompeUser IN :pompeUserIds")
    List<Transaction> findTransactionsByExcludedPompeUserIds(@Param("pompeUserIds") List<Long> pompeUserIds);


}
