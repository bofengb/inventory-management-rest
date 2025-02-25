package com.project.inventorymanagement.dto;

import com.project.inventorymanagement.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class OrderByStatusDTO {
    private Long orderId;
    private OrderStatus status;
    private LocalDateTime createdAt;
}
