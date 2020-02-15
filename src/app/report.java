package app;

import java.util.*;

class report {

    public String name_of_Sitems;
    public int qty_of_Sitems;
    public int totalP_of_each_Items;
    public String total_Sitems;
    public float total_Samount;

    public void name_of_Sitems() {
        // return the n_items of sold items

    }

    public void qty_of_Sitems() {
        // returns the quantity of each sold items

    }

    public void totalP_of_each_Items() {
        // returns the price total of each sections
    }

    public void total_Sitems() {
        // returns the total number of sold items

    }

    public void total_Samount() {
        // returns the total price all sold items
        // returns the total tax calculated
        // returns the net selling price of items
    }

    public static void main(String[] args) {

        LinkedList<String> n_items = new LinkedList<String>();
        n_items.add("Coke");
        n_items.add("Cheetos");
        n_items.add("Chocolate");
        n_items.add("Marlboro");

        System.out.println("Name of Items: " + n_items.toString());

    }

}