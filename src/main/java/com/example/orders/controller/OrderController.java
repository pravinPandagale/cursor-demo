package com.example.orders.controller;

import com.example.orders.dto.OrderRequest;
import com.example.orders.dto.OrderResponse;
import com.example.orders.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class OrderController {
    
    private final OrderService orderService;
    
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {
        log.info("Received request to create order for customer: {}", request.getCustomerName());
        OrderResponse response = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        log.info("Received request to get all orders");
        List<OrderResponse> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable @NotNull Long id) {
        log.info("Received request to get order with ID: {}", id);
        OrderResponse response = orderService.getOrderById(id);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable @NotNull Long id, 
                                                   @Valid @RequestBody OrderRequest request) {
        log.info("Received request to update order with ID: {}", id);
        OrderResponse response = orderService.updateOrder(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable @NotNull Long id) {
        log.info("Received request to delete order with ID: {}", id);
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search/customer")
    public ResponseEntity<List<OrderResponse>> searchByCustomerName(@RequestParam String customerName) {
        log.info("Received request to search orders by customer name: {}", customerName);
        List<OrderResponse> orders = orderService.searchByCustomerName(customerName);
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/search/amount")
    public ResponseEntity<List<OrderResponse>> searchByAmountRange(@RequestParam BigDecimal minAmount, 
                                                                 @RequestParam BigDecimal maxAmount) {
        log.info("Received request to search orders by amount range: {} - {}", minAmount, maxAmount);
        List<OrderResponse> orders = orderService.searchByAmountRange(minAmount, maxAmount);
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        log.info("Health check endpoint called");
        return ResponseEntity.ok("Orders microservice is running!");
    }
}
