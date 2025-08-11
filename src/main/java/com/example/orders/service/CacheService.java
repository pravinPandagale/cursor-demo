package com.example.orders.service;

import com.example.orders.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheService {
    
    private static final String ORDER_CACHE_NAME = "orders";
    
    /**
     * Cache an order response with the order ID as the key
     */
    @CachePut(value = ORDER_CACHE_NAME, key = "#orderResponse.id")
    public OrderResponse cacheOrder(OrderResponse orderResponse) {
        log.debug("Caching order with ID: {}", orderResponse.getId());
        return orderResponse;
    }
    
    /**
     * Retrieve an order from cache by ID
     */
    @Cacheable(value = ORDER_CACHE_NAME, key = "#id")
    public OrderResponse getCachedOrder(Long id) {
        log.debug("Order with ID: {} not found in cache", id);
        return null; // This will only be called if the cache miss occurs
    }
    
    /**
     * Evict an order from cache by ID
     */
    @CacheEvict(value = ORDER_CACHE_NAME, key = "#id")
    public void evictOrder(Long id) {
        log.debug("Evicting order with ID: {} from cache", id);
    }
    
    /**
     * Evict all orders from cache
     */
    @CacheEvict(value = ORDER_CACHE_NAME, allEntries = true)
    public void evictAllOrders() {
        log.debug("Evicting all orders from cache");
    }
}
