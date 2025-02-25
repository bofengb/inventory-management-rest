package com.project.inventorymanagement.dto;

import com.project.inventorymanagement.enums.OrderStatus;
import com.project.inventorymanagement.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class OrderDetailsDTO {
    private Long orderId;
    private Float totalAmount;
    private OrderStatus status;
    private String productName;
    private String customerName;
    private PaymentMethod paymentMethod;
    private LocalDateTime createdAt;
}
