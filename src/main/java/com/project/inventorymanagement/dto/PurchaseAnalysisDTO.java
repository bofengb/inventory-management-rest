package com.project.inventorymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseAnalysisDTO {
    private LocalDate date;
    private Double totalPurchaseCost;
}
