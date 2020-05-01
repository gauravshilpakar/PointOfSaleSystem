package app;

import java.sql.ResultSet;
import java.util.Scanner;

class Inventory {

    void addInventory() throws Exception {
        // UI.Display();
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
        ResultSet rs = Database.checkItems(itemName, "INVENTORY");
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
        System.out.println("\n");
        rs.close();
    }

    void quickLookup() throws Exception {
        Scanner in3 = new Scanner(System.in);
        System.out.println("Enter the item name: ");
        while (in3.hasNext("")) {
            System.out.println("Thats not a name");
            in3.next();
        }
        String itemName = in3.nextLine();
        ResultSet isEmpty = Database.checkItems(itemName, "INVENTORY");
        if (isEmpty.next()) {
            isEmpty.close();
            System.out.println("\nItem\t\tQuantity\tPrice");
            System.out.println("------------------------------------");

            ResultSet rs = Database.checkItems(itemName, "INVENTORY");
            while (rs.next()) {
                System.out.println(rs.getString("name") + "\t\t" + rs.getInt("stock") + "\t\t" + rs.getString("price"));
            }
            System.out.println("------------------------------------");

        } else {
            System.out.println("The selected item is not currently present in our inventory!");
        }
    }

    void sales() throws Exception {
        Payment payment = new Payment();
        Scanner in2 = new Scanner(System.in);
        ResultSet checkEmpty = Database.checkAll("INVENTORY");
        if (checkEmpty.next()) {
            checkEmpty.close();
            System.out.print("\nEnter name of item you want to buy: ");
            String itemName = in2.nextLine();
            ResultSet rs = Database.checkItems(itemName, "INVENTORY");

            String queryName = null;
            int queryStock = 0;
            double queryPrice = 0;

            while (rs.next()) {
                queryName = rs.getString("name");
                queryStock = rs.getInt("stock");
                queryPrice = rs.getDouble("price");
            }
            rs.close();
            System.out.println("Selected Item: " + queryName);
            if (queryName.equals(itemName)) {
                System.out.print("\nHow many units of " + itemName + " do you want to buy? ");
                while (!in2.hasNextInt()) {
                    System.out.println("Input a Valid Integer.");
                    in2.next();
                }
                int itemQuantity = in2.nextInt();

                if (itemQuantity > queryStock) {
                    System.out.println("Unfortunately " + itemQuantity + " units of " + itemName
                            + " is not currently in our stock!");
                } else {
                    double totalPrice = itemQuantity * queryPrice;
                    System.out.println("Grand Total: " + totalPrice);
                    boolean paymentStatus = payment.payment(totalPrice);
                    String transType;
                    if (payment.isCashTransaction) {
                        transType = "CASH";
                    } else {
                        transType = "CARD";
                    }
                    if (paymentStatus) {
                        queryStock -= itemQuantity;
                        System.out.println("You bought " + itemQuantity + " " + itemName + " at price "
                                + itemQuantity * queryPrice);
                        ResultSet forUpdating = Database.checkItems(itemName, "INVENTORY");
                        Database.updateItems(itemName, queryStock, queryPrice, forUpdating, "INVENTORY", false);
                        forUpdating.close();
                        Database.addItemsSales(itemName, itemQuantity, queryPrice, "SALES", transType);
                    } else {
                        System.out.println("Payment Declined!!");
                    }
                }
            }
        } else {
            System.out.println("Nothing in inventory!");
        }

    }

    void checkStock() throws Exception {
        System.out.println("\nThe following items are low in stock: ");
        System.out.println("Item\t\tQuantity");
        System.out.println("------------------------------------");
        ResultSet rs = Database.checkAll("INVENTORY");

        while (rs.next()) {
            String name = rs.getString("name");
            int stock = rs.getInt("stock");

            if (stock <= 5) {
                System.out.println(name + "\t\t" + stock);
            }
        }
    }

    void printReport() throws Exception {
        int totalQuantity = 0;
        double totalAmount = 0;
        ResultSet isEmpty = Database.checkAll("SALES");
        if (isEmpty.next()) {
            isEmpty.close();
            System.out.println("\nItem\t\tQuantity\tNet Sales\tTransaction");
            ResultSet rs = Database.checkAll("SALES");

            while (rs.next()) {
                String name = rs.getString("name");
                int stock = rs.getInt("stock");
                double price = rs.getDouble("price");
                String type = rs.getString("transactionType");
                totalAmount += stock * price;
                totalQuantity += stock;
                System.out.println(name + "\t\t" + stock + "\t\t" + stock * price + "\t\t" + type);
            }
            System.out.println("\nTotal\t\t" + totalQuantity + "\t\t" + totalAmount);

        } else {
            System.out.println("No Items Sold");
        }
    }
}
