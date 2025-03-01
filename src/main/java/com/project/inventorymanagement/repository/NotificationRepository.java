package com.project.inventorymanagement.repository;

import com.project.inventorymanagement.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository  extends JpaRepository<NotificationEntity, Long> {
}
