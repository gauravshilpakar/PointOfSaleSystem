package app;

import java.util.*;

class Inventory {
    private Map<String, Integer> inventory = new HashMap<>();

    void AddInventory(String item, int amount) {
        if (inventory.containsKey(item)) {
            int stock = inventory.get(item);
            inventory.put(item, stock + amount);
            int newstock = inventory.get(item);
            System.out.println("New Stock of " + item + ": " + newstock);
        } else {
            inventory.put(item, amount);
            System.out.println(item + " added to inventory.");
        }
    }

    void displayInventory() {
        System.out.println("\nItem\t\t\tValue");
        for (Map.Entry<String, Integer> entry : inventory.entrySet())
            System.out.println(entry.getKey() + "\t\t\t" + entry.getValue());
    }
}