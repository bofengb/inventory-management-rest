package com.project.inventorymanagement.service;

import com.project.inventorymanagement.dto.PurchaseAnalysisDTO;
import com.project.inventorymanagement.dto.SalesAnalysisDTO;
import com.project.inventorymanagement.entity.InventoryTransactionEntity;
import com.project.inventorymanagement.enums.TransactionType;
import com.project.inventorymanagement.repository.InventoryTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryTransactionService {

    private final InventoryTransactionRepository inventoryTransactionRepository;

    public List<SalesAnalysisDTO> getSalesAnalysis(String groupBy) {
        // Fetch all SALE transactions
        List<InventoryTransactionEntity> transactions = inventoryTransactionRepository.findByType(TransactionType.SALE);
        Map<String, Double> groupedSales = new TreeMap<>();

        // If no transactions, return an empty list
        if (transactions.isEmpty()) {
            return new ArrayList<>();
        }

        // Determine overall min and max dates from transactions
        LocalDate minDate = null;
        LocalDate maxDate = null;
        for (InventoryTransactionEntity txn : transactions) {
            LocalDate txnDate = txn.getTimestamp().toLocalDate();
            if (minDate == null || txnDate.isBefore(minDate)) {
                minDate = txnDate;
            }
            if (maxDate == null || txnDate.isAfter(maxDate)) {
                maxDate = txnDate;
            }
        }

        switch (groupBy.toLowerCase()) {
            case "daily": {
                // Group by day: format yyyy-MM-dd
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                for (InventoryTransactionEntity txn : transactions) {
                    String key = txn.getTimestamp().toLocalDate().format(formatter);
                    double saleAmount = txn.getQuantity() * txn.getUnitPrice();
                    groupedSales.merge(key, saleAmount, Double::sum);
                }
                // Fill missing days between minDate and maxDate
                LocalDate currentDate = minDate;
                while (!currentDate.isAfter(maxDate)) {
                    String key = currentDate.format(formatter);
                    groupedSales.putIfAbsent(key, 0.0);
                    currentDate = currentDate.plusDays(1);
                }
                break;
            }
            case "weekly": {
                // Group by week: e.g., "2025-W07" using ISO week definitions
                WeekFields weekFields = WeekFields.ISO;
                for (InventoryTransactionEntity txn : transactions) {
                    LocalDate date = txn.getTimestamp().toLocalDate();
                    int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
                    int year = date.get(weekFields.weekBasedYear());
                    String key = year + "-W" + String.format("%02d", weekNumber);

                    LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);
                    WeekFields weekFields2 = WeekFields.of(Locale.getDefault());
                    TemporalField weekOfYear = weekFields2.weekOfYear();
                    LocalDate startDate = firstDayOfYear.with(weekOfYear, weekNumber).with(weekFields.dayOfWeek(), 1); // Monday as the start day
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String dateString = startDate.format(formatter);

                    double saleAmount = txn.getQuantity() * txn.getUnitPrice();
                    // groupedSales.merge(key, saleAmount, Double::sum);
                    groupedSales.merge(dateString, saleAmount, Double::sum);
                }
                // Fill missing weeks: determine the start of the week for min and max dates
                LocalDate startOfWeek = minDate.with(weekFields.dayOfWeek(), 1);
                LocalDate endOfWeek = maxDate.with(weekFields.dayOfWeek(), 1);
                LocalDate currentWeek = startOfWeek;
                while (!currentWeek.isAfter(endOfWeek)) {
                    int weekNumber = currentWeek.get(weekFields.weekOfWeekBasedYear());
                    int year = currentWeek.get(weekFields.weekBasedYear());
                    String key = year + "-W" + String.format("%02d", weekNumber);

                    LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);
                    WeekFields weekFields2 = WeekFields.of(Locale.getDefault());
                    TemporalField weekOfYear = weekFields2.weekOfYear();
                    LocalDate startDate = firstDayOfYear.with(weekOfYear, weekNumber).with(weekFields.dayOfWeek(), 1); // Monday as the start day
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String dateString = startDate.format(formatter);

                    // groupedSales.putIfAbsent(key, 0.0);
                    groupedSales.putIfAbsent(dateString, 0.0);
                    currentWeek = currentWeek.plusWeeks(1);
                }
                break;
            }
            case "monthly": {
                // Group by month: format "yyyy-MM", e.g., "2025-02"
                for (InventoryTransactionEntity txn : transactions) {
                    LocalDate date = txn.getTimestamp().toLocalDate();
                    String key = date.getYear() + "-" + String.format("%02d", date.getMonthValue());
                    double saleAmount = txn.getQuantity() * txn.getUnitPrice();
                    groupedSales.merge(key, saleAmount, Double::sum);
                }
                // Fill missing months: iterate month by month
                LocalDate startOfMonth = minDate.withDayOfMonth(1);
                LocalDate endOfMonth = maxDate.withDayOfMonth(1);
                while (!startOfMonth.isAfter(endOfMonth)) {
                    String key = startOfMonth.getYear() + "-" + String.format("%02d", startOfMonth.getMonthValue());
                    groupedSales.putIfAbsent(key, 0.0);
                    startOfMonth = startOfMonth.plusMonths(1);
                }
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid groupBy parameter: " + groupBy);
        }

        // Build the final list of DTOs, computing percentage change compared to the previous period.
        // Here, we set the first period's percentage change to 0.
        List<SalesAnalysisDTO> analysisList = new ArrayList<>();
        Double previousTotal = null;
        for (Map.Entry<String, Double> entry : groupedSales.entrySet()) {
            SalesAnalysisDTO dto = new SalesAnalysisDTO();
            dto.setPeriod(entry.getKey());
            dto.setTotalSales(entry.getValue());
            if (previousTotal == null) {
                dto.setPercentageChange(0.0);
            } else {
                // Avoid division by zero
                double change = previousTotal == 0.0 ? 0.0 : ((entry.getValue() - previousTotal) / previousTotal) * 100;
                dto.setPercentageChange(change);
            }
            analysisList.add(dto);
            previousTotal = entry.getValue();
        }
        return analysisList;
    }

    public List<PurchaseAnalysisDTO> getPurchaseAnalysis() {
        List<Object[]> results = inventoryTransactionRepository.findPurchaseAnalysis(TransactionType.PURCHASE);
        return results.stream().map(result -> {
            // Convert java.sql.Date to LocalDate
            java.sql.Date sqlDate = (java.sql.Date) result[0];
            LocalDate localDate = sqlDate.toLocalDate();
            Double totalCost = ((Number) result[1]).doubleValue();
            return new PurchaseAnalysisDTO(localDate, totalCost);
        }).collect(Collectors.toList());
    }

}
