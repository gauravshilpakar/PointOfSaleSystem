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


    void sales() { 
        Scanner in2 = new Scanner(System.in);
        System.out.println("Enter name of item you want to buy: ");
        String itemName = in2.nextLine();
        if (Inventory.containsKey(itemName)) {
            System.out.println("How many units of " + itemName + " do you want to buy? ");
            int itemQuantity = in2.nextInt();  
        if (itemQuantity > Inventory.get(itemName).stock){
            System.out.println("Unfortunately " + itemQuantity + " units of " +  itemName + "is not currently in our stock ! ");         
        }    
        else {
            Inventory.get(itemName).stock -= itemQuantity;
        }
        }
        else{
            System.out.println("This item is not in our inventory !! ");
        }
    }

    void reporting() {
        Set<Map.Entry<String, data>> entrySet = Inventory.entrySet();
        System.out.println("The following items are low in stock: ");
        for (Map.Entry<String, data> entry : entrySet) {
            data data1 = entry.getValue();
            if (data1.stock < 5){
            System.out.println(entry.getKey());
            }       
    }
}

}
    // class oldStock extends Inventory {

    // @Override
    // void addInventory(String item, int price, int quantity) {

    // }
    // }

