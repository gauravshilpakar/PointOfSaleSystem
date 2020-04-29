package app;

import java.util.Scanner;

public class CreditCardPayment {
    boolean status = false;

    public CreditCardPayment(double totalPrice) throws Exception {
        Scanner in5 = new Scanner(System.in);
        System.out.println("Enter Card No: ");
        int card_no = in5.nextInt();
        System.out.println("Enter Expiry Date: ");

        // Read the leftover new line
        in5.nextLine();
        String expiry_date = in5.nextLine();
        System.out.println(expiry_date);
        System.out.println("Enter ZipCode: ");
        int zipcode = in5.nextInt();
        Database.creditTransaction(card_no, expiry_date, zipcode, totalPrice, "CREDITSALES");
        status = true;
    }

}
