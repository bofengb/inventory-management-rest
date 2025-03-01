package com.project.inventorymanagement.service;

import com.project.inventorymanagement.entity.CustomerEntity;
import com.project.inventorymanagement.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<CustomerEntity> getCustomers() {
        return customerRepository.findAll();
    }

}
