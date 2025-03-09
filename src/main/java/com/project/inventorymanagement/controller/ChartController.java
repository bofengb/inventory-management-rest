package com.project.inventorymanagement.controller;

import com.project.inventorymanagement.dto.OrderByStatusDTO;
import com.project.inventorymanagement.dto.PaymentByMethodDTO;
import com.project.inventorymanagement.dto.TransactionByTypeDTO;
import com.project.inventorymanagement.service.ChartService;
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
        value = "/charts",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
@Tag(name = "Charts", description = "Operations related to charts")
public class ChartController {

    private final ChartService chartService;

    @Operation(
            summary = "Get orders",
            description = "Retrieve a list of orders grouped by status."
    )
    @GetMapping("/orders")
    public ResponseEntity<List<OrderByStatusDTO>> getOrders() {
        return ResponseEntity.ok().body(chartService.getOrders());
    }

    @Operation(
            summary = "Get payments",
            description = "Retrieve a list of payments grouped by method."
    )
    @GetMapping("/payments")
    public ResponseEntity<List<PaymentByMethodDTO>> getPayment() {
        return ResponseEntity.ok().body(chartService.getPayment());
    }

    @Operation(
            summary = "Get transactions",
            description = "Retrieve a list of inventory transactions grouped by type."
    )
    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionByTypeDTO>> getInventoryTransaction() {
        return ResponseEntity.ok().body(chartService.getInventoryTransaction());
    }

}
