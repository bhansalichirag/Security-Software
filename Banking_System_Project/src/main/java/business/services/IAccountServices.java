package main.java.business.services;

import main.java.dal.accounts.Account;
import main.java.dal.accounts.CreditCard;
import main.java.dal.users.customers.Customer;

public interface IAccountServices {

	boolean MakePayment(int payerAccount, int recipientAccount, double amount);
	boolean MakePaymentToPrimary(int payerAccount, String RecipientEmail, String RecipientPhoneNumber, double amount);
	Account CreateAccount(Customer customer, String accountType);
	boolean AccountExists(int accountNumber);
	boolean MakePaymentToMerchant(CreditCard payer, CreditCard payee, double amount);
	boolean TakePayment(int customeraccount, int cvv, CreditCard merchant, double amount);
	Account GetAccount(int accountNumber);
}
