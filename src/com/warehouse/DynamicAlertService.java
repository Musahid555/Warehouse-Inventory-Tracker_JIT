package com.warehouse;

public class DynamicAlertService implements AlertService {

    @Override
    public void notifyLowStock(String productName, int quantity) {
        System.out.println("ALERT: Attention! Product '" + productName + "' is low in stock. Current quantity: " + quantity);
    }
}
