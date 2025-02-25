package com.project.inventorymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatisticDTO {
    private Float totalSales;
    private Float salesChangePercentage;
    private Float totalPurchases;
    private Float purchasesChangePercentage;
    private Float totalExpenses;
    private Float expensesChangePercentage;
    private Long customerGrowth;
    private Float customerGrowthChangePercentage;
    private Float totalPendingAmount;
    private Float pendingAmountChangePercentage;
}
