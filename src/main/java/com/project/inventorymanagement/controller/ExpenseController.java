package com.project.inventorymanagement.controller;

import com.project.inventorymanagement.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Expense", description = "Operations related to expense analysis")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Operation(
            summary = "Get expense breakdown",
            description = "Retrieve expense breakdown by category."
    )
    @GetMapping("/breakdown")
    public ResponseEntity<List<Map<String, Object>>> getExpenseBreakdown() {
        return ResponseEntity.ok().body(expenseService.getExpenseAnalysis());
    }

}
