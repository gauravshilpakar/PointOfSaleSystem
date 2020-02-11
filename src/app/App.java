package app;

// import java.util.*;

public class App {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        inventory.AddInventory("Coke", 10);
        inventory.AddInventory("Fanta", 10);
        inventory.AddInventory("Choc", 10);
        inventory.AddInventory("Test", 10);
        inventory.AddInventory("Coke", 10);
        inventory.displayInventory();
    }
}