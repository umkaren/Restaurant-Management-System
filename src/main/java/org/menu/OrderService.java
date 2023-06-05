package org.menu;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders;

    public OrderService () {
        this.orders = new ArrayList<>();
    }

    //Place new order
    public void placeOrder (Order order) {
        orders.add(order);
        System.out.println("Order successfully placed. Order ID " + order.getOrderId());
    }

    //Update order status
    public void updateOrderStatus (int orderId, String status) {
        Order order = getOrderById(orderId);
        if (order != null) {
            order.setStatus(status);
            System.out.println("Order updated successfully. Order ID: " + orderId);
        } else {
            System.out.println("Order not found. Order Id: " + orderId);
        }
    }

    //Get order by ID
    public Order getOrderById (int orderId) {
        for
    }



    //Get all orders
}
