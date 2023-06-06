package org.menu;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

//Represents an individual order
public class Order {

    //Name and prices of items declared as constants
    private static final String CHICKEN_TENDERS = "Chicken Tenders";
    private static final String CHICKEN_SANDWICH = "Chicken Sandwich";
    private static final String GRILLED_CHEESE = "Grilled Cheese";
    private static final String FRIES = "Fries";
    private static final String BOTTLE_WATER = "Bottle Water";
    private static final String SODA = "Soda";

    private static final double PRICE_CHICKEN_TENDERS = 5.0;
    private static final double PRICE_CHICKEN_SANDWICH = 6.0;
    private static final double PRICE_GRILLED_CHEESE = 6.0;
    private static final double PRICE_FRIES = 2.0;
    private static final double PRICE_BOTTLE_WATER = 1.0;
    private static final double PRICE_SODA = 1.0;

    private int orderID;
    private Map<String, Integer> itemsOrdered;//Stores items and amounts
    private int totalPrice;
    private String orderStatus;//Status to show waiting, preparing, or ready
//    private Map<String, Integer> itemPrices;

    //Stores prices
    private static Map<String, Double> itemPrices = createItemPrices();

    //Constructor
    public Order(int orderID, Map<String, Integer> itemsOrdered, int totalPrice, String orderStatus) {
        this.orderID = orderID;
        this.itemsOrdered = itemsOrdered;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
    }
    // Getters and setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Map<String, Integer> getItemsOrdered() {
        return itemsOrdered;
    }

    public void setItemsOrdered(Map<String, Integer> itemsOrdered) {
        this.itemsOrdered = itemsOrdered;
    }

    public int getTotalPriceD() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderID +
                "\nItems Ordered: " + itemsOrdered +
                "\nTotal Price: " + totalPrice +
                "\nOrder Status: " + orderStatus + "\n";
    }

    //Method creates and returns item prices
    private static Map<String, Double> createItemPrices() {
        Map<String, Double> prices = new HashMap<>();
        prices.put(CHICKEN_TENDERS, PRICE_CHICKEN_TENDERS);
        prices.put(CHICKEN_SANDWICH, PRICE_CHICKEN_SANDWICH);
        prices.put(GRILLED_CHEESE, PRICE_GRILLED_CHEESE);
        prices.put(FRIES, PRICE_FRIES);
        prices.put(BOTTLE_WATER, PRICE_BOTTLE_WATER);
        prices.put(SODA, PRICE_SODA);
        return prices;
    }
    // Method to retrieve the price of an item
    public static double getItemPrice(String itemName) {
        if (itemPrices.containsKey(itemName)) {
            return itemPrices.get(itemName);
        } else {
            throw new IllegalArgumentException("Item not found: " + itemName);
        }
    }

}
