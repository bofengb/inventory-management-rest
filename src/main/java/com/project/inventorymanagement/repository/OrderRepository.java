package com.project.inventorymanagement.repository;

import com.project.inventorymanagement.dto.OrderByStatusDTO;
import com.project.inventorymanagement.dto.OrderDetailsDTO;
import com.project.inventorymanagement.dto.OrderDiscountTrendDTO;
import com.project.inventorymanagement.entity.OrderEntity;
import com.project.inventorymanagement.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT new com.project.inventorymanagement.dto.OrderDiscountTrendDTO(" +
            "o.id, o.createdAt, o.totalAmount, COALESCE(SUM(oi.discount), 0.0)) " +
            "FROM OrderEntity o LEFT JOIN OrderItemEntity oi ON oi.order = o GROUP BY o.id")
    List<OrderDiscountTrendDTO> findOrderDiscountTrends();

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM OrderEntity o")
    Float findTotalOrderAmount();

    List<OrderEntity> findOrdersByCreatedAtBetweenAndStatus(LocalDateTime startDate, LocalDateTime endDate, OrderStatus status);

    @Query("select new com.project.inventorymanagement.dto.OrderDetailsDTO(" +
            "oi.id, o.totalAmount, o.status, p.name, c.name, pay.method, o.createdAt) " +
            "from OrderEntity o, OrderItemEntity oi, ProductEntity p, PaymentEntity pay, CustomerEntity c " +
            "where oi.order.id = o.id " +
            "and p.id = oi.product.id " +
            "and pay.order.id = o.id " +
            "and c.id = o.customer.id")
    List<OrderDetailsDTO> findOrderDetails();

    @Query("SELECT new com.project.inventorymanagement.dto.OrderByStatusDTO(o.id, o.status, o.createdAt) " +
            "FROM OrderEntity o")
    List<OrderByStatusDTO> findOrdersByStatus();
}
