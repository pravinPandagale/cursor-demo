package com.example.orders.controller;



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
    
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        log.info("Received request to get order with ID: {}", id);
        OrderResponse response = orderService.getOrderById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        log.info("Received request to get all orders");
        List<OrderResponse> responses = orderService.getAllOrders();
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/search/customer")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomerName(
            @RequestParam String customerName) {
        log.info("Received request to search orders for customer: {}", customerName);
        List<OrderResponse> responses = orderService.getOrdersByCustomerName(customerName);
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/search/amount")
    public ResponseEntity<List<OrderResponse>> getOrdersByAmountRange(
            @RequestParam BigDecimal minAmount,
            @RequestParam BigDecimal maxAmount) {
        log.info("Received request to search orders with amount between {} and {}", minAmount, maxAmount);
        List<OrderResponse> responses = orderService.getOrdersByAmountRange(minAmount, maxAmount);
        return ResponseEntity.ok(responses);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(
            @PathVariable Long id,
            @Valid @RequestBody OrderRequest request) {
        log.info("Received request to update order with ID: {}", id);
        OrderResponse response = orderService.updateOrder(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        log.info("Received request to delete order with ID: {}", id);
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Orders Microservice is running!");
    }
}
