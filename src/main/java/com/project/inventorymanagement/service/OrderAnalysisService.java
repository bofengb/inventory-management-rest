package com.project.inventorymanagement.service;

import com.project.inventorymanagement.dto.OrderDiscountSummaryDTO;
import com.project.inventorymanagement.dto.OrderDiscountTrendDTO;
import com.project.inventorymanagement.repository.OrderItemRepository;
import com.project.inventorymanagement.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderAnalysisService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderDiscountSummaryDTO getOrderDiscountSummary() {
        Float totalOrders = orderRepository.findTotalOrderAmount();
        Float totalDiscount = orderItemRepository.findTotalDiscount();
        Float averageDiscount = orderItemRepository.findAverageDiscount();
        return new OrderDiscountSummaryDTO(totalOrders, totalDiscount, averageDiscount);
    }

    public List<OrderDiscountTrendDTO> getOrderDiscountTrends() {
        return orderRepository.findOrderDiscountTrends();
    }

}
