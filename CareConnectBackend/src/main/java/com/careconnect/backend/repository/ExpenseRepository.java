package com.careconnect.backend.repository;

import com.careconnect.backend.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByOrphanageId(Long orphanageId);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.orphanage.id = :orphanageId")
    BigDecimal getTotalExpensesByOrphanageId(@Param("orphanageId") Long orphanageId);
}
