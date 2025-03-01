package com.project.inventorymanagement.service;

import com.project.inventorymanagement.dto.ProductDTO;
import com.project.inventorymanagement.entity.ProductEntity;
import com.project.inventorymanagement.enums.TransactionType;
import com.project.inventorymanagement.repository.InventoryTransactionRepository;
import com.project.inventorymanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final InventoryTransactionRepository inventoryTransactionRepository;
    private final NotificationService notificationService;

    public List<ProductDTO> getTopFifteenBestSaleProducts() {
        PageRequest pageable = PageRequest.of(0, 15);
        List<Object[]> results = inventoryTransactionRepository.findBestSaleProducts(TransactionType.SALE, pageable);

        return results.stream().map(result -> {
            ProductEntity product = (ProductEntity) result[0];
            Long stockQuantity = (Long) result[1];
            return new ProductDTO(product.getId(), product.getName(), product.getBasePrice(), product.getRating(), (int) (long) stockQuantity);
        }).collect(Collectors.toList());
    }

    public List<ProductEntity> getProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<ProductEntity> searchProducts(String name) {
        return productRepository.findByNameContaining(name);
    }

    public ProductEntity createProduct(ProductEntity product) {
//        return productRepository.save(product);
        ProductEntity savedProduct = productRepository.save(product);
        // Format the current date as "yyyy-MM-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = LocalDateTime.now().format(formatter);
        String message = "Product create: " + product.getName() +
                " (Id: " + product.getId() + ") created at " + formattedDate;
        notificationService.createNotification(message, savedProduct.getId());

        return savedProduct;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
