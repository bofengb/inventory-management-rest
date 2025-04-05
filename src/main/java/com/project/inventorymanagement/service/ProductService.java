package com.project.inventorymanagement.service;

import com.project.inventorymanagement.dto.ProductDTO;
import com.project.inventorymanagement.dto.ProductDetailInfoDTO;
import com.project.inventorymanagement.entity.InventoryTransactionEntity;
import com.project.inventorymanagement.entity.OrderItemEntity;
import com.project.inventorymanagement.entity.ProductEntity;
import com.project.inventorymanagement.enums.TransactionType;
import com.project.inventorymanagement.repository.InventoryTransactionRepository;
import com.project.inventorymanagement.repository.OrderItemRepository;
import com.project.inventorymanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final InventoryTransactionRepository inventoryTransactionRepository;
    private final NotificationService notificationService;
    private final OrderItemRepository orderItemRepository;

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
        // return productRepository.findByNameContaining(name);
        String cleanedName = cleanString(name);
        List<ProductEntity> products = productRepository.findAll();

        return products.stream()
                .filter(product -> cleanString(product.getName()).contains(cleanedName))
                .collect(Collectors.toList());
    }

    private String cleanString(String str) {
        // Clean strings: remove non-alphanumeric characters and trim extra spaces
        return str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim();
    }

    public ProductEntity createProduct(ProductEntity product) {
        // return productRepository.save(product);
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

    public ProductDetailInfoDTO getProductDetail(Long productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // ====== SALES DATA ======
        List<OrderItemEntity> orderItems = orderItemRepository.findByProductId(productId);
        int totalUnitsSold = orderItems.stream()
                .mapToInt(OrderItemEntity::getQuantity)
                .sum();

        float totalRevenue = (float) orderItems.stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity() * (1 - item.getDiscount()))
                .sum();

        int uniqueCustomers = orderItemRepository.countDistinctCustomerByProductId(productId);

        // ====== INVENTORY DATA ======
        List<InventoryTransactionEntity> transactions = inventoryTransactionRepository.findByProductId(productId);
        int inventoryLeft = transactions.stream()
                .mapToInt(tx -> tx.getType() == TransactionType.SALE ? tx.getQuantity() : -tx.getQuantity())
                .sum();

        boolean lowStockAlert = inventoryLeft < 25;

        float inventoryCost = (float) transactions.stream()
                .filter(tx -> tx.getType() == TransactionType.SALE)
                .mapToDouble(tx -> tx.getUnitPrice() * tx.getQuantity())
                .sum();

        float estimatedProfit = totalRevenue - inventoryCost;

        // ====== MOVEMENT RATE (items/month) ======
        if (orderItems.isEmpty()) {
            // Avoid divide by zero
            return buildDTO(product, totalUnitsSold, totalRevenue, uniqueCustomers, inventoryLeft,
                    lowStockAlert, estimatedProfit, 0);
        }

        LocalDateTime firstSale = orderItems.stream()
                .map(oi -> oi.getOrder().getCreatedAt())
                .min(LocalDateTime::compareTo)
                .orElse(LocalDateTime.now());

        long monthsBetween = ChronoUnit.MONTHS.between(firstSale.toLocalDate().withDayOfMonth(1), LocalDate.now().withDayOfMonth(1));
        monthsBetween = Math.max(monthsBetween, 1); // avoid division by 0

        float movementRate = (float) totalUnitsSold / monthsBetween;

        return buildDTO(product, totalUnitsSold, totalRevenue, uniqueCustomers, inventoryLeft,
                lowStockAlert, estimatedProfit, movementRate);
    }

    private ProductDetailInfoDTO buildDTO(ProductEntity product, int totalSold, float revenue, int customers,
                                     int inventoryLeft, boolean stockAlert, float profit, float movementRate) {
        ProductDetailInfoDTO dto = new ProductDetailInfoDTO();
        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setBasePrice(product.getBasePrice());
        dto.setRating(product.getRating());

        dto.setTotalUnitsSold(totalSold);
        dto.setTotalRevenue(revenue);
        dto.setTotalCustomers(customers);
        dto.setInventoryLeft(inventoryLeft);

        dto.setLowStockAlert(stockAlert);
        dto.setEstimatedProfit(profit);
        dto.setInventoryMovementRate(movementRate);
        return dto;
    }

}
