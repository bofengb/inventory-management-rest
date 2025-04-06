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

    private Integer totalCustomers;
    private Integer inventoryLeft;
    // Items sold per month
    private Float inventoryMovementRate;
}
