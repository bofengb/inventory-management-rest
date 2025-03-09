package com.project.inventorymanagement.controller;

import com.project.inventorymanagement.entity.CustomerEntity;
import com.project.inventorymanagement.service.CustomerService;
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
        value = "/customers",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
@Tag(name = "Customers", description = "Operations related to customers")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(
            summary = "Get customers",
            description = "Retrieve a list of all customers."
    )
    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getCustomers() {
        return ResponseEntity.ok().body(customerService.getCustomers());
    }

}
