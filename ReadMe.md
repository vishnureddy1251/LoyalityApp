Develop a Spring Boot Rest API for the following scenarios

1. **Total Sales by Category:**
   Constraint: Only consider transactions with status **"Delivered"**

2. **Get Order Receipt:**
   Given an orderId(e.g., 101), calculate the **final total bill** 
   applying the discount logic **(Gold/Platinum rules)**

3. **Find Highest Value Order:**
   Return the orderId that has the highest total dollar value (Pre-Discount) across the entire system

// Test data:
{
        
       (ID: 101, CUSTOMER: "Alice", TIER: "GOLD", STATUS: "DELIVERED", PRODUCT: "Laptop", CATEGORY: "Electronics", PRICE= 1000, QUANTITY= 1);
        (ID: 101, CUSTOMER: "Alice", TIER: "GOLD", STATUS: "DELIVERED", PRODUCT: "Mouse", CATEGORY: "Electronics", PRICE= 20, QUANTITY= 2);
        (ID: 101, CUSTOMER: "Alice", TIER: "GOLD", STATUS: "DELIVERED", PRODUCT: "Chair", CATEGORY: "Furniture", PRICE= 100, QUANTITY= 4);
        (ID: 102, CUSTOMER: "Bob", TIER: "REGULAR", STATUS: "PENDING", PRODUCT: "Monitor", CATEGORY: "Electronics", PRICE= 300, QUANTITY= 2);
        (ID: 103, CUSTOMER: "Charlie", TIER: "PLATINUM", STATUS: "RETURNED", PRODUCT: "Desk", CATEGORY: "Furniture", PRICE= 500, QUANTITY= 1);
}

**Discounts:**

**GOLD - 10%**

**REGULAR - NO DISCOUNT**

**PLATINUM - 20% DISCOUNT**
   