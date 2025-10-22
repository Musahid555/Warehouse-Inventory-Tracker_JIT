package com.warehouse;

import java.util.InputMismatchException;
import java.util.Scanner;

public class WarehouseApp {

    private final Scanner scanner;
    private final WarehouseManager warehouseManager;

    // Default file name for persistence
    private static final String FILE_NAME = "inventory.txt";

    public WarehouseApp() {
        this.scanner = new Scanner(System.in);
        this.warehouseManager = new WarehouseManager();
    }

    public void start() {
        System.out.println("=== Warehouse Inventory Tracker (Multiple Warehouses) ===");

        // Load data from file at startup
        warehouseManager.loadFromFile(FILE_NAME);

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleAddWarehouse();
                    break;
                case 2:
                    handleAddProduct();
                    break;
                case 3:
                    handleReceiveShipment();
                    break;
                case 4:
                    handleFulfillOrder();
                    break;
                case 5:
                    handleShowInventory();
                    break;
                case 6:
                    warehouseManager.printWarehouses();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting application. Saving data...");
                    // Save data to file on exit
                    warehouseManager.saveToFile(FILE_NAME);
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Enter 1-7.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("Menu:");
        System.out.println(" 1. Add Warehouse");
        System.out.println(" 2. Add Product");
        System.out.println(" 3. Receive Shipment");
        System.out.println(" 4. Fulfill Order");
        System.out.println(" 5. Show Inventory");
        System.out.println(" 6. List Warehouses");
        System.out.println(" 7. Exit");
    }

    private void handleAddWarehouse() {
        System.out.println("---- Add Warehouse ----");
        String name = readString("Enter warehouse name: ");
        String location = readString("Enter warehouse location: ");
        AlertService alertService = new DynamicAlertService();
        warehouseManager.addWarehouse(new Warehouse(name, location, alertService));
    }

    private Warehouse selectWarehouse() {
        warehouseManager.printWarehouses();
        int index = readInt("Select warehouse by number: ");
        return warehouseManager.selectWarehouse(index);
    }

    private void handleAddProduct() {
        System.out.println("---- Add Product ----");
        Warehouse w = selectWarehouse();
        if (w == null) return;

        String name = readString("Product name: ");
        int qty = readInt("Initial quantity: ");
        int threshold = readInt("Minimum stock for alert: ");
        Product p = new Product(name, qty, threshold);
        w.addProduct(p);
    }

    private void handleReceiveShipment() {
        System.out.println("---- Receive Shipment ----");
        Warehouse w = selectWarehouse();
        if (w == null) return;

        w.printAllProducts();
        int productId = readInt("Enter Product ID to add stock: ");
        int qty = readInt("Quantity to add: ");
        w.receiveShipment(productId, qty);
    }

    private void handleFulfillOrder() {
        System.out.println("---- Fulfill Order ----");
        Warehouse w = selectWarehouse();
        if (w == null) return;

        w.printAllProducts();
        int productId = readInt("Enter Product ID to fulfill order: ");
        int qty = readInt("Quantity to fulfill: ");
        w.fulfillOrder(productId, qty);
    }

    private void handleShowInventory() {
        System.out.println("---- Current Inventory ----");
        Warehouse w = selectWarehouse();
        if (w == null) return;

        w.printAllProducts();
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // consume newline
                return value;
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Enter a valid integer.");
                scanner.nextLine();
            }
        }
    }

    private String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Input cannot be empty.");
        }
    }

    public static void main(String[] args) {
        new WarehouseApp().start();
    }
}
