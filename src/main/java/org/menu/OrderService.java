package org.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Manages the orders
public class OrderService {

    //Method - returns order with by orderID orders
    private List<Order> orders;

    //Constructor that initializes 'orders' list as an empty array
    public OrderService () {
        orders = new ArrayList<>();
    }

    public static void placeOrder(Order order) {
        orders.add(order);
        System.out.println("Order ID: " + order.getOrderID() + " placed.");
    }

    public void updateOrderStatus(int orderID, String newOrderStatus) {
        Order order = getOrderByID(orderID);

        if (order != null) {
            order.setOrderStatus(newOrderStatus);
            System.out.println("Order ID: " + orderID + " updated.");
        } else {
            System.out.println("Order ID " + orderID + " not found.");
        }
    }
    public Order getOrderByID (int orderID) {

        for (Order order : orders) {
            if (order.getOrderID() == orderID) {
                return order;
            }
        }
        return null;
    }
    //Method - returns all orders
    public List<Order> getAllOrders() {
        return orders;
    }

    //Method to calculate items total price
    public int calculateTotalPrice(Order order) {
        Map<String, Integer> itemsOrdered = order.getItemsOrdered();
        int totalPrice = 0;

        for (Map.Entry<String, Integer> entry : itemsOrdered.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            double itemPrice = Order.getItemPrice(itemName);
            totalPrice += itemPrice * quantity;
        }
        return totalPrice;
    }


}

