package main.java.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.dal.Transaction;
import main.java.dal.accounts.Account;
import main.java.dal.accounts.CheckingAccount;
import main.java.dal.accounts.CreditCard;
import main.java.dal.accounts.SavingsAccount;
import main.java.dal.users.customers.Customer;
import main.java.dal.users.customers.Individual;
import main.java.repositories.AccountRepository;
import main.java.repositories.TransactionRepository;
import main.java.repositories.UserRepository;


@Service
public class UserServiesImpl implements IUserServices {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public void createUser() {
		Individual customer = new Individual("Walther", null, "White", "wwhite75", null, "password", "4804808000", "wwh@wwe", "somewhere", "123476409", "Super", null);
		Account account = new SavingsAccount(100000.0, 2.0);
		accountRepository.save(account);
		List<Account> accs = customer.getAccountsList();
		if (accs == null)
			accs = new ArrayList<Account>();
		accs.add(account);
		account = new SavingsAccount(20000.0, 5.0);
		accountRepository.save(account);
		accs.add(account);
		account = new CheckingAccount(500.0);
		accountRepository.save(account);
		accs.add(account);
		account = new CreditCard(500.0,-27.5);
		accountRepository.save(account);
		accs.add(account);
		userRepository.save(customer);
	}
	
	public boolean ValidateUser(String username, String password)
	{
		Account accnt = accountRepository.findById(288).get();
		List<Transaction> transacts = accnt.getTransactions();
		if (transacts == null)
			transacts = new ArrayList<Transaction>();
		for(int i = 0; i<10; i++)
		{
			Transaction transact = new Transaction(accnt, accountRepository.findById((int)(Math.random()*200) + 77).get(), (int)(Math.random()*800) + 200.0);
			if(Math.random() < 0.6)
			{
				transact.setApprovalStatus(true);
			}
			transactionRepository.save(transact);
			transacts.add(transact);
		}
		accountRepository.save(accnt);
		System.out.println("Done!");
		return false;
	}
	
	public Customer GetCustomerAccountData(String username)
	{
		Customer user = (Customer) userRepository.findById(username).get();
		user.setPassword("");
		return user;
	}

}
