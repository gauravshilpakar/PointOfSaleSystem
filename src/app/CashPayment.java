package app;

import java.sql.ResultSet;

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
