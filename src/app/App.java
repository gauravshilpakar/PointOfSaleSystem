package app;

public class App {
    public static void main(String[] args) {

        Inventory inv = new Inventory();

        inv.AddInventory("Coke", 100);
        inv.AddInventory("Snicker", 200);
        inv.AddInventory("Marlboro", 20);
        inv.AddInventory("Cheetos", 40);
        inv.AddInventory("Bud Light", 10);
        inv.AddInventory("Coke", 100);

        inv.displayInventory();
    }
}
