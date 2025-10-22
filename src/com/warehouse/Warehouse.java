package com.warehouse;

import java.util.List;
import java.util.ArrayList;

/**
 * Warehouse represents a single warehouse inventory.
 * Stores products and handles stock operations.
 */
public class Warehouse {

    private String name;        // Warehouse name
    private String location;    // Warehouse location
    private List<Product> products;
    private AlertService alertService;

    /**
     * Constructor: initialize warehouse with name, location, and alert service.
     */
    public Warehouse(String name, String location, AlertService alertService) {
        this.name = name;
        this.location = location;
        this.products = new ArrayList<Product>();
        this.alertService = alertService;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<Product> getProducts() {
        return products;
    }

    /**
     * Add a product to the warehouse.
     */
    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Product added: " + product);
    }

    /**
     * Print all products in a formatted way.
     */
    public void printAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products in inventory.");
            return;
        }

        System.out.printf("%-5s %-15s %-10s %-10s%n", "ID", "Name", "Quantity", "MinStock");
        System.out.println("-----------------------------------------");
        for (Product p : products) {
            System.out.printf("%-5d %-15s %-10d %-10d%n",
                    p.getId(), p.getName(), p.getQuantity(), p.getReorderThreshold());
        }
    }

    /**
     * Receive shipment for a product by increasing quantity.
     */
    public void receiveShipment(int productId, int quantity) {
        for (Product p : products) {
            if (p.getId() == productId) {
                p.setQuantity(p.getQuantity() + quantity);
                System.out.println(quantity + " units added to " + p.getName() + ". New quantity: " + p.getQuantity());
                return;
            }
        }
        System.out.println("Product with ID " + productId + " not found.");
    }

    /**
     * Fulfill order for a product by reducing quantity.
     * Triggers low stock alert if below threshold.
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

                if (p.getQuantity() < p.getReorderThreshold()) {
                    alertService.notifyLowStock(p.getName(), p.getQuantity());
                }
                return;
            }
        }
        System.out.println("Product with ID " + productId + " not found.");
    }

    /**
     * Convert warehouse and its products to a string for file saving.
     */
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(",").append(location).append("\n");
        for (Product p : products) {
            sb.append(p.getId()).append(",")
              .append(p.getName()).append(",")
              .append(p.getQuantity()).append(",")
              .append(p.getReorderThreshold()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Load a product from a file line.
     */
    public void loadProductFromFile(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length != 4) return;
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            int qty = Integer.parseInt(parts[2]);
            int threshold = Integer.parseInt(parts[3]);
            Product p = new Product(name, qty, threshold);
            // Adjust product ID counter to maintain uniqueness
            if (id >= Product.idCounter) Product.idCounter = id + 1;
            products.add(p);
        } catch (Exception e) {
            System.out.println("Error loading product from line: " + line);
        }
    }
}
