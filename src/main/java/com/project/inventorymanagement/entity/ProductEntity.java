package com.project.inventorymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq")
    private Long id;

    @Column(name = "name", length = 225)
    private String name;

    @Column(name = "base_price")
    private Float basePrice;

    @Column(name = "rating")
    private Float rating;

    // @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<InventoryTransactionEntity> transactions = new ArrayList<>();

    // @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<OrderItemEntity> orderItems = new ArrayList<>();
}
