package com.example.orders.repository;

import com.example.orders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByCustomerNameContainingIgnoreCase(String customerName);
    
    List<Order> findByAmountBetween(java.math.BigDecimal minAmount, java.math.BigDecimal maxAmount);
}
