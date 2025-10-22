package com.warehouse;

import java.util.List;
import java.util.ArrayList;
import java.io.*;

/**
 * WarehouseManager manages multiple Warehouse objects.
 * Provides functionality to add warehouses, select warehouse, and file operations.
 */
public class WarehouseManager {

    private List<Warehouse> warehouses;

    /**
     * Constructor: initialize warehouses list.
     */
    public WarehouseManager() {
        warehouses = new ArrayList<Warehouse>();
    }

    /**
     * Add a warehouse to the list.
     */
    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
        System.out.println("New warehouse added: " + warehouse.getName() + " (" + warehouse.getLocation() + "). Total warehouses: " + warehouses.size());
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    /**
     * Select warehouse by index (1-based).
     */
    public Warehouse selectWarehouse(int index) {
        if (index < 1 || index > warehouses.size()) {
            System.out.println("Invalid warehouse selection.");
            return null;
        }
        return warehouses.get(index - 1);
    }

    /**
     * Print all warehouses and their product count.
     */
    public void printWarehouses() {
        if (warehouses.isEmpty()) {
            System.out.println("No warehouses available.");
            return;
        }
        System.out.println("Available Warehouses:");
        for (int i = 0; i < warehouses.size(); i++) {
            Warehouse w = warehouses.get(i);
            System.out.println((i + 1) + ". " + w.getName() + " (" + w.getLocation() + ") - " + w.getProducts().size() + " products");
        }
    }

    /**
     * Save all warehouses and their products to a text file.
     */
    public void saveToFile(String filename) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            for (Warehouse w : warehouses) {
                writer.write(w.toFileString());
                writer.write("###\n"); // delimiter between warehouses
            }
            System.out.println("Warehouses saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        } finally {
            try { if (writer != null) writer.close(); } catch (IOException e) {}
        }
    }

    /**
     * Load warehouses and their products from a text file.
     * Invalid lines are skipped with a log message.
     */
    public void loadFromFile(String filename) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            Warehouse currentWarehouse = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.equals("###")) {
                    // End of current warehouse
                    currentWarehouse = null;
                } else if (currentWarehouse == null) {
                    // First line of warehouse: name,location
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        currentWarehouse = new Warehouse(parts[0], parts[1], new DynamicAlertService());
                        warehouses.add(currentWarehouse);
                    } else {
                        System.out.println("Invalid warehouse line skipped: " + line);
                    }
                } else {
                    // Product line
                    currentWarehouse.loadProductFromFile(line);
                }
            }
            System.out.println("Warehouses loaded from file: " + filename);
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        } finally {
            try { if (reader != null) reader.close(); } catch (IOException e) {}
        }
    }
}
