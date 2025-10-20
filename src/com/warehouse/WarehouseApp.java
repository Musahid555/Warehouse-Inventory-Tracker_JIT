package com.warehouse;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class WarehouseApp {

    private final Warehouse warehouse;
    private final Scanner scanner;

    public WarehouseApp() {
        AlertService alertService = new DynamicAlertService();
        this.warehouse = new Warehouse(alertService);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== Warehouse Inventory Tracker ===");
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleAddProduct();
                    break;
                case 2:
                    handleReceiveShipment();
                    break;
                case 3:
                    handleFulfillOrder();
                    break;
                case 4:
                    handleShowInventory();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Enter 1-5.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("Menu:");
        System.out.println(" 1. Add Product");
        System.out.println(" 2. Receive Shipment");
        System.out.println(" 3. Fulfill Order");
        System.out.println(" 4. Show Inventory");
        System.out.println(" 5. Exit");
    }

    private void handleAddProduct() {
        System.out.println("---- Add Product ----");
        String name = readString("Product name: ");
        int qty = readInt("Initial quantity: ");
        int threshold = readInt("Minimum stock for alert: ");
        Product p = new Product(name, qty, threshold);
        warehouse.addProduct(p);
    }

    private void handleReceiveShipment() {
        System.out.println("---- Receive Shipment ----");
        warehouse.printAllProducts();
        int productId = readInt("Enter Product ID to add stock: ");
        int qty = readInt("Quantity to add: ");
        warehouse.receiveShipment(productId, qty);
    }

    private void handleFulfillOrder() {
        System.out.println("---- Fulfill Order ----");
        warehouse.printAllProducts();
        int productId = readInt("Enter Product ID to fulfill order: ");
        int qty = readInt("Quantity to fulfill: ");
        warehouse.fulfillOrder(productId, qty);
    }

    private void handleShowInventory() {
        System.out.println("---- Current Inventory ----");
        warehouse.printAllProducts();
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); 
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
