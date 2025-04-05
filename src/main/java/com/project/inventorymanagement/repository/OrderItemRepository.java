package com.project.inventorymanagement.repository;

import com.project.inventorymanagement.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

    @Query("SELECT COALESCE(SUM(oi.discount), 0) FROM OrderItemEntity oi")
    Float findTotalDiscount();

    @Query("SELECT COALESCE(AVG(oi.discount), 0) FROM OrderItemEntity oi")
    Float findAverageDiscount();

    List<OrderItemEntity> findByProductId(Long productId);

    @Query("SELECT COUNT(DISTINCT oi.order.customer.id) FROM OrderItemEntity oi WHERE oi.product.id = :productId")
    int countDistinctCustomerByProductId(@Param("productId") Long productId);

}
