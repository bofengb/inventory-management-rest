package com.project.inventorymanagement.controller;

import com.project.inventorymanagement.dto.PurchaseAnalysisDTO;
import com.project.inventorymanagement.service.InventoryTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
        value = "/purchase",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class PurchaseController {
    private final InventoryTransactionService inventoryTransactionService;

    @GetMapping("/analysis")
    public ResponseEntity<List<PurchaseAnalysisDTO>> getPurchaseAnalysis() {
        List<PurchaseAnalysisDTO> analysis = inventoryTransactionService.getPurchaseAnalysis();
        return ResponseEntity.ok(analysis);
    }
}
