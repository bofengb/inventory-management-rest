package com.project.inventorymanagement.controller;

import com.project.inventorymanagement.dto.ProductDTO;
import com.project.inventorymanagement.dto.ProductDetailInfoDTO;
import com.project.inventorymanagement.entity.ProductEntity;
import com.project.inventorymanagement.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = "/products",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
@Tag(name = "Product", description = "Operations related to products")
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "Get top fifteen best sale products",
            description = "Retrieve the top fifteen best sale products based on sales data."
    )
    @GetMapping("/top")
    public ResponseEntity<List<ProductDTO>> getTopFifteenBestSaleProducts() {
        return ResponseEntity.ok().body(productService.getTopFifteenBestSaleProducts());
    }

    @Operation(
            summary = "Get all products",
            description = "Retrieve a list of all products."
    )
    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getProducts());
    }

    @Operation(
            summary = "Search products",
            description = "Search products by name with the provided query parameter."
    )
    @GetMapping("/search")
    public ResponseEntity<List<ProductEntity>> searchProducts(@RequestParam("name") String name) {
        return ResponseEntity.ok().body(productService.searchProducts(name));
    }

//    @PostMapping
//    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product) {
//        return ResponseEntity.ok().body(productService.createProduct(product));
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        if (!productService.getProductById(id).isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        productService.deleteProduct(id);
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailInfoDTO> getProductStats(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductDetail(productId));
    }

}
