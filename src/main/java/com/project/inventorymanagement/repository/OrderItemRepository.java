package com.project.inventorymanagement.repository;

import com.project.inventorymanagement.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

    @Query("SELECT COALESCE(SUM(oi.discount), 0) FROM OrderItemEntity oi")
    Float findTotalDiscount();

    @Query("SELECT COALESCE(AVG(oi.discount), 0) FROM OrderItemEntity oi")
    Float findAverageDiscount();
}
