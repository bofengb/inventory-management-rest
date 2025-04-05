package com.project.inventorymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailInfoDTO {
    private Long productId;
    private String productName;
    private Float basePrice;
    private Float rating;

    private Integer totalUnitsSold;
    private Float totalRevenue;
    private Integer totalCustomers;
    private Integer inventoryLeft;

    private Boolean lowStockAlert;
    private Float estimatedProfit;
    // Items sold per month
    private Float inventoryMovementRate;
}
