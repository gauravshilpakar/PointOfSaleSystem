public class Payment
{

private double amount; //amount to 0

public Payment()
{
amount = 0.0;
}

public Payment(double Payment)
{
amount = 0.00;
}

public void setPayment(double paymentAmount)
{
amount = paymentAmount;
}

public double getPayment()
{
return amount;
}

public void paymentDetails()
{
System.out.println("The payment amount is " + amount);
}
public static void main(String[] args)
{

Payment cash1 = new CashPayment(50.5);
Payment cash2 = new CashPayment(20.45);
Payment credit1 =
new CreditCardPayment(10.5, "Raju", "2/14/2020", "123456789");
Payment credit2 =
new CreditCardPayment(100, "Parnesh", "1/12/2020", "1234567890");
System.out.println("Cash 1 details:");
cash1.paymentDetails();
System.out.println();
System.out.println("Cash 2 details:");
cash2.paymentDetails();
System.out.println();
System.out.println("Credit 1 details:");
credit1.paymentDetails();
System.out.println();
System.out.println("Credit 2 details:");
credit2.paymentDetails();
System.out.println();
}
}