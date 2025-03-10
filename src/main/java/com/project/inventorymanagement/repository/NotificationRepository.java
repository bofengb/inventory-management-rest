package com.project.inventorymanagement.repository;

import com.project.inventorymanagement.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository  extends JpaRepository<NotificationEntity, Long> {

    @Query(value = "SELECT * FROM notification WHERE EXTRACT(YEAR FROM created_at) = :year", nativeQuery = true)
    List<NotificationEntity> findByYear(@Param("year") int year);

}
