package com.warehouse;

/**
 * Class Name: Product
 * Description: Represents a single product in the warehouse.
 * Author: Musahid Mansuri
 * Date: 16-Oct-2025
 */

public class Product {
    private int id;                // Unique ID for product
    private String name;           // Product name
    private int quantity;          // Current stock quantity
    private int reorderThreshold;  // Threshold to trigger restock alert

    public Product(int id, String name, int quantity, int reorderThreshold) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.reorderThreshold = reorderThreshold;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getReorderThreshold() {
        return reorderThreshold;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setReorderThreshold(int reorderThreshold) {
        this.reorderThreshold = reorderThreshold;
    }

    // toString method for displaying product info
    @Override
    public String toString() {
        return "Product{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", quantity=" + quantity +
               ", reorderThreshold=" + reorderThreshold +
               '}';
    }
}
