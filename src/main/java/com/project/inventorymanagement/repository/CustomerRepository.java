package com.project.inventorymanagement.repository;

import com.project.inventorymanagement.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    List<CustomerEntity> findCustomersByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

}
