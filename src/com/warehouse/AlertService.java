package com.warehouse;

/**
 * Interface Name: AlertService
 * Description: Defines method for triggering low stock alerts.
 * Author: Musahid Mansuri
 * Date: 18-Oct-2025
 */
public interface AlertService {

    void notifyLowStock(String productName, int quantity);
}
