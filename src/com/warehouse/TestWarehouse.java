package com.warehouse;

/**
 * Test class to simulate Day 2 operations of Warehouse Inventory Tracker.
 * Author: Musahid Mansuri
 * Date: 17-Oct-2025
 */
public class TestWarehouse {

    public static void main(String[] args) {
        // Step 1: Create warehouse
        Warehouse warehouse = new Warehouse();

        // Step 2: Add products
        Product laptop = new Product("Laptop", 0, 5);
        Product mouse = new Product("Mouse", 10, 3);
        warehouse.addProduct(laptop);
        warehouse.addProduct(mouse);

        // Step 3: Receive shipment
        warehouse.receiveShipment(1, 10); // Laptop +10

        // Step 4: Fulfill orders
        warehouse.fulfillOrder(1, 6);     // Laptop -6, triggers alert (10-6=4 < 5)
        warehouse.fulfillOrder(2, 5);     // Mouse -5, no alert (10-5=5 > 3)
    }
}
