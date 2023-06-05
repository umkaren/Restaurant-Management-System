package org.menu;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {

    private Map<String, InventoryItem> inventory;

    public InventoryService() {
        inventory = new HashMap<>();
    }

    public void addIngredient(String name, int quantity) {
        InventoryItem item = inventory.get(name);
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            inventory.put(name, new InventoryItem(name, quantity));
        }
    }

        public void checkInventoryStatus() {
            System.out.println("Inventory Status:");
            inventory.forEach((name, item) -> System.out.println(name + ": " + item.getQuantity()));
        }


}
