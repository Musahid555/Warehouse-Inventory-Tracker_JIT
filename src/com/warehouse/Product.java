package com.warehouse;

/**
 * Represents a single product in the warehouse inventory.
 * Each product has a unique auto-generated ID, name, quantity,
 * and a reorder threshold to trigger restock alerts.
 * 
 * Author: Musahid Mansuri
 * Date: 17-Oct-2025
 */

public class Product {

    // Static counter for auto-generating unique product IDs
    private static int idCounter = 1;

    // Product attributes
    private int id;
    private String name;
    private int quantity;
    private int reorderThreshold;

    /**
     * Constructs a new Product with auto-generated ID.
     * @param name product name
     * @param quantity initial stock quantity
     * @param reorderThreshold minimum quantity before restock alert
     */
    public Product(String name, int quantity, int reorderThreshold) {
        this.id = idCounter++;
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

    // Setters (ID excluded as it is auto-generated)
    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setReorderThreshold(int reorderThreshold) {
        this.reorderThreshold = reorderThreshold;
    }

    /**
     * Returns a string representation of the product details.
     */
    @Override
    public String toString() {
        return String.format("Product [ID=%d, Name=%s, Quantity=%d, ReorderThreshold=%d]",
                id, name, quantity, reorderThreshold);
    }
}
