package com.project.inventorymanagement.service;

import com.project.inventorymanagement.dto.StatisticDTO;
import com.project.inventorymanagement.entity.CustomerEntity;
import com.project.inventorymanagement.entity.ExpenseEntity;
import com.project.inventorymanagement.entity.InventoryTransactionEntity;
import com.project.inventorymanagement.entity.OrderEntity;
import com.project.inventorymanagement.enums.OrderStatus;
import com.project.inventorymanagement.enums.TransactionType;
import com.project.inventorymanagement.repository.CustomerRepository;
import com.project.inventorymanagement.repository.ExpenseRepository;
import com.project.inventorymanagement.repository.InventoryTransactionRepository;
import com.project.inventorymanagement.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final OrderRepository orderRepository;
    private final ExpenseRepository expenseRepository;
    private final InventoryTransactionRepository inventoryTransactionRepository;
    private final CustomerRepository customerRepository;

    // Utility method to get the start of the current month
    private LocalDateTime getStartOfCurrentMonth() {
        LocalDate today = LocalDate.now();
        return today.withDayOfMonth(1).atStartOfDay();
    }

    // Utility method to get the start of the last month
    private LocalDateTime getStartOfLastMonth() {
        LocalDate lastMonth = LocalDate.now().minusMonths(1);
        return lastMonth.withDayOfMonth(1).atStartOfDay();
    }

    // Utility method to get the end of the current month
    private LocalDateTime getEndOfCurrentMonth() {
        LocalDate today = LocalDate.now();
        return today.withDayOfMonth(today.lengthOfMonth()).atTime(23, 59, 59);
    }

    // Utility method to get the end of the last month
    private LocalDateTime getEndOfLastMonth() {
        LocalDate lastMonth = LocalDate.now().minusMonths(1);
        return lastMonth.withDayOfMonth(lastMonth.lengthOfMonth()).atTime(23, 59, 59);
    }

    // Calculate sales for the given month range
    private Float getTotalSales(LocalDateTime startDate, LocalDateTime endDate) {
        List<OrderEntity> orders = orderRepository.findOrdersByCreatedAtBetweenAndStatus(startDate, endDate, OrderStatus.COMPLETED);
        return orders.stream().map(OrderEntity::getTotalAmount).reduce(0f, Float::sum);
    }

    // Calculate purchases for the given month range
    private Float getTotalPurchases(LocalDateTime startDate, LocalDateTime endDate) {
        List<InventoryTransactionEntity> transactions = inventoryTransactionRepository.findTransactionsByTimestampBetweenAndType(startDate, endDate, TransactionType.PURCHASE);
        return transactions.stream().map(t -> t.getUnitPrice() * t.getQuantity()).reduce(0f, Float::sum);
    }

    // Calculate expenses for the given month range
    private Float getTotalExpenses(LocalDateTime startDate, LocalDateTime endDate) {
        List<ExpenseEntity> expenses = expenseRepository.findExpensesByTimestampBetween(startDate, endDate);
        return expenses.stream().map(ExpenseEntity::getAmount).reduce(0f, Float::sum);
    }

    // Calculate customer growth for the given month range
    private Long getCustomerGrowth(LocalDateTime startDate, LocalDateTime endDate) {
        List<CustomerEntity> newCustomers = customerRepository.findCustomersByCreatedAtBetween(startDate, endDate);
        return (long) newCustomers.size();
    }

    // Calculate the total pending amount for the given month range
    private Float getTotalPendingAmount(LocalDateTime startDate, LocalDateTime endDate) {
        List<OrderEntity> orders = orderRepository.findOrdersByCreatedAtBetweenAndStatus(startDate, endDate, OrderStatus.PENDING);
        return orders.stream().map(OrderEntity::getPendingAmount).reduce(0f, Float::sum);
    }

    @Transactional
    public StatisticDTO generateMonthlyStatistic() {
        LocalDateTime startCurrentMonth = getStartOfCurrentMonth();
        LocalDateTime endCurrentMonth = getEndOfCurrentMonth();
        LocalDateTime startLastMonth = getStartOfLastMonth();
        LocalDateTime endLastMonth = getEndOfLastMonth();

        // Get current month data
        Float currentSales = getTotalSales(startCurrentMonth, endCurrentMonth);
        Float currentPurchases = getTotalPurchases(startCurrentMonth, endCurrentMonth);
        Float currentExpenses = getTotalExpenses(startCurrentMonth, endCurrentMonth);
        Long currentCustomerGrowth = getCustomerGrowth(startCurrentMonth, endCurrentMonth);
        Float currentPendingAmount = getTotalPendingAmount(startCurrentMonth, endCurrentMonth);

        // Get previous month data
        Float lastSales = getTotalSales(startLastMonth, endLastMonth);
        Float lastPurchases = getTotalPurchases(startLastMonth, endLastMonth);
        Float lastExpenses = getTotalExpenses(startLastMonth, endLastMonth);
        Long lastCustomerGrowth = getCustomerGrowth(startLastMonth, endLastMonth);
        Float lastPendingAmount = getTotalPendingAmount(startLastMonth, endLastMonth);

        // Calculate percentage changes with better handling for edge cases
        Float salesChange = calculatePercentageChange(lastSales, currentSales);
        Float purchasesChange = calculatePercentageChange(lastPurchases, currentPurchases);
        Float expensesChange = calculatePercentageChange(lastExpenses, currentExpenses);
        Float customerGrowthChange = calculatePercentageChange(lastCustomerGrowth.floatValue(), currentCustomerGrowth.floatValue());
        Float pendingAmountChange = calculatePercentageChange(lastPendingAmount, currentPendingAmount);

        return new StatisticDTO(currentSales, salesChange, currentPurchases, purchasesChange, currentExpenses, expensesChange, currentCustomerGrowth, customerGrowthChange, currentPendingAmount, pendingAmountChange);
    }

    // Helper method to calculate percentage change with edge case handling
    private Float calculatePercentageChange(Float lastValue, Float currentValue) {
        if (lastValue == 0 && currentValue == 0) {
            // No change when both are zero
            return 0f;
        } else if (lastValue == 0) {
            // If last month is 0 but current value is not, return a large positive value (indicating a huge increase)
            return currentValue > 0 ? 1000f : 0f;
        } else if (currentValue == 0) {
            // If current value is 0 and last value is non-zero, return a negative percentage (indicating a decrease)
            return -100f; // Indicating 100% decrease
        } else {
            // Normal percentage change calculation
            return (currentValue - lastValue) / lastValue * 100;
        }
    }
}
