package com.example.orders.config;

import com.example.orders.entity.Order;
import com.example.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    
    private final OrderRepository orderRepository;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("Loading initial data...");
        
        // Clear existing data
        orderRepository.deleteAll();
        
        // Create sample orders
        Order order1 = new Order();
        order1.setCustomerName("John Doe");
        order1.setAmount(new BigDecimal("150.50"));
        
        Order order2 = new Order();
        order2.setCustomerName("Jane Smith");
        order2.setAmount(new BigDecimal("299.99"));
        
        Order order3 = new Order();
        order3.setCustomerName("Bob Johnson");
        order3.setAmount(new BigDecimal("75.25"));
        
        Order order4 = new Order();
        order4.setCustomerName("Alice Brown");
        order4.setAmount(new BigDecimal("450.00"));
        
        Order order5 = new Order();
        order5.setCustomerName("Charlie Wilson");
        order5.setAmount(new BigDecimal("199.99"));
        
        orderRepository.saveAll(Arrays.asList(order1, order2, order3, order4, order5));
        
        log.info("Initial data loaded successfully. Created {} sample orders.", orderRepository.count());
    }
}
