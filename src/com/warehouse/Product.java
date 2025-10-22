package com.warehouse;

public class Product {

    // idCounter ko package-access banaya taaki Warehouse class se update ho sake
    static int idCounter = 1;

    private int id;
    private String name;
    private int quantity;
    private int reorderThreshold;

    /**
     * Constructor: initialize product with name, quantity, and reorder threshold.
     * Auto-assign unique ID using idCounter.
     */
    public Product(String name, int quantity, int reorderThreshold) {
        this.id = idCounter++;
        this.name = name;
        this.quantity = quantity;
        this.reorderThreshold = reorderThreshold;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setReorderThreshold(int reorderThreshold) {
        this.reorderThreshold = reorderThreshold;
    }

    @Override
    public String toString() {
        return String.format("ID=%d | Name=%s | Quantity=%d | Minimum Stock=%d",
                id, name, quantity, reorderThreshold);
    }
}
