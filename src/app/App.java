package app;

import java.awt.event.*; 
import java.awt.Dimension;
import javax.swing.*;
import java.util.*;

public class App {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        // inventory.addInventory();
          
        JFrame f = new JFrame();
        JPanel panel = new JPanel(null);

        JTextField t1 = new JTextField("Welcome to Sales Application. Please choose from the following options");
        JButton b1=new JButton("Add Inventory");
        JButton b2=new JButton("Display all items");
        JButton b3=new JButton("Item lookup");
        JButton b4=new JButton("Sales Report");
        JButton b5=new JButton("Sale");
        JButton b6=new JButton("Terminate");
    
        
        t1.setBounds(600, 10, 600, 50); // x, y, width, height
        b1.setBounds(50, 600, 200, 200); // x, y, width, height
        b2.setBounds(250, 600, 200, 200);
        b3.setBounds(450, 600, 200, 200);
        b4.setBounds(650, 600, 200, 200);
        b5.setBounds(850, 600, 200, 200);
        b6.setBounds(1050, 600, 150, 200);


        b1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                   inventory.addInventory(); 
                    }  
                });  


        panel.add(t1);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);

        f.add(panel);
        f.setPreferredSize(new Dimension(1250, 850));
        f.pack();
        f.setVisible(true);

      
        Scanner input = new Scanner(System.in);

        int number = 0;
        System.out.println("---------------------------------");
        System.out.println("Welcome to Application");

        while (number != 6) {
            System.out.println("---------------------------------");
            System.out.println("Enter 1\t\tAdd Inventory");
            System.out.println("Enter 2\t\tDisplay all Items");
            System.out.println("Enter 3\t\tItem Lookup");
            System.out.println("Enter 4\t\tSales Report");
            System.out.println("Enter 5\t\tSales");
            System.out.println("Enter 6\t\tTerminate");
            boolean notInt = true;

            // validates input for an integer and if within range
            while (notInt) {
                try {
                    while (true) {
                        System.out.println("\nYour Number? ");
                        String value = input.nextLine();
                        number = Integer.parseInt(value);
                        if (number <= 6 & number >= 1) {
                            break;
                        }
                        System.out.println("Please Enter Number Within Range!");
                    }
                    notInt = false;
                    break;
                } catch (Exception e) {
                    System.out.println("Please Enter an Integer!");
                    continue;
                }
            }
            switch (number)

            {
            case (1):
                inventory.addInventory();
                break;

            case (2):
                inventory.displayInventory();
                inventory.checkStock();
                break;

            case (3):
                inventory.quickLookup();
                break;

            case (4):
                inventory.printReport();
                break;

            case (5):
                inventory.displayInventory();
                inventory.sales();
                break;

            case (6):
                System.out.println("Terminating!\n");
                break;

            }
        }
        input.close();
    }
}
