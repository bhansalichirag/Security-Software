package main.java.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.dal.accounts.Account;
import main.java.dal.accounts.SavingsAccount;
import main.java.dal.users.customers.Individual;
import main.java.repositories.AccountRepository;
import main.java.repositories.UserRepository;


@Service
public class UserServiesImpl implements IUserServices {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public void createUser() {
		Individual customer = new Individual("John", null, "Doe", "jhdoe", null, "password", "4804808000", "abc@abc", "somewhere", "123456409", "Super", null);
		Account account = new SavingsAccount(100000.0, 2.0);
		accountRepository.save(account);
		List<Account> accs = customer.getAccountsList();
		if (accs == null)
			accs = new ArrayList<Account>();
		accs.add(account);
		userRepository.save(customer);
	}

	@Override
	public boolean validateUser(String username,String password) {
		
		if(username ==null||password==null) {
			return false;
		}
		if(username.equals("SYSTEM")&&password.contentEquals("system")) {
			return true;
		}
		
		return false;
		
		// TODO Auto-generated method stub
		
		
		
	}

}