package com.project.inventorymanagement.service;

import com.project.inventorymanagement.entity.NotificationEntity;
import com.project.inventorymanagement.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    public List<NotificationEntity> getLatestYearNotifications() {
        int currentYear = LocalDateTime.now().getYear();
        int targetYear = 2025;
        List<NotificationEntity> notifications = notificationRepository.findByYear(targetYear);

        return notifications.stream().map(notification -> {
            LocalDateTime oldDate = notification.getCreatedAt();
            LocalDateTime updatedDate = oldDate.withYear(currentYear);
            notification.setCreatedAt(updatedDate);

            String updatedMessage = updateYearInMessage(notification.getMessage(), targetYear, currentYear);
            notification.setMessage(updatedMessage);

            return notification;
        }).collect(Collectors.toList());
    }

    private String updateYearInMessage(String message, int oldYear, int newYear) {
        if (message == null) return null;
        return message.replace(String.valueOf(oldYear), String.valueOf(newYear));
    }

    public NotificationEntity toggleReadStatus(Long notificationId) {
        NotificationEntity notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NoSuchElementException("Notification not found with id: " + notificationId));
        notification.setRead(!notification.isRead());
        return notificationRepository.save(notification);
    }

}
