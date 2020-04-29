package app;

import java.sql.ResultSet;

import java.util.*;
import java.util.Scanner;

class Inventory {

    void addInventory() throws Exception {
        System.out.println("\nAdding items to inventory!");
        Scanner in1 = new Scanner(System.in);
        System.out.print("Enter name of the item: ");
        // a number can be a valid string for item name
        while (in1.hasNext("")) {
            System.out.println("Thats not a name");
            in1.nextLine();
        }
        String itemName = in1.nextLine();

        System.out.print("Enter price: ");
        while (!in1.hasNextDouble()) {
            System.out.println("Input a Valid Integer.");
            in1.next();
        }
        double price = in1.nextDouble();

        System.out.print("Enter Stock Quantity: ");
        while (!in1.hasNextInt()) {
            System.out.println("Input a Valid Integer.");
            in1.next();
        }
        int stock = in1.nextInt();

        Database.addItems(itemName, stock, price, "INVENTORY");
        System.out.println("Item Details...\n");
        ResultSet rs = Database.checkItems(itemName);
        while (rs.next()) {
            System.out.println("Item:\t" + rs.getString("name"));
            System.out.println("Stock:\t" + rs.getInt("stock"));
            System.out.println("Price:\t" + rs.getDouble("price"));
        }
        rs.close();
    }

    void displayInventory() throws Exception {
        System.out.println("\nItem\t\tQuantity\tPrice");
        System.out.println("------------------------------------");
        ResultSet rs = Database.checkAll("INVENTORY");
        while (rs.next()) {
            System.out.println(rs.getString("name") + "\t\t" + rs.getInt("stock") + "\t\t" + rs.getString("price"));
        }
        System.out.println("------------------------------------");
        // if (!Inventory.isEmpty()) {
        // for (Map.Entry<String, data> entry : Inventory.entrySet()) {
        // System.out.println(entry.getKey() + "\t\t" + entry.getValue().stock + "\t\t"
        // + entry.getValue().price);
        // }
        // }
        System.out.println("\n");
        rs.close();
    }

    // void quickLookup() {
    // Scanner in3 = new Scanner(System.in);
    // System.out.println("Enter the item name: ");
    // while (in3.hasNext("")) {
    // System.out.println("Thats not a name");
    // in3.next();
    // }
    // String itemName = in3.nextLine();
    // if (Inventory.containsKey(itemName)) {
    // System.out.println("Item\t\tQuantity\t\tPrice");
    // System.out.println(
    // itemName + "\t\t" + Inventory.get(itemName).stock + "\t\t" +
    // Inventory.get(itemName).price);
    // } else {
    // System.out.println("Item not found!\n");
    // }
    // }

    void sales() throws Exception {
        Payment payment = new Payment();
        Scanner in2 = new Scanner(System.in);
        ResultSet checkEmpty = Database.checkAll("INVENTORY");
        if (checkEmpty.next()) {
            checkEmpty.close();
            System.out.print("\nEnter name of item you want to buy: ");
            String itemName = in2.nextLine();
            ResultSet rs = Database.checkItems(itemName);

            String queryName = null;
            int queryStock = 0;
            double queryPrice = 0;

            while (rs.next()) {
                queryName = rs.getString("name");
                queryStock = rs.getInt("stock");
                queryPrice = rs.getDouble("price");
            }
            rs.close();
            System.out.println(queryName);
            System.out.println(itemName);
            if (queryName.equals(itemName)) {
                System.out.print("\nHow many units of " + itemName + " do you want to buy? ");
                while (!in2.hasNextInt()) {
                    System.out.println("Input a Valid Integer.");
                    in2.next();
                }
                int itemQuantity = in2.nextInt();

                if (itemQuantity > queryStock) {
                    System.out.println("Unfortunately " + itemQuantity + " units of " + itemName
                            + "is not currently in our stock!");
                } else {

                    double totalPrice = itemQuantity * queryPrice;
                    System.out.println("Grand Total: " + totalPrice);
                    boolean paymentStatus = payment.payment(totalPrice);
                    if (paymentStatus) {
                        queryStock -= itemQuantity;
                        System.out.println("You bought " + itemQuantity + " " + itemName + " at price "
                                + itemQuantity * queryPrice);
                        ResultSet forUpdating = Database.checkItems(itemName);
                        Database.updateItems(itemName, queryStock, queryPrice, forUpdating, "INVENTORY", false);
                        forUpdating.close();

                        Database.addItems(itemName, itemQuantity, queryPrice, "SALES");
                    } else {
                        System.out.println("Payment Declined!!");
                    }
                }
            }
        } else {
            System.out.println("Nothing in inventory!");
        }

    }

    // void checkStock() {
    // Set<Map.Entry<String, data>> entrySet = Inventory.entrySet();
    // System.out.println("\nThe following items are low in stock: ");
    // for (Map.Entry<String, data> entry : entrySet) {
    // data data1 = entry.getValue();
    // if (data1.stock < 5) {
    // System.out.println(entry.getKey());
    // }
    // }
    // }

    void printReport() throws Exception {
        int totalQuantity = 0;
        double totalAmount = 0;
        ResultSet isEmpty = Database.checkAll("SALES");

        if (isEmpty.next()) {
            isEmpty.close();
            System.out.println("\nItem\t\tQuantity\tPrice");
            ResultSet rs = Database.checkAll("SALES");

            while (rs.next()) {
                System.out.println(rs.getString("name") + "\t\t" + rs.getInt("stock") + "\t\t" + rs.getString("price"));
            }
            System.out.println("\nTotal\t\t" + totalQuantity + "\t\t" + totalAmount);
        } else {
            System.out.println("No Items Sold");
        }
    }

}
