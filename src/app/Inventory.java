package app;

import java.awt.event.*; 
import java.awt.Dimension;
import javax.swing.*;
import java.util.*;
import java.util.Scanner;

import javax.swing.JFrame;

import sun.jvm.hotspot.oops.IntField;

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
    public static HashMap<String, data> Sold = new HashMap<>();
    String itemName;
    String price_str = "0";
    String stock_str = "0";
    void addInventory() {
        JFrame f1 = new JFrame();
        JPanel panel1 = new JPanel(null);
        JLabel label1 = new JLabel("Adding items to inventory!");
        JLabel label2 = new JLabel("Enter name of the item: ");
        JLabel label3 = new JLabel("Enter price of the item: ");
        JLabel label4 = new JLabel("Enter Stock Quantity: ");
        JTextField text1 = new JTextField();
        JTextField text2 = new JTextField();
        JTextField text3 = new JTextField();
        //JButton btn1=new JButton("SUBMIT");
        //JButton btn2=new JButton("SUBMIT");
        JButton btn3=new JButton("SUBMIT");

        label1.setBounds(200, 10, 300, 50); // x, y, width, height
        
        label2.setBounds(200, 70, 300, 50); // x, y, width, height
        text1.setBounds(200, 125, 300, 50);
        //btn1.setBounds(510, 125, 100, 50);
        
        label3.setBounds(200, 180, 300, 50);
        text2.setBounds(200, 235, 300, 50);
        //btn2.setBounds(510, 235, 100, 50);
        
        label4.setBounds(200, 290, 300, 50);
        text3.setBounds(200, 345, 300, 50);
        btn3.setBounds(510, 345, 100, 50);


        /*btn1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                //itemName = (text1.getText());
                text1.setText(""); 
               //System.out.println(itemName);
                    }  
                });  */

        /* btn2.addActionListener(new ActionListener(){  
             public void actionPerformed(ActionEvent e){  
                 //price_str = (text2.getText());
                text2.setText(""); 
                 //System.out.println(price);
                      }  
                 });        
        */  

        btn3.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
               itemName = (text1.getText());
               price_str = (text2.getText());
               stock_str = (text3.getText());
                text3.setText(""); 
                 //System.out.println(stock);
                     }  
                });        
                
        double price = Double.parseDouble(price_str);  
        int stock = Integer.parseInt(stock_str);      
        System.out.println(price + " is the price.\n"+stock+" is the stock.");

       

        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(label4);
        panel1.add(text1);
        //panel1.add(btn1);
        panel1.add(text2);
        //panel1.add(btn2);
        panel1.add(text3);
        panel1.add(btn3);

        f1.add(panel1);
        f1.setPreferredSize(new Dimension(900, 600));
        f1.pack();
        f1.setVisible(true);

        /*if (Inventory.containsKey(itemName)) { // updating stock
            Inventory.get(itemName).stock += stock;
            if (Inventory.get(itemName).price != price) // updating price
                Inventory.get(itemName).price = price;
            System.out.println("New Stock of " + itemName + ": " + Inventory.get(itemName).stock + "\n");
        } else {
            Inventory.put(itemName, new data(stock, price));
            System.out.println(itemName + " added to inventory.\n");
        }*/


      /*  System.out.println("\nAdding items to inventory!");
        Scanner in1 = new Scanner(System.in);
        System.out.print("Enter name of the item: ");
        // a number can be a valid string for item name
        while (in1.hasNext("")) {
            System.out.println("Please Enter a Name.");
            in1.next();
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

        if (Inventory.containsKey(itemName)) { // updating stock
            Inventory.get(itemName).stock += stock;
            if (Inventory.get(itemName).price != price) // updating price
                Inventory.get(itemName).price = price;
            System.out.println("New Stock of " + itemName + ": " + Inventory.get(itemName).stock + "\n");
        } else {
            Inventory.put(itemName, new data(stock, price));
            System.out.println(itemName + " added to inventory.\n");
        }*/

    }

    void displayInventory() {
        System.out.println("\nItem\t\tQuantity\tPrice");
        if (!Inventory.isEmpty()) {
            for (Map.Entry<String, data> entry : Inventory.entrySet()) {
                System.out.println(entry.getKey() + "\t\t" + entry.getValue().stock + "\t\t" + entry.getValue().price);
            }
        }
        System.out.println("\n");
    }

    void quickLookup() {
        Scanner in3 = new Scanner(System.in);
        System.out.println("Enter the item name: ");
        while (in3.hasNext("")) {
            System.out.println("Thats not a name");
            in3.next();
        }
        String itemName = in3.nextLine();
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
        if (!Inventory.isEmpty()) {
            System.out.print("\nEnter name of item you want to buy: ");
            String itemName = in2.nextLine();
            if (Inventory.containsKey(itemName)) {
                System.out.print("How many units of " + itemName + " do you want to buy? ");
                while (!in2.hasNextInt()) {
                    System.out.println("Input a Valid Integer.");
                    in2.next();
                }
                int itemQuantity = in2.nextInt();

                if (itemQuantity > Inventory.get(itemName).stock) {
                    System.out.println("Unfortunately " + itemQuantity + " units of " + itemName
                            + "is not currently in our stock!");
                } else {
                    Inventory.get(itemName).stock -= itemQuantity;
                    System.out.println("You bought " + itemQuantity + " " + itemName + " at price "
                            + itemQuantity * Inventory.get(itemName).price);

                    if (Sold.containsKey(itemName)) {
                        Sold.get(itemName).stock += itemQuantity;
                    } else {
                        Sold.put(itemName, new data(itemQuantity, Inventory.get(itemName).price * itemQuantity));

                    }
                }
            }
        } else {
            System.out.println("Nothing in inventory!");
        }

    }

    void checkStock() {
        Set<Map.Entry<String, data>> entrySet = Inventory.entrySet();
        System.out.println("\nThe following items are low in stock: ");
        for (Map.Entry<String, data> entry : entrySet) {
            data data1 = entry.getValue();
            if (data1.stock < 5) {
                System.out.println(entry.getKey());
            }
        }
    }

    void printReport() {
        int totalQuantity = 0;
        double totalAmount = 0;

        if (!Sold.isEmpty()) {
            System.out.println("\nItem\t\tQuantity\tPrice");

            for (Map.Entry<String, data> entry : Sold.entrySet()) {
                System.out.println(entry.getKey() + "\t\t" + entry.getValue().stock + "\t\t" + entry.getValue().price);
                totalQuantity += entry.getValue().stock;
                totalAmount += entry.getValue().price;
            }
            System.out.println("\nTotal\t\t" + totalQuantity + "\t\t" + totalAmount);
        } else {
            System.out.println("No Items Sold");
        }
    }
}
// class oldStock extends Inventory {

// @Override
// void addInventory(String item, int price, int quantity) {

// }
// }
