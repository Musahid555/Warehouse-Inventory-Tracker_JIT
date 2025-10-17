package com.warehouse;

import java.util.List;
import java.util.ArrayList;

/**
 * Manages warehouse inventory operations.
 * Responsible for adding products, updating stock, and alerts.
 * Author: Musahid Mansuri
 * Date: 17-Oct-2025
 */
public class Warehouse {

    // In-memory list to store products
    private List<Product> products;

    /**
     * Constructor initializes the products list.
     */
    public Warehouse() {
        products = new ArrayList<Product>();
    }

    /**
     * Adds a new product to the warehouse inventory.
     * @param product the product to add
     */
    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Product added: " + product);
    }

    /**
     * Returns the list of all products in the warehouse.
     * @return List<Product>
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Increases the stock of a product when a shipment is received.
     * @param productId the unique ID of the product
     * @param quantity the number of units received
     */
    public void receiveShipment(int productId, int quantity) {
        for (Product p : products) {
            if (p.getId() == productId) {
                p.setQuantity(p.getQuantity() + quantity);
                System.out.println(quantity + " units added to " + p.getName() +
                                   ". New quantity: " + p.getQuantity());
                return;
            }
        }
        System.out.println("Product with ID " + productId + " not found.");
    }

    /**
     * Decreases the stock of a product when an order is fulfilled.
     * Triggers low stock alert if quantity falls below reorder threshold.
     * @param productId the unique ID of the product
     * @param quantity the number of units to fulfill
     */
    public void fulfillOrder(int productId, int quantity) {
        for (Product p : products) {
            if (p.getId() == productId) {
                if (p.getQuantity() < quantity) {
                    System.out.println("Insufficient stock for " + p.getName());
                    return;
                }
                p.setQuantity(p.getQuantity() - quantity);
                System.out.println(quantity + " units of " + p.getName() + " fulfilled. Remaining: " + p.getQuantity());

                // Low stock alert
                if (p.getQuantity() < p.getReorderThreshold()) {
                    System.out.println("ALERT: Low stock for " + p.getName() + " – only " + p.getQuantity() + " left!");
                }
                return;
            }
        }
        System.out.println("Product with ID " + productId + " not found.");
    }
}
