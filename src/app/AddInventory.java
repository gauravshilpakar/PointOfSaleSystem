package app;

import java.util.*;

class AddInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    AddInventory(String item, int amount) {
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
        Set<Map.Entry<String, Integer>> dI = inventory.entrySet();
        for (Map.Entry<String, Integer> ite : dI) {
            System.out.println(ite.getValue());
        }
    }

}