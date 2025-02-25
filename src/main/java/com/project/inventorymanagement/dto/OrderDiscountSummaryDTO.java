package com.project.inventorymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDiscountSummaryDTO {
    private Float totalOrders;
    private Float totalDiscount;
    private Float averageDiscount;
}
