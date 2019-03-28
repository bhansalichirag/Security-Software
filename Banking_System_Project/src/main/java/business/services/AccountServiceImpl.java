package main.java.business.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.dal.Transaction;
import main.java.dal.accounts.Account;
import main.java.dal.accounts.CheckingAccount;
import main.java.dal.accounts.CreditCard;
import main.java.dal.accounts.SavingsAccount;
import main.java.dal.users.User;
import main.java.dal.users.customers.Customer;
import main.java.dal.users.employees.Employee;
import main.java.repositories.AccountRepository;
import main.java.repositories.TransactionRepository;
import main.java.repositories.UserRepository;

@Service
public class AccountServiceImpl implements IAccountServices {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private ITransactionServices transactionService;
	
	@Override
	public boolean MakePayment(int payerAccount, int recipientAccount, double amount)
	{
		Optional<Account> payerWrapper = accountRepository.findById(payerAccount);
		Optional<Account> payeeWrapper = accountRepository.findById(recipientAccount);
		if(payerWrapper.isPresent() && payeeWrapper.isPresent())
		{
			Account payer = payerWrapper.get();
			Account payee = payeeWrapper.get();
			Transaction transaction = new Transaction(payer, payee, amount);
			return CreatePaymentTransaction(transaction, payer, payee);
		}
		return false;
	}
	
	@Override
	public boolean MakePaymentToPrimary(int payerAccount, String RecipientEmail, String RecipientPhoneNumber, double amount)
	{
		Optional<User> user = null;
		if((RecipientEmail != null && !"".equalsIgnoreCase(RecipientEmail)) 
				&& (RecipientEmail != null && !"".equalsIgnoreCase(RecipientEmail)))
		{
			user = userRepository.findByEmailAndPhoneNumber(RecipientEmail, RecipientPhoneNumber);
		}
		else if(RecipientEmail != null && !"".equalsIgnoreCase(RecipientEmail))
		{
			user = userRepository.findByEmail(RecipientEmail);
		}
		else if(RecipientPhoneNumber != null && !"".equalsIgnoreCase(RecipientPhoneNumber))
		{
			user = userRepository.findByPhoneNumber(RecipientPhoneNumber);
		}
		else 
		{
			return false;
		}
		
		if(user.isPresent())
		{
			Customer payeeCustomer = (Customer) user.get();
			Optional<Account> payerWrapper = accountRepository.findById(payerAccount);
			Account payee = payeeCustomer.getPrimaryAccount();
			if(payerWrapper.isPresent() && payee != null)
			{
				Account payer = payerWrapper.get();
				Transaction transaction = new Transaction(payer, payee, amount);
				return CreatePaymentTransaction(transaction, payer, payee);
			}
		}
		return false;
	}
	
	@Override
	public Account CreateAccount(Customer customer, String accountType) 
	{
		Account account = null;
		if("Savings".equals(accountType))
		{
			account = new SavingsAccount(0.0, 5.0);
		}
		else if("Checking".equals(accountType))
		{
			account = new CheckingAccount(0.0);
		}
		else if("CreditCard".equals(accountType))
		{
			account = new CreditCard(0.0, -10.0);
		}
		else
		{
			return null;
		}
		
		accountRepository.save(account);
		
		if(customer.getAccountsList().isEmpty())
		{
			customer.setPrimaryAccount(account);
		}
		customer.addAccount(account);
		userRepository.save(customer);
		return account;
	}
	@Override
	public boolean ApproveAccount(Employee approver, int accountnum)
	{
		Optional<Account> accountWrapper = accountRepository.findById(accountnum);
		if(accountWrapper.isPresent())
		{
			AccountApprover(approver, accountWrapper.get(),true);
			return true;
		}
		return false;
	}
	@Override
	public boolean DeclineAccount(Employee approver, int accountnum)
	{
		Optional<Account> accountWrapper = accountRepository.findById(accountnum);
		if(accountWrapper.isPresent())
		{
			AccountApprover(approver, accountWrapper.get(),false);
			return true;
		}
		return false;
	}
	@Override
	public List<Account> getAllApprovedAccounts()
	{
		Iterable<Account> accounts = accountRepository.findAllByApprovalStatus(true);
		return AccountIterableToListHelper(accounts);
	}
	@Override
	public List<Account> getAllPendingAccounts()
	{
		Iterable<Account> accounts = accountRepository.findAllByApprovalStatusAndApprovalDate(false, null);
		return AccountIterableToListHelper(accounts);
	}
	@Override
	public List<Account> getAllDeclinedAccounts()
	{
		Iterable<Account> accounts = accountRepository.findAllByApprovalStatusAndApprovalDateNotNull(false);
		return AccountIterableToListHelper(accounts);
	}
	
	@Override
	public boolean PayCreditCardAccount(Account sourceAccount, CreditCard ccard, double amount)
	{
		Transaction transaction = new Transaction(sourceAccount, ccard, amount);
		return CreatePaymentTransaction(transaction, sourceAccount, ccard);
	}
	
	@Override
	public Account GetAccount(int accountNumber)
	{
		return accountRepository.findById(accountNumber).get();
	}

	@Override
	public boolean AccountExists(int accountNumber) {
		
		return accountRepository.existsById(accountNumber);
	}
	
	@Override
	public boolean MakePaymentToMerchant(CreditCard payer, CreditCard payee, double amount)
	{
		String valueString = payer.getAccountNumber().toString() + "~" + String.valueOf(amount);
		payee.addAuthorizedMerchants(valueString);
		accountRepository.save(payee);
		return false;
	}

	@Override
	public boolean TakePayment(int customeraccount, int cvv, CreditCard merchant, double amount)
	{
		Optional<Account> payerWrapper = accountRepository.findById(customeraccount);
		if(payerWrapper.isPresent())
		{
			CreditCard payer = (CreditCard) payerWrapper.get();
			Optional<String> entry = merchant.getAuthorizedMerchants().stream().filter(t -> {
				String[] strings = t.split("~");
				if(strings!=null && strings.length == 2 && strings[0].equals(String.valueOf(customeraccount)) && strings[1].equals(String.valueOf(amount)))
				{
					return true;
				}
				return false;
			}
			).findFirst();
			if(entry.isPresent())
			{
				if(payer.getCvv() == cvv)
				{
					MakePayment(customeraccount, merchant.getAccountNumber(), amount);
					return true;
				}
			}
		}
		return false;
	}
	
	private List<Account> AccountIterableToListHelper(Iterable<Account> accounts)
	{
		List<Account> accountsList = new ArrayList<Account>();
		for ( Account account : accounts) 
		{
			accountsList.add(account);
		}
		return accountsList;
	}
	
	private void AccountApprover(Employee approver, Account account, boolean action)
	{
		account.setApprovalDate(new Date());
		account.setApprovalStatus(action);
		account.setApprover(approver);
		accountRepository.save(account);
	}

	private boolean CreatePaymentTransaction(Transaction transaction, Account payer, Account payee)
	{
		if(transaction != null)
		{
			transactionRepository.save(transaction);
			payer.addTransaction(transaction);
			payee.addTransaction(transaction);
			accountRepository.save(payer);
			accountRepository.save(payee);
			return true;
		}
		return false;
	}
	
}










