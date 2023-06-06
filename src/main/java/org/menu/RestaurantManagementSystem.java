package org.menu;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class RestaurantManagementSystem {

    public static void main(String[] args) {
        final String MENU_FILE = "menu.txt";
        final Scanner scanner = new Scanner(System.in);

        MenuService menuService = new MenuService();

        LoginSystem login = new LoginSystem();

        // Create lists to store employee usernames and hashed passwords
        List<String> employee = new ArrayList<>();
        List<String> password = new ArrayList<>();

        // Add employee usernames and hashed passwords to the lists
        employee.add("Karen(STAFF)");
        employee.add("Miosotis(STAFF)");
        employee.add("Swathi(MANAGER)");
        employee.add("Chitra(STAFF)");
        employee.add("John(MANAGER)");
        // Hash the password before storing
        password.add(login.hash("karen2023"));
        password.add(login.hash("miosotis2023"));
        password.add(login.hash("swathi2023"));
        password.add(login.hash("chitra2023"));
        password.add(login.hash("john2023"));


        // Create a UserService object with the employee and password lists
        UserService userService = new UserService(employee, password, scanner);

        System.out.println("\n*** HELLO, PLEASE SIGN IN ***\n");

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("Enter your name: ");
            String username = scanner.nextLine();
            System.out.println("Enter your password: ");
            String pw = scanner.nextLine();
            loggedIn = userService.login(username, pw);

            if (!loggedIn) {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        // Authentication
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String pwd = scanner.nextLine();


        // Check user role
        String role = UserAuthentication.authenticateUser(username, employee, password);
        if (role.equals("manager")) {
            // Manager menu options
            System.out.println("Welcome, Manager!");
            menuService.loadMenu();

            int choice;
            do {
                System.out.println("Manager Menu:");
                System.out.println("1. View Menu");
                System.out.println("2. Add Menu Item");
                System.out.println("3. Remove Menu Item");
                System.out.println("4. Edit Menu Item");
                System.out.println("5. Generate Sales Report");
                System.out.println("6. Check Inventory Status");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        menuService.displayMenu();
                        break;
                    case 2:
                        menuService.addItem();
                        menuService.saveMenu();
                        break;
                    case 3:
                        menuService.removeItem();
                        menuService.saveMenu();
                        break;
                    case 4:
                        menuService.editItem();
                        menuService.saveMenu();
                    case 5:
                        InventoryService inventoryService = new InventoryService("/Users/minhsmair/IdeaProjects/Restaurant-Management-System/src/main/java/org/menu");
                        inventoryService.checkInventoryStatus();
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 0);

        } else if (role.equals("staff")) {
            // Staff menu options
            System.out.println("Welcome, Staff!");
            menuService.loadMenu();

            int choice;
            do {
                System.out.println("Staff Menu:");
                System.out.println("1. Take Order");
                System.out.println("2. Assign Table");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        OrderService.placeOrder();
                        break;
                    case 2:
                        // code to assign a table
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                }


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


                InventoryService inventoryService = new InventoryService("/Users/minhsmair/IdeaProjects/Restaurant-Management-System/src/main/java/org/menu");


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
            }
        }

        // Creating an instance of OrderService
        OrderService orderService = new OrderService();

        //Creates from Order and names it order1
        Order order1 = new Order(1, createItemsOrdered1(), 21, "Preparing");
        orderService.placeOrder(order1);//Places actual order

        Order order2 = new Order(2, createItemsOrdered2(), 11, "Waiting");
        orderService.placeOrder(order2);

        //Updates order status
        orderService.updateOrderStatus(1, "Preparing");
        orderService.updateOrderStatus(2, "Waiting");

        List<Order> allOrders = orderService.getAllOrders();
        System.out.println("All Orders:");
        for (Order order : allOrders) {
            System.out.println("Order ID: " + order.getOrderID() + ", Status: " + order.getOrderStatus());
        }
    }

    private static Map<String, Integer> createItemsOrdered1() {
        Map<String, Integer> itemsOrdered = new HashMap<>();
        itemsOrdered.put("Item1", 2);
        itemsOrdered.put("Item2", 1);
        return itemsOrdered;
    }

    private static Map<String, Integer> createItemsOrdered2() {
        Map<String, Integer> itemsOrdered = new HashMap<>();
        itemsOrdered.put("Item3", 1);
        itemsOrdered.put("Item4", 1);
        return itemsOrdered;
    }


}



