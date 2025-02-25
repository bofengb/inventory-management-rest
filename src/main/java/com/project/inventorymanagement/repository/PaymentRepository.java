package com.project.inventorymanagement.repository;

import com.project.inventorymanagement.dto.OrderByStatusDTO;
import com.project.inventorymanagement.dto.PaymentByMethodDTO;
import com.project.inventorymanagement.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    @Query("SELECT new com.project.inventorymanagement.dto.PaymentByMethodDTO(o.id, o.method, o.timestamp) " +
            "FROM PaymentEntity o")
    List<PaymentByMethodDTO> findPaymentsByMethod();
}
