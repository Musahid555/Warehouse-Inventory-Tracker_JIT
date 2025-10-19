package com.warehouse;

/**
 * DynamicAlertService implements AlertService to handle low stock notifications.
 * Provides logical alert handling that can be extended in future.
 * Author: Musahid Mansuri
 * Date: 19-Oct-2025
 */
public class DynamicAlertService implements AlertService {

    @Override
    public void notifyLowStock(String productName, int quantity) {
        // Logical dynamic alert
        System.out.println("ALERT: Attention! Product '" + productName + "' is low in stock. Current quantity: " + quantity);
    }
}
