package com.project.inventorymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
@Getter
@Setter
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_seq")
    @SequenceGenerator(name = "expense_seq", sequenceName = "expense_seq")
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;

    @Column(name = "year", nullable = false, updatable = false)
    private int year;

    @Column(name = "month", nullable = false, updatable = false)
    private int month;

    @PrePersist
    public void prePersist() {
        this.year = this.timestamp.getYear();
        this.month = this.timestamp.getMonthValue();
    }

}
