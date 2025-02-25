package com.project.inventorymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesAnalysisDTO {
    private String period;
    private Double totalSales;
    private Double percentageChange;
}
