package com.project.inventorymanagement.service;

import com.project.inventorymanagement.dto.OrderByStatusDTO;
import com.project.inventorymanagement.dto.PaymentByMethodDTO;
import com.project.inventorymanagement.dto.TransactionByTypeDTO;
import com.project.inventorymanagement.repository.InventoryTransactionRepository;
import com.project.inventorymanagement.repository.OrderRepository;
import com.project.inventorymanagement.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChartService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final InventoryTransactionRepository inventoryTransactionRepository;

    public List<OrderByStatusDTO> getOrders() {
        return orderRepository.findOrdersByStatus();
    }

    public List<PaymentByMethodDTO> getPayment() {
        return paymentRepository.findPaymentsByMethod();
    }

    public List<TransactionByTypeDTO> getInventoryTransaction() {
        return inventoryTransactionRepository.findTransactionByType();
    }
}
