package com.example.loyalityapp.model;

    public class Discount {
        private int id;
        private String customer;
        private String tier;
        private String status;
        private String product;
        private String category;
        private int price;
        private int quantity;

        // Constructor
        public Discount(int id, String customer, String tier, String status,
                        String product, String category, int price, int quantity) {
            this.id = id;
            this.customer = customer;
            this.tier = tier;
            this.status = status;
            this.product = product;
            this.category = category;
            this.price = price;
            this.quantity = quantity;
        }

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCustomer() {
            return customer;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
        }

        public String getTier() {
            return tier;
        }

        public void setTier(String tier) {
            this.tier = tier;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        // Calculate total (price * quantity)
        public int getTotal() {
            return price * quantity;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "id=" + id +
                    ", customer='" + customer + '\'' +
                    ", tier='" + tier + '\'' +
                    ", status='" + status + '\'' +
                    ", product='" + product + '\'' +
                    ", category='" + category + '\'' +
                    ", price=" + price +
                    ", quantity=" + quantity +
                    '}';
        }
    }

