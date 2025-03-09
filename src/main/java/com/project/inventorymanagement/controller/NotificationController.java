package com.project.inventorymanagement.controller;

import com.project.inventorymanagement.entity.NotificationEntity;
import com.project.inventorymanagement.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Notification", description = "Operations related to notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(
            summary = "Get all notifications",
            description = "Retrieve a list of all notifications."
    )
    @GetMapping
    public ResponseEntity<List<NotificationEntity>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @Operation(
            summary = "Toggle read status",
            description = "Toggle the read status of a notification identified by its ID."
    )
    @PutMapping("/{id}/read")
    public ResponseEntity<NotificationEntity> toggleReadStatus(@PathVariable Long id) {
        NotificationEntity updatedNotification = notificationService.toggleReadStatus(id);
        return ResponseEntity.ok(updatedNotification);
    }

}
