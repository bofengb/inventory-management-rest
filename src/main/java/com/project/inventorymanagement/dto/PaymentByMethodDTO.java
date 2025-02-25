package com.project.inventorymanagement.dto;

import com.project.inventorymanagement.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PaymentByMethodDTO {
    private Long paymentId;
    private PaymentMethod method;
    private LocalDateTime timestamp;
}
