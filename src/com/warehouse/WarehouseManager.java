package com.warehouse;

import java.util.ArrayList;
import java.util.List;

/**
 * WarehouseManager manages multiple Warehouse objects.
 * Provides functionality to add warehouses and select a warehouse by ID.
 */
public class WarehouseManager {

    private List<Warehouse> warehouses;

    public WarehouseManager() {
        warehouses = new ArrayList<Warehouse>();
    }

    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
        System.out.println("New warehouse added: " + warehouse.getName() + " (" + warehouse.getLocation() + "). Total warehouses: " + warehouses.size());
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public Warehouse selectWarehouse(int index) {
        if (index < 1 || index > warehouses.size()) {
            System.out.println("Invalid warehouse selection.");
            return null;
        }
        return warehouses.get(index - 1);
    }

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
}
