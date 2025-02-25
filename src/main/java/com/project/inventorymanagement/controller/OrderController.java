package com.project.inventorymanagement.controller;

import com.project.inventorymanagement.dto.OrderDetailsDTO;
import com.project.inventorymanagement.dto.OrderDiscountSummaryDTO;
import com.project.inventorymanagement.dto.OrderDiscountTrendDTO;
import com.project.inventorymanagement.service.OrderAnalysisService;
import com.project.inventorymanagement.service.OrderService;
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
public class OrderController {

    private final OrderAnalysisService orderAnalysisService;
    private final OrderService orderService;

    @GetMapping("/order-discount-summary")
    public ResponseEntity<OrderDiscountSummaryDTO> getOrderDiscountSummary() {
        return ResponseEntity.ok().body(orderAnalysisService.getOrderDiscountSummary());
    }

    @GetMapping("/order-discount-trends")
    public ResponseEntity<List<OrderDiscountTrendDTO>> getOrderDiscountTrends() {
        return ResponseEntity.ok().body(orderAnalysisService.getOrderDiscountTrends());
    }

    @GetMapping("/details")
    public ResponseEntity<List<OrderDetailsDTO>> getOrderDetails() {
        return ResponseEntity.ok().body(orderService.getOrderDetails());
    }
}
