package com.warehouse;

/**
 * ConsoleAlertService implements AlertService to simulate low stock alerts.
 * Author: Musahid Mansuri
 * Date: 18-Oct-2025
 */
public class ConsoleAlertService implements AlertService {

    @Override
    public void notifyLowStock(String productName, int quantity) {
        System.out.println("ALERT: Low stock for " + productName + " – only " + quantity + " left! [Console Alert]");
    }
}
