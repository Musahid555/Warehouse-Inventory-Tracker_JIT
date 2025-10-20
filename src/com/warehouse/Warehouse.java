package com.warehouse;

import java.util.List;
import java.util.ArrayList;

public class Warehouse {

    private List<Product> products;
    private AlertService alertService;

    public Warehouse(AlertService alertService) {
        this.products = new ArrayList<Product>();
        this.alertService = alertService;
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Product added: " + product);
    }

    public List<Product> getProducts() {
        return products;
    }

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
}
