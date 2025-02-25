package com.project.inventorymanagement.repository;

import com.project.inventorymanagement.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    @Query("SELECT e.category AS category, SUM(e.amount) AS total FROM ExpenseEntity e GROUP BY e.category")
    List<Map<String, Object>> getExpenseBreakdownByCategory();

    List<ExpenseEntity> findExpensesByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);
}
