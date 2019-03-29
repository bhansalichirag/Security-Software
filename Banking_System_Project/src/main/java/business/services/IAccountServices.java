package main.java.business.services;

import java.util.List;

import main.java.business.exceptions.AccountNotFoundException;
import main.java.business.exceptions.CustomerNotFoundException;
import main.java.business.exceptions.TransactionFailedException;
import main.java.dal.accounts.Account;
import main.java.dal.accounts.CreditCard;
import main.java.dal.users.customers.Customer;
import main.java.dal.users.employees.Employee;

public interface IAccountServices {

	boolean MakePayment(int payerAccount, int recipientAccount, double amount) throws AccountNotFoundException, TransactionFailedException;
	boolean MakePaymentToPrimary(int payerAccount, String RecipientEmail, String RecipientPhoneNumber, double amount) throws TransactionFailedException, CustomerNotFoundException, AccountNotFoundException;
	Account CreateAccount(Customer customer, String accountType);
	boolean AccountExists(int accountNumber);
	boolean MakePaymentToMerchant(CreditCard payer, CreditCard payee, double amount);
	boolean TakePayment(int customeraccount, int cvv, CreditCard merchant, double amount) throws AccountNotFoundException, TransactionFailedException;
	Account GetAccount(int accountNumber);
	boolean DeclineAccount(Employee approver, int accountnum);
	boolean ApproveAccount(Employee approver, int accountnum);
	List<Account> getAllApprovedAccounts();
	List<Account> getAllPendingAccounts();
	List<Account> getAllDeclinedAccounts();
	boolean PayCreditCardAccount(Account sourceAccount, CreditCard ccard, double amount) throws TransactionFailedException;
}
