 package com.example.loyalityapp.controller;

import com.example.loyalityapp.model.Discount;
import com.example.loyalityapp.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class DiscountController {

    @Autowired
    private DiscountService orderService;

    // Question 1: GET total sales by category (only DELIVERED)
    @GetMapping("/sales-by-category")
    public ResponseEntity<Map<String, Object>> getSalesByCategory() {
        Map<String, Object> result = orderService.getTotalSalesByCategory();
        return ResponseEntity.ok(result);
    }

    // Question 2: GET order receipt with discount
    @GetMapping("/receipt/{orderId}")
    public ResponseEntity<Map<String, Object>> getOrderReceipt(@PathVariable int orderId) {
        Map<String, Object> result = orderService.getOrderReceipt(orderId);
        return ResponseEntity.ok(result);
    }

    // Question 3: GET highest value order
    @GetMapping("/highest-value")
    public ResponseEntity<Map<String, Object>> getHighestValueOrder() {
        Map<String, Object> result = orderService.getHighestValueOrder();
        return ResponseEntity.ok(result);
    }

    // Bonus: GET all orders
    @GetMapping("/all")
    public ResponseEntity<List<Discount>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // Bonus: GET orders by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Discount>> getOrdersByStatus(@PathVariable String status) {
        return ResponseEntity.ok(orderService.getOrdersByStatus(status));
    }

    // Bonus: GET orders by customer
    @GetMapping("/customer/{customer}")
    public ResponseEntity<List<Discount>> getOrdersByCustomer(@PathVariable String customer) {
        return ResponseEntity.ok(orderService.getOrdersByCustomer(customer));
    }
}
