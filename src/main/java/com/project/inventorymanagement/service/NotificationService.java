package com.project.inventorymanagement.service;

import com.project.inventorymanagement.entity.NotificationEntity;
import com.project.inventorymanagement.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationEntity createNotification(String message, Long productId) {
        NotificationEntity notification = new NotificationEntity();

        notification.setMessage(message);
        notification.setProductId(productId);
        notification.setCreatedAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    public List<NotificationEntity> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public NotificationEntity toggleReadStatus(Long notificationId) {
        NotificationEntity notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NoSuchElementException("Notification not found with id: " + notificationId));
        notification.setRead(!notification.isRead());
        return notificationRepository.save(notification);
    }

}
