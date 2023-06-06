package org.menu;

import java.io.*;
import java.util.*;

public class MenuService {
    Scanner scanner = new Scanner(System.in);
    private static final String MENU_FILE = "menu.txt";
    private static final Scanner scanner = new Scanner(System.in);
    private static List<MenuItem> menuItems = new ArrayList<>();

    public static void main(String[] args) {
        menuItems = loadMenu();

        while (true) {
            System.out.println("Menu Management");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Edit Item");
            System.out.println("4. Display Menu");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addItem();
                    saveMenu();
                    break;
                case 2:
                    removeItem();
                    saveMenu();
                    break;
                case 3:
                    editItem();
                    saveMenu();
                    break;
                case 4:
                    displayMenu();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static List<MenuItem> loadMenu() {
        try {
            File file = new File(MENU_FILE);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));

                List<MenuItem> menuItems = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    MenuItem menuItem = MenuItem.fromStringWithDelimiters(line);
                    menuItems.add(menuItem);
                }

                reader.close();
                return menuItems;
            }
        } catch (IOException e) {
            System.out.println("Error loading menu from file: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    static void saveMenu() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE));

            for (MenuItem menuItem : menuItems) {
                String line = menuItem.toStringWithDelimiters();
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving menu to file: " + e.getMessage());
        }
    }

    static void addItem() {
        System.out.println("Enter the item details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Preparation Time: ");
        String prepTime = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        System.out.print("Ingredients (comma-separated): ");
        String ingredientsString = scanner.nextLine();
        List<String> ingredients = Arrays.asList(ingredientsString.split(","));

        MenuItem menuItem = new MenuItem(name, description, prepTime, price, ingredients);
        menuItems.add(menuItem);

        System.out.println("Menu item added successfully!");
    }

    static void removeItem() {
        System.out.print("Enter the name of the item to remove: ");
        String itemName = scanner.nextLine();

        boolean found = false;
        Iterator<MenuItem> iterator = menuItems.iterator();
        while (iterator.hasNext()) {
            MenuItem menuItem = iterator.next();
            if (menuItem.getName().equalsIgnoreCase(itemName)) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Menu item removed successfully!");
        } else {
            System.out.println("Item not found in the menu.");
        }
    }

    static void editItem() {
        System.out.print("Enter the name of the item to edit: ");
        String itemName = scanner.nextLine();

        boolean found = false;
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getName().equalsIgnoreCase(itemName)) {
                System.out.println("Enter new values (leave blank to keep the current value):");

                System.out.print("New name (" + menuItem.getName() + "): ");
                String name = scanner.nextLine();
                if (!name.isEmpty()) {
                    menuItem.setName(name);
                }

                System.out.print("New description (" + menuItem.getDescription() + "): ");
                String description = scanner.nextLine();
                if (!description.isEmpty()) {
                    menuItem.setDescription(description);
                }

                System.out.print("New preparation time (" + menuItem.getPrepTime() + "): ");
                String prepTime = scanner.nextLine();
                if (!prepTime.isEmpty()) {
                    menuItem.setPrepTime(prepTime);
                }

                System.out.print("New price (" + menuItem.getPrice() + "): ");
                String priceStr = scanner.nextLine();
                if (!priceStr.isEmpty()) {
                    double price = Double.parseDouble(priceStr);
                    menuItem.setPrice(price);
                }

                System.out.print("New ingredients (" + String.join(", ", menuItem.getIngredients()) + "): ");
                String ingredientsString = scanner.nextLine();
                if (!ingredientsString.isEmpty()) {
                    List<String> ingredients = Arrays.asList(ingredientsString.split(","));
                    menuItem.setIngredients(ingredients);
                }

                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Menu item edited successfully!");
        } else {
            System.out.println("Item not found in the menu.");
        }
    }

    static void displayMenu() {
        if (menuItems.isEmpty()) {
            System.out.println("Menu is empty.");
        } else {
            System.out.println("Menu:");
            for (MenuItem menuItem : menuItems) {
                System.out.println("\nName: " + menuItem.getName());
                System.out.println("Description: " + menuItem.getDescription());
                System.out.println("Preparation Time: " + menuItem.getPrepTime());
                System.out.println("Price: " + menuItem.getPrice());
                System.out.println("Ingredients: " + String.join(", ", menuItem.getIngredients()));
            }
        }
    }
}
