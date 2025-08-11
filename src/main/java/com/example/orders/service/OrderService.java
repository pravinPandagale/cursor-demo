package com.example.orders.service;

import com.example.orders.dto.OrderRequest;
import com.example.orders.dto.OrderResponse;
import com.example.orders.entity.Order;
import com.example.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {
    
    private final OrderRepository orderRepository;
    
    public OrderResponse createOrder(OrderRequest request) {
        log.info("Creating new order for customer: {}", request.getCustomerName());
        
        Order order = new Order();
        order.setCustomerName(request.getCustomerName());
        order.setAmount(request.getAmount());
        
        Order savedOrder = orderRepository.save(order);
        log.info("Order created successfully with ID: {}", savedOrder.getId());
        
        return mapToResponse(savedOrder);
    }
    
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long id) {
        log.info("Fetching order with ID: {}", id);
        
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        
        return mapToResponse(order);
    }
    
    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {
        log.info("Fetching all orders");
        
        return orderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<OrderResponse> searchByCustomerName(String customerName) {
        log.info("Fetching orders for customer: {}", customerName);
        
        return orderRepository.findByCustomerNameContainingIgnoreCase(customerName)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<OrderResponse> searchByAmountRange(BigDecimal minAmount, BigDecimal maxAmount) {
        log.info("Fetching orders with amount between {} and {}", minAmount, maxAmount);
        
        if (minAmount == null || maxAmount == null) {
            throw new IllegalArgumentException("Both minAmount and maxAmount must be provided");
        }
        
        if (minAmount.compareTo(maxAmount) > 0) {
            throw new IllegalArgumentException("minAmount cannot be greater than maxAmount");
        }
        
        return orderRepository.findByAmountBetween(minAmount, maxAmount)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    public OrderResponse updateOrder(Long id, OrderRequest request) {
        log.info("Updating order with ID: {}", id);
        
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        
        order.setCustomerName(request.getCustomerName());
        order.setAmount(request.getAmount());
        
        Order updatedOrder = orderRepository.save(order);
        log.info("Order updated successfully with ID: {}", updatedOrder.getId());
        
        return mapToResponse(updatedOrder);
    }
    
    public void deleteOrder(Long id) {
        log.info("Deleting order with ID: {}", id);
        
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with ID: " + id);
        }
        
        orderRepository.deleteById(id);
        log.info("Order deleted successfully with ID: {}", id);
    }
    
    private OrderResponse mapToResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setCustomerName(order.getCustomerName());
        response.setAmount(order.getAmount());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());
        return response;
    }
}
