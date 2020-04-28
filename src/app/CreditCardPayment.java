package app;

import java.util.Scanner;

public class CreditCardPayment {
    boolean status = false;

    public CreditCardPayment(double totalPrice) {
        Scanner in5 = new Scanner(System.in);
        System.out.println("Enter Card No: ");
        int card_no = in5.nextInt();
        System.out.println("Enter Expiry Date: ");
        in5.next();
        String expiry = in5.nextLine();
        System.out.println("Enter ZipCode: ");
        int zipcode = in5.nextInt();
        status = true;
    }

}
