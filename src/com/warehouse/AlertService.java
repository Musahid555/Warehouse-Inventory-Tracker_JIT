package com.warehouse;

public interface AlertService {
    void notifyLowStock(String productName, int quantity);
}
