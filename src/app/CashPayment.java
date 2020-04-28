package app;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author
 */
public class CashPayment {
    public boolean status = false;

    public CashPayment(double givenCash, double totalPrice) {
        if (givenCash < totalPrice) {
            System.out.println("Insufficient Amount");
        } else {
            double change = givenCash - totalPrice;
            System.out.println("Change owed: " + change);
            status = true;
        }
    }

}
