package main.java.business.services;

public interface IAccountServices {

	boolean MakePayment(int payerAccount, int recipientAccount, double amount);
	boolean MakePaymentToPrimary(int payerAccount, String RecipientEmail, String RecipientPhoneNumber, double amount);

}
