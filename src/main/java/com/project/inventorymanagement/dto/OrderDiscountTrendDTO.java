package com.project.inventorymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDiscountTrendDTO {
    private Long orderId;
    private LocalDateTime orderCreatedAt;
    private Float orderTotalAmount;
    private Float totalDiscount;

    public OrderDiscountTrendDTO(Long orderId, LocalDateTime orderCreatedAt, Float orderTotalAmount, Number totalDiscount) {
        this.orderId = orderId;
        this.orderCreatedAt = orderCreatedAt;
        this.orderTotalAmount = orderTotalAmount;
        this.totalDiscount = totalDiscount != null ? totalDiscount.floatValue() : 0.0f;
    }
}
