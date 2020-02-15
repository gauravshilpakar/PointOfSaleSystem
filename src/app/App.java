package app;

import java.util.*;

public class App {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        // inventory.addInventory();

        Scanner input = new Scanner(System.in);

        int number = 0;
        System.out.println("---------------------------------");
        System.out.println("Welcome to Application");

        while (number != 6) {
            System.out.println("---------------------------------");
            System.out.println("Enter 1\t\tAdd Inventory");
            System.out.println("Enter 2\t\tDisplay all Items");
            System.out.println("Enter 3\t\tQuicklookup");
            System.out.println("Enter 4\t\tReporting");
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
