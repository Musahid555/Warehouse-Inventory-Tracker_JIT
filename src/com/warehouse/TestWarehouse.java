package com.warehouse;

/**
 * Test class for Day 4: Warehouse Inventory Tracker with AlertService
 * Author: Musahid Mansuri
 * Date: 18-Oct-2025
 */
public class TestWarehouse {

    public static void main(String[] args) {

        // Step 1: Implement AlertService with anonymous class
        AlertService alertService = new AlertService() {
            @Override
            public void notifyLowStock(String productName, int quantity) {
                System.out.println("ALERT: Low stock for " + productName + " – only " + quantity + " left!");
            }
        };

        // Step 2: Create warehouse with alert service
        Warehouse warehouse = new Warehouse(alertService);

        // Step 3: Add products
        Product laptop = new Product("Laptop", 0, 5);
        Product mouse = new Product("Mouse", 10, 3);
        warehouse.addProduct(laptop);
        warehouse.addProduct(mouse);

        // Step 4: Receive shipment
        warehouse.receiveShipment(laptop.getId(), 10); // Laptop +10

        // Step 5: Fulfill orders
        warehouse.fulfillOrder(laptop.getId(), 6);     // Laptop -6, triggers alert
        warehouse.fulfillOrder(mouse.getId(), 5);      // Mouse -5, no alert
    }
}
