package org.menu;

import java.util.List;
import java.util.Map;
import java.io.*;
import java.util.*;

public class SalesReport {
    private String date;
    private double totalRevenue;
    private Map<MenuItem, Integer> popularItems;
    private Map<Table, Double> tableSales;
    private List<Order> orders;

    public SalesReport(String date, double totalRevenue, Map<MenuItem, Integer> popularItems,
                       Map<Table, Double> tableSales, List<Order> orders) {
        this.date = date;
        this.totalRevenue = totalRevenue;
        this.popularItems = popularItems;
        this.tableSales = tableSales;
        this.orders = orders;
    }


    public void exportReport(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("----------------------------");
            writer.println("Daily Sales Report");
            writer.println("Date: " + date);
            writer.println("----------------------------");
            writer.println("Total Revenue: $" + totalRevenue);
            writer.println();
            writer.println("Most Popular Items:");
            int rank = 1;
            for (Map.Entry<MenuItem, Integer> entry : popularItems.entrySet()) {
                MenuItem item = entry.getKey();
                int quantity = entry.getValue();
                writer.println(rank + ". " + item.getName() + ": " + quantity + " orders");
                rank++;
            }
            writer.println();
            writer.println("Table Sales:");
            rank = 1;
            for (Map.Entry<Table, Double> entry : tableSales.entrySet()) {
                Table table = entry.getKey();
                double sales = entry.getValue();
                writer.println(rank + ". " + "Table " + table.getTableId() + ": $" + sales);
                rank++;
            }
            writer.println();
            writer.println("Detailed Orders:");
            for (Order order : orders) {
                writer.println("Order ID: " + order.getOrderId());
                writer.println("Table ID: " + order.getTableId());
                writer.println("Items:");
                for (MenuItem item : order.getItems()) {
                    writer.println("  - " + item.getName() + ": " + item.getQuantity() + " ($" + item.getPrice() + ")");
                }
                writer.println("Total: $" + order.getTotalPrice());
                writer.println();
            }
            System.out.println("Sales report exported successfully.");
        } catch (IOException e) {
            System.out.println("Failed to export sales report: " + e.getMessage());
        }
    }
}
