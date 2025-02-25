package com.project.inventorymanagement.dto;

import com.project.inventorymanagement.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TransactionByTypeDTO {
    private Long transactionId;
    private TransactionType type;
    private LocalDateTime timestamp;
}
