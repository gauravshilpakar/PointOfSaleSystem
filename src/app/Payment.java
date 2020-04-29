package app;

import java.util.Scanner;

public class Payment {
    public boolean isCashTransaction = false;

    public boolean payment(double totalPrice) throws Exception {
        Scanner in3 = new Scanner(System.in);
        System.out.println("Please Select Payment Option: ");
        System.out.println("1.\t\tCash");
        System.out.println("2.\t\tCredit Card");
        int option = in3.nextInt();
        switch (option) {
            case (1): {
                isCashTransaction = true;
                System.out.println("Enter Amount: ");
                int givenCash = in3.nextInt();
                CashPayment cash = new CashPayment(givenCash, totalPrice);
                return cash.status;
            }
            case (2): {
                isCashTransaction = false;
                CreditCardPayment cc = new CreditCardPayment(totalPrice);
                return cc.status;
            }
            default:
                return false;
        }
    }
}
