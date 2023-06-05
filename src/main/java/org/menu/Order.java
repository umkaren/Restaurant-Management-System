package org.menu;

import java.util.List;

public class Order {

    private int orderID;
    private List<MenuItem> items;
    private int totalPrice;
    private String orderStatus;

    public Order(int orderID, List<MenuItem> items, int totalPrice, String orderStatus) {
        this.orderID = orderID;
        this.items = items;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
    }
    //Gettters and setters

}
