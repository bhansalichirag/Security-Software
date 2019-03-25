package main.java.business.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.dal.Transaction;
import main.java.dal.accounts.Account;
import main.java.dal.users.User;
import main.java.dal.users.customers.Customer;
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
			transactionRepository.save(transaction);
			if(transaction != null)
			{
				payer.addTransaction(transaction);
				payee.addTransaction(transaction);
				accountRepository.save(payer);
				accountRepository.save(payee);
				return true;
			}
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
				transactionRepository.save(transaction);
				if(transaction != null)
				{
					payer.addTransaction(transaction);
					payee.addTransaction(transaction);
					accountRepository.save(payer);
					accountRepository.save(payee);
					return true;
				}
			}
		}
		return false;
	}

	
}
