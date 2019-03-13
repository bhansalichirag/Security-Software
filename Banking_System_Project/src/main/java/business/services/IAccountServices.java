package main.java.business.services;

public interface IAccountServices {

	boolean MakePayment(int payerAccount, int recipientAccount, double amount);

}
