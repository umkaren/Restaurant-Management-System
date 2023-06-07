package org.menu;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RestaurantManagementSystem {

    public static void main(String[] args) {
        final String MENU_FILE = "menu.txt";
        final Scanner scanner = new Scanner(System.in);

        MenuService menuService = new MenuService();

//Beginning of login portion
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

        String hashedPassword = login.hash(password.toString());

        boolean exitProgram = false;

        // Create a UserService object with the employee and password lists
        UserService userService = new UserService(employee, password, scanner);
        while (!exitProgram) {
            System.out.println("\n*** HELLO, PLEASE SIGN IN ***\n");

            boolean loggedIn = false;
            String username = "";
            String pw = "";
            while (!loggedIn) {
                System.out.println("Enter your name: ");
                username = scanner.nextLine();
                System.out.println("Enter your password: ");
                pw = scanner.nextLine();
                loggedIn = userService.login(username, pw);
                System.out.println("Hashed Password: " + hashedPassword);

                if (!loggedIn) {
                    System.out.println("Invalid username or password. Please try again.");
                }
            }

            // Authentication
            UserAuthentication userAuth = new UserAuthentication();
            String role = userAuth.authenticateUser(username, employee, password, pw);
//        System.out.println("Role: " + role);
//        System.out.println("pwd" + pw);
            if (role != null) {

                if (role.equalsIgnoreCase("MANAGER")) {
                    // Manager menu options
                    System.out.println("Welcome, Manager!");
                    MenuItem menu = new MenuItem();
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
                        System.out.println("7. Add inventory ");
                        System.out.println("8. Logoff ");
                        System.out.println("0. Exit");
                        System.out.print("Enter your choice: ");
                        choice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        InventoryService inventoryService = new InventoryService();

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
                                break;
                            case 5:
                                // SalesReportService salesReportService = new SalesReportService();
                                //salesReportService.generateSalesReport();

                            case 6:
                                inventoryService.checkInventoryStatus();
                                break;
                            case 7:
                                System.out.println("Please enter the ingredient name to be added: ");
                                String name = scanner.nextLine();
                                System.out.println("Please enter the number of units you are adding: ");
                                int quantity = Integer.parseInt(scanner.nextLine());
                                inventoryService.addIngredient(name, quantity);
                                break;
                            case 8:
                                loggedIn = false;
                                System.out.println("Logged out successfully.");
                                break;
                            case 0:
                                loggedIn = false;
                                exitProgram = true;
                                System.out.println("Exiting...");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } while (loggedIn && !exitProgram && choice != 0 && choice != 8);

                    //menuService.saveMenuItems();
                } else if (role.equalsIgnoreCase("STAFF")) {
                    // Staff menu options
                    System.out.println("Welcome, Staff!");

                    TableManager tableManager = new TableManager();
                    MenuItem menu = new MenuItem();
                    menuService.loadMenu();
                    //  MenuService menuService = new MenuService();
                    //menuService.loadMenuItems();

                    int choice;
                    do {
                        System.out.println("Staff Menu:");

                        System.out.println("1. Take Order");
                        System.out.println("2. Assign Table");
                        System.out.println("3. Reserve Table");
                        System.out.println("4. Release Table");
                        System.out.println("5. Display all Tables");
                        System.out.println("6. Log Out");
                        System.out.println("0. Exit");
                        System.out.print("Enter your choice: ");
                        choice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        OrderService orderService = new OrderService();

                        switch (choice) {
                            case 1:
                                // Take order
                                orderService.placeOrder();
                                // ...
                                break;
                            case 2:
                                // Assign table
                                System.out.print("Enter party size: ");
                                int partySize = scanner.nextInt();
                                scanner.nextLine(); // Consume the newline character

                                // Find available table based on party size
                                List<Table> availableTablesForId = tableManager.findAvailableTable(partySize);

                                if (availableTablesForId.isEmpty()) {
                                    System.out.println("No available tables for a party size of " + partySize);
                                } else {
                                    System.out.println("Available Tables for a party size of " + partySize + ":");
                                    for (Table table : availableTablesForId) {
                                        System.out.println("Table ID: " + table.getTableId() + ", Size: " + table.getTableSize());
                                    }

                                    System.out.println("Enter the table Id to assign to the party?");
                                    int tableId = Integer.parseInt(scanner.nextLine());

                                    // Check if the selected table is available and has the correct party size
                                    Table selectedTable = tableManager.getTableById(tableId);
                                    if (selectedTable != null && selectedTable.getStatus().equals("Available") && selectedTable.getTableSize() >= partySize) {
                                        tableManager.assignCustomerToTable(tableId);
                                        System.out.println("Table " + tableId + " assigned to the party successfully.");
                                    } else {
                                        System.out.println("Invalid table selection. Please choose an available table with a size greater than or equal to the party size.");
                                    }
                                }

                                break;
                            case 3:
                                // Reserve table
                                System.out.print("Enter table ID to reserve: ");
                                int tableIdToReserve = scanner.nextInt();
                                scanner.nextLine(); // Consume the newline character
                                tableManager.reserveTable(tableIdToReserve);
                                break;
                            case 4:
                                // Release table
                                System.out.print("Enter table ID to release: ");
                                int tableIdToRelease = scanner.nextInt();
                                scanner.nextLine(); // Consume the newline character
                                Table tableToRelease = tableManager.getTableById(tableIdToRelease);
                                if (tableToRelease != null && tableToRelease.getStatus() != "Available") {
                                    tableToRelease.setStatus("Available");
                                    System.out.println("Table released successfully. Table ID: " + tableToRelease.getTableId());
                                } else {
                                    System.out.println("Table not found.");
                                }
                                break;
                            case 5:
                                // Display all tables
                                List<Table> availableTables = tableManager.getAllTables();

                                break;

                            case 6:
                                loggedIn = false;
                                System.out.println("Logged out successfully.");
                                break;
                            case 0:
                                System.out.println("Exiting...");
                                exitProgram = true;
                                loggedIn = false;
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } while (loggedIn && !exitProgram && choice != 0 && choice != 6);
                }
            } else {
                System.out.println("Invalid username or password. Exiting...");
            }
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

    // Creating an instance of OrderService
    OrderService orderService = new OrderService();

    //Creates from Order and names it order1
    Order order1 = new Order(1, createItemsOrdered1(), 21, "Preparing");
    orderService.placeOrder(order1);//Places actual order
    Order order2 = new Order(2, createItemsOrdered2(), 11, "Waiting");
    orderService.placeOrder(order2);


    //Updates order status
    orderService.updateOrderStatus(1,"Preparing");
    orderService.updateOrderStatus(2,"Waiting");

    List<Order> allOrders = orderService.getAllOrders();
        System.out.println("All Orders:");

        for (Order order : allOrders)
        System.out.println("Order ID: " + order.getOrderID() + ", Status: " + order.getOrderStatus());
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



