package com.project.inventorymanagement.controller;

import com.project.inventorymanagement.dto.OrderDetailsDTO;
import com.project.inventorymanagement.dto.OrderDiscountSummaryDTO;
import com.project.inventorymanagement.dto.OrderDiscountTrendDTO;
import com.project.inventorymanagement.service.OrderAnalysisService;
import com.project.inventorymanagement.service.OrderService;
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
        value = "/order",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
@Tag(name = "Order", description = "Operations related to orders and order analysis")
public class OrderController {

    private final OrderAnalysisService orderAnalysisService;
    private final OrderService orderService;

    @Operation(
            summary = "Get order discount summary",
            description = "Retrieve a summary of order discounts including total orders, total discount, and average discount."
    )
    @GetMapping("/order-discount-summary")
    public ResponseEntity<OrderDiscountSummaryDTO> getOrderDiscountSummary() {
        return ResponseEntity.ok().body(orderAnalysisService.getOrderDiscountSummary());
    }

    @Operation(
            summary = "Get order discount trends",
            description = "Retrieve the trends of order discounts over time."
    )
    @GetMapping("/order-discount-trends")
    public ResponseEntity<List<OrderDiscountTrendDTO>> getOrderDiscountTrends() {
        return ResponseEntity.ok().body(orderAnalysisService.getOrderDiscountTrends());
    }

    @Operation(
            summary = "Get order details",
            description = "Retrieve detailed information about orders."
    )
    @GetMapping("/details")
    public ResponseEntity<List<OrderDetailsDTO>> getOrderDetails() {
        return ResponseEntity.ok().body(orderService.getOrderDetails());
    }

}
