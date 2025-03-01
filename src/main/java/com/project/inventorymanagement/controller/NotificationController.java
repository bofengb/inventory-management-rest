package com.project.inventorymanagement.controller;

import com.project.inventorymanagement.entity.NotificationEntity;
import com.project.inventorymanagement.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = "/notification",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationEntity>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<NotificationEntity> markNotificationAsRead(@PathVariable Long id) {
        NotificationEntity updatedNotification = notificationService.toggleReadStatus(id);
        return ResponseEntity.ok(updatedNotification);
    }

}
