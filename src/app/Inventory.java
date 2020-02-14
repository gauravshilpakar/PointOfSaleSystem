package app;

import java.util.*;
import java.util.Scanner;

class Inventory {
    public static HashMap<String, data> Inventory = new HashMap<>();

    void AddInventory() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name of the Item:");
        String itemName = input.nextLine();
        System.out.println("Enter price of item: ");
        double price = input.nextInt();
        System.out.println("Enter quantiy: ");
        int stock = input.nextInt();
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
        for (Map.Entry<String, data> entry : Inventory.entrySet())
            System.out.println(entry.getKey() + "\t\t" + entry.getValue().stock + "\t\t" + entry.getValue().price);
    }

    void Quicklookup() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the item name: ");
        String itemName = input.nextLine();
        System.out.println("Item\t\tQuantity\t\tPrice");
        System.out.println(itemName + "\t\t" + Inventory.get(itemName).stock + "\t\t" + Inventory.get(itemName).price);
    }
}

class data {
    int stock;
    double price;

    public data(int stock, double price) {
        this.stock = stock;
        this.price = price;
    }
}