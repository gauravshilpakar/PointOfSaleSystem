package app;

import java.util.*;

public class App {

    public static void main(String[] args) {
<<<<<<< HEAD
=======

>>>>>>> 410304e898bafdb537fe325a60257a52a7959737
        Scanner input = new Scanner(System.in);
        Inventory inventory = new Inventory();
        int number;
        do {
            System.out.println("Welcome to Application");
            System.out.println("Enter 1 for Add Inventory");
            System.out.println("Enter 2 for Display all Items");
            System.out.println("Enter 3 for Quicklookup");
            System.out.println("Enter 4 for Reporting");
            System.out.println("Enter 5 for Sales");
            System.out.println("Enter 6 to terminate application");
            number = input.nextInt();

            switch (number) {
            case (1):
                inventory.addInventory();
                break;

            case (2):
                inventory.displayInventory();
                break;

            case (3):
                inventory.quickLookup();
                break;

            case (4):
                break;

            case (5):
                break;

            case (6):
                break;

            }

        } while (number != 6);
        input.close();
    }
}