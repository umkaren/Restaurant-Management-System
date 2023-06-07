package org.menu;

import java.io.*;
import java.util.*;

public class MenuItem {

    private static List<MenuItem> menuItems = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private String MENU_FILE;
    private String name;
    private String description;
    private double price;
    private int prepTime;
    private List<String> ingredients;

    public MenuItem(String MENU_FILE, String name, String description, int prepTime, double price, List<String> ingredients) {
        this.MENU_FILE = "/Users/minhsmair/TestingRestaurant/TestingRestaurant/src/main/java/example/menu.txt";
        this.name = name;
        this.description = description;
        this.prepTime = prepTime;
        this.price = price;
        this.ingredients = ingredients;
    }


    public List<MenuItem> loadMenu() {
        try {
            File file = new File(MENU_FILE);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));

                menuItems.clear(); // Clear the existing menu items

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

    public void saveMenu() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE));

            for (int i = 0; i < menuItems.size(); i++) {
                MenuItem menuItem = menuItems.get(i);
                String line = (i + 1) + ". " + menuItem.toStringWithDelimiters();
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving menu to file: " + e.getMessage());
        }
    }


    public void addItem() {
        System.out.println("Enter the item details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Preparation Time: ");
        int prepTime = Integer.parseInt(scanner.nextLine());
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

    public void removeItem() {
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

    public void editItem() {
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
                String prepTimeStr = scanner.nextLine();
                if (!prepTimeStr.isEmpty()) {
                    try {
                        int prepTime = Integer.parseInt(prepTimeStr);
                        menuItem.setPrepTime(prepTime);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for preparation time. Keeping the current value.");
                    }
                }

                System.out.print("New price (" + menuItem.getPrice() + "): ");
                String priceStr = scanner.nextLine();
                if (!priceStr.isEmpty()) {
                    try {
                        double price = Double.parseDouble(priceStr);
                        menuItem.setPrice(price);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for price. Keeping the current value.");
                    }
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


    public void displayMenu() {
        if (menuItems.isEmpty()) {
            System.out.println("Menu is empty.");
        } else {
            System.out.println("Menu:");
            for (MenuItem menuItem : menuItems) {
                System.out.println("\nName: " + menuItem.getName());
                System.out.println("Description: " + menuItem.getDescription());
                System.out.println("Price: " + menuItem.getPrice());
            }
        }
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription() {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice () {
        this.price = price;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

}
