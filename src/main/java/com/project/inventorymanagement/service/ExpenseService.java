package com.project.inventorymanagement.service;

import com.project.inventorymanagement.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public List<Map<String, Object>> getExpenseAnalysis() {
        return expenseRepository.getExpenseBreakdownByCategory();
    }

}
