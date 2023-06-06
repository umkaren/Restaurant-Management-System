package org.menu;

import java.awt.*;
import java.util.ArrayList;

public class RestaurantManagementSystem {

    public static void main(String[] args) {

        TableManager tableManager = new TableManager();

        // Add tables
        Table table1 = new Table(1, 2, "Available");
        Table table2 = new Table(2, 2, "Available");
        Table table3 = new Table(3, 2, "Available");
        Table table4 = new Table(4, 4, "Available");
        Table table5 = new Table(5, 4, "Available");

        tableManager.addTable(table1);
        tableManager.addTable(table2);
        tableManager.addTable(table3);
        tableManager.addTable(table4);
        tableManager.addTable(table5);

        InventoryService inventoryService = new InventoryService("C:\\CTAC\\Java Final Project\\Restaurant-Management-System\\src\\main\\java\\org\\menu");

        // Adding items to the inventory
        inventoryService.addIngredient("Potatoes", 100);
        inventoryService.addIngredient("Chicken tenders", 100);
        inventoryService.addIngredient("Oil", 100);
        inventoryService.addIngredient("Bread slices", 200);
        inventoryService.addIngredient("Cheese", 100);
        inventoryService.addIngredient("Lettuce", 100);
        inventoryService.addIngredient("Cucumber", 100);
        inventoryService.addIngredient("Ranch", 100);
        inventoryService.addIngredient("Tomato", 100);
        inventoryService.addIngredient("Water", 100);
        inventoryService.addIngredient("Coke", 100);

        // Checking the inventory status
        inventoryService.checkInventoryStatus();

        // Check ingredient quantity

        inventoryService.checkIngredientQuantity("Tomato");
        inventoryService.checkIngredientQuantity("Onion");

        // Use ingredient
        System.out.println("Order of fries is being prepared.");
        inventoryService.checkIngredientQuantity("Potatoes");
        inventoryService.useIngredient("Potatoes", 2);
        inventoryService.checkIngredientQuantity("Potatoes");

        // Use ingredient
        System.out.println("Order of chicken tenders is being prepared.");
        inventoryService.checkIngredientQuantity("Chicken tenders");
        inventoryService.useIngredient("Chicken tenders", 5);
        inventoryService.useIngredient("Oil", 2);

        // Checking the inventory status
        inventoryService.checkInventoryStatus();
        //menu
        private static List<MenuItem> menu = new ArrayList<>();
        MenuItem item1 = new MenuItem("Chicken Tenders", "Handbreaded. Tender and juicy.", 10, 5); //takes 10 minutes
        MenuItem item2 = new MenuItem("House Salad", "Tasty. For the health nut.", 8, 7); //takes 8 minutes
        MenuItem item3 = new MenuItem("Chicken Tender Sandwich", "Smack yo mama good", 5, 6); // takes 5 minutes

        MenuItem item4 = new MenuItem("Grilled Cheese", ""); //takes 5 minutes
        MenuItem item5 = new MenuItem("Fries", 2); // takes 8 minutes
        MenuItem item6 = new MenuItem("Bottle Water", 1); //  takes 1 minutes
        MenuItem item7 = new MenuItem("Soda", 2); //takes 1 minute

        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        menu.add(item4);
        menu.add(item5);
        menu.add(item6);
        menu.add(item7);
    }


}
