package com.project.inventorymanagement.controller;

import com.project.inventorymanagement.dto.OrderByStatusDTO;
import com.project.inventorymanagement.dto.PaymentByMethodDTO;
import com.project.inventorymanagement.dto.TransactionByTypeDTO;
import com.project.inventorymanagement.service.ChartService;
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
public class ChartController {

    private final ChartService chartService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderByStatusDTO>> getOrders() {
        return ResponseEntity.ok().body(chartService.getOrders());
    }

    @GetMapping("/payments")
    public ResponseEntity<List<PaymentByMethodDTO>> getPayment() {
        return ResponseEntity.ok().body(chartService.getPayment());
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionByTypeDTO>> getInventoryTransaction() {
        return ResponseEntity.ok().body(chartService.getInventoryTransaction());
    }
}

