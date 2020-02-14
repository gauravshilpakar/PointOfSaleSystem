package app;

import java.util.*;
import java.util.Scanner;

class data {
    // update this to a database???

    int stock;
    double price;

    public data(int stock, double price) {
        this.stock = stock;
        this.price = price;
    }
}

class Inventory {
    public static HashMap<String, data> Inventory = new HashMap<>();
    Scanner in1 = new Scanner(System.in);

    void addInventory() {
        System.out.println("Enter name of the item: ");
        String itemName = in1.nextLine();
        System.out.println("Enter price of item: ");
        double price = in1.nextInt();
        System.out.println("Enter quantiy: ");
        int stock = in1.nextInt();
        if (Inventory.containsKey(itemName)) { // updating stock
            Inventory.get(itemName).stock += stock;
            if (Inventory.get(itemName).price != price) // updating price
                Inventory.get(itemName).price = price;
            System.out.println("New Stock of " + itemName + ": " + Inventory.get(itemName).stock + "\n");
        } else {
            Inventory.put(itemName, new data(stock, price));
            System.out.println(itemName + " added to inventory.\n");
        }
    }

    void displayInventory() {
        System.out.println("\nItem\t\tQuantity\t\tPrice");
        if (!Inventory.isEmpty()) {
            for (Map.Entry<String, data> entry : Inventory.entrySet()) {
                System.out.println(entry.getKey() + "\t\t" + entry.getValue().stock + "\t\t" + entry.getValue().price);
            }
        }
        System.out.println("\n");
    }

    void quickLookup() {
        System.out.println("Enter the item name: ");
        String itemName = in1.nextLine();
        if (Inventory.containsKey(itemName)) {
            System.out.println("Item\t\tQuantity\t\tPrice");
            System.out.println(
                    itemName + "\t\t" + Inventory.get(itemName).stock + "\t\t" + Inventory.get(itemName).price);
        } else {
            System.out.println("Item not found!\n");
        }

    }

    // class oldStock extends Inventory {

    // @Override
    // void addInventory(String item, int price, int quantity) {

    // }
    // }
}
