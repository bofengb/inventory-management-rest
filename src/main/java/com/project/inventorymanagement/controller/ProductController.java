package com.project.inventorymanagement.controller;

import com.project.inventorymanagement.dto.ProductDTO;
import com.project.inventorymanagement.entity.ProductEntity;
import com.project.inventorymanagement.service.ProductService;
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
public class ProductController {

    private final ProductService productService;

    @GetMapping("/top")
    public ResponseEntity<List<ProductDTO>> getTopFifteenBestSaleProducts() {
        return ResponseEntity.ok().body(productService.getTopFifteenBestSaleProducts());
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getProducts());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductEntity>> searchProducts(@RequestParam("name") String name) {
        return ResponseEntity.ok().body(productService.searchProducts(name));
    }

    @PostMapping
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product) {
        return ResponseEntity.ok().body(productService.createProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productService.getProductById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
