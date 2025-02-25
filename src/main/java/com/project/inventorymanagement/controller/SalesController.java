package com.project.inventorymanagement.controller;

import com.project.inventorymanagement.dto.SalesAnalysisDTO;
import com.project.inventorymanagement.service.InventoryTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
        value = "/sales",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class SalesController {

    private final InventoryTransactionService inventoryTransactionService;

    @GetMapping("/analysis")
    public ResponseEntity<List<SalesAnalysisDTO>> getSalesAnalysis(
            @RequestParam(defaultValue = "daily") String groupBy) {
        List<SalesAnalysisDTO> analysis = inventoryTransactionService.getSalesAnalysis(groupBy);
        return ResponseEntity.ok(analysis);
    }
}
