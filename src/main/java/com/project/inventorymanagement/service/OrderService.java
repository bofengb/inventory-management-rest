package com.project.inventorymanagement.service;

import com.project.inventorymanagement.dto.OrderDetailsDTO;
import com.project.inventorymanagement.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDetailsDTO> getOrderDetails() {
        return orderRepository.findOrderDetails();
    }

}
