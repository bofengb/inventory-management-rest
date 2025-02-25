package com.project.inventorymanagement.controller;

import com.project.inventorymanagement.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(
        value = "/expense",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping("/breakdown")
    public ResponseEntity<List<Map<String, Object>>> getExpenseBreakdown() {
        return ResponseEntity.ok().body(expenseService.getExpenseAnalysis());
    }
}
