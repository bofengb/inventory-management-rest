package com.project.inventorymanagement.repository;

import com.project.inventorymanagement.dto.PaymentByMethodDTO;
import com.project.inventorymanagement.dto.TransactionByTypeDTO;
import com.project.inventorymanagement.entity.InventoryTransactionEntity;
import com.project.inventorymanagement.enums.TransactionType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface InventoryTransactionRepository extends JpaRepository<InventoryTransactionEntity, Long> {

    @Query("SELECT it.product, SUM(it.quantity) AS totalQuantity " +
            "FROM InventoryTransactionEntity it " +
            "WHERE it.type = :type " +
            "GROUP BY it.product " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> findBestSaleProducts(@Param("type") TransactionType type, Pageable pageable);

    List<InventoryTransactionEntity> findByType(TransactionType type);

    @Query("SELECT FUNCTION('DATE', t.timestamp), SUM(t.unitPrice * t.quantity) " +
            "FROM InventoryTransactionEntity t " +
            "WHERE t.type = :type " +
            "GROUP BY FUNCTION('DATE', t.timestamp) " +
            "ORDER BY FUNCTION('DATE', t.timestamp)")
    List<Object[]> findPurchaseAnalysis(@Param("type") TransactionType type);

    List<InventoryTransactionEntity> findTransactionsByTimestampBetweenAndType(LocalDateTime startDate, LocalDateTime endDate, TransactionType type);

    @Query("SELECT new com.project.inventorymanagement.dto.TransactionByTypeDTO(o.id, o.type, o.timestamp) " +
            "FROM InventoryTransactionEntity o")
    List<TransactionByTypeDTO> findTransactionByType();
}
