package com.example.loyalityapp.service;

import com.example.loyalityapp.model.Discount;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DiscountService {

    private List<Discount> orders;

    public DiscountService() {
        initializeOrders();
    }

    private void initializeOrders() {
        orders = new ArrayList<>();

        // Test data
        orders.add(new Discount(101, "Alice", "GOLD", "DELIVERED", "Laptop", "Electronics", 1000, 1));
        orders.add(new Discount(101, "Alice", "GOLD", "DELIVERED", "Mouse", "Electronics", 20, 2));
        orders.add(new Discount(101, "Alice", "GOLD", "DELIVERED", "Chair", "Furniture", 100, 4));
        orders.add(new Discount(102, "Bob", "REGULAR", "PENDING", "Monitor", "Electronics", 300, 2));
        orders.add(new Discount(103, "Charlie", "PLATINUM", "RETURNED", "Desk", "Furniture", 500, 1));
    }

    // Question 1: Total Sales by Category (Only DELIVERED status)
    public Map<String, Object> getTotalSalesByCategory() {
        // Filter only DELIVERED orders
        Map<String, Integer> salesByCategory = orders.stream()
                .filter(order -> order.getStatus().equals("DELIVERED"))
                .collect(Collectors.groupingBy(
                        Discount::getCategory,
                        Collectors.summingInt(order -> order.getPrice() * order.getQuantity())
                ));

        Map<String, Object> result = new HashMap<>();
        result.put("salesByCategory", salesByCategory);
        result.put("totalCategories", salesByCategory.size());

        return result;
    }

    // Question 2: Get Order Receipt (with discount applied)
    public Map<String, Object> getOrderReceipt(int orderId) {
        // Find all items for this order
        List<Discount> orderItems = orders.stream()
                .filter(order -> order.getId() == orderId)
                .collect(Collectors.toList());

        if (orderItems.isEmpty()) {
            Map<String, Object> result = new HashMap<>();
            result.put("error", "Order not found");
            return result;
        }

        // Calculate subtotal (before discount)
        int subtotal = orderItems.stream()
                .mapToInt(order -> order.getPrice() * order.getQuantity())
                .sum();

        // Get tier from first item (all items have same tier for one order)
        String tier = orderItems.get(0).getTier();

        // Apply discount
        double discountPercent = getDiscountPercent(tier);
        double discountAmount = subtotal * (discountPercent / 100.0);
        double finalTotal = subtotal - discountAmount;

        // Build response
        Map<String, Object> result = new HashMap<>();
        result.put("orderId", orderId);
        result.put("customer", orderItems.get(0).getCustomer());
        result.put("tier", tier);
        result.put("items", orderItems);
        result.put("subtotal", subtotal);
        result.put("discountPercent", discountPercent);
        result.put("discountAmount", discountAmount);
        result.put("finalTotal", finalTotal);

        return result;
    }

    // Question 3: Find Highest Value Order (pre-discount)
    public Map<String, Object> getHighestValueOrder() {
        // Group by order ID and calculate total for each order
        Map<Integer, Integer> orderTotals = orders.stream()
                .collect(Collectors.groupingBy(
                        Discount::getId,
                        Collectors.summingInt(order -> order.getPrice() * order.getQuantity())
                ));

        // Find the order with highest total
        Optional<Map.Entry<Integer, Integer>> maxEntry = orderTotals.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        Map<String, Object> result = new HashMap<>();

        if (maxEntry.isPresent()) {
            int orderId = maxEntry.get().getKey();
            int totalValue = maxEntry.get().getValue();

            // Get order details
            List<Discount> orderItems = orders.stream()
                    .filter(order -> order.getId() == orderId)
                    .collect(Collectors.toList());

            result.put("orderId", orderId);
            result.put("customer", orderItems.get(0).getCustomer());
            result.put("totalValue", totalValue);
            result.put("items", orderItems);
        }

        return result;
    }

    // Helper method: Get discount percentage based on tier
    private double getDiscountPercent(String tier) {
        switch (tier.toUpperCase()) {
            case "GOLD":
                return 10.0;
            case "PLATINUM":
                return 20.0;
            case "REGULAR":
            default:
                return 0.0;
        }
    }

    // Get all orders
    public List<Discount> getAllOrders() {
        return new ArrayList<>(orders);
    }

    // Get orders by status
    public List<Discount> getOrdersByStatus(String status) {
        return orders.stream()
                .filter(order -> order.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    // Get orders by customer
    public List<Discount> getOrdersByCustomer(String customer) {
        return orders.stream()
                .filter(order -> order.getCustomer().equalsIgnoreCase(customer))
                .collect(Collectors.toList());
    }
}
