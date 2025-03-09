package com.project.inventorymanagement.controller;

import com.project.inventorymanagement.dto.PurchaseAnalysisDTO;
import com.project.inventorymanagement.service.InventoryTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Purchase", description = "Operations related to purchase analysis")
public class PurchaseController {
    private final InventoryTransactionService inventoryTransactionService;

    @Operation(
            summary = "Get purchase analysis",
            description = "Retrieve purchase analysis data based on inventory transactions."
    )
    @GetMapping("/analysis")
    public ResponseEntity<List<PurchaseAnalysisDTO>> getPurchaseAnalysis() {
        List<PurchaseAnalysisDTO> analysis = inventoryTransactionService.getPurchaseAnalysis();
        return ResponseEntity.ok(analysis);
    }

}
