package main.java.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.dal.accounts.Account;
import main.java.dal.accounts.SavingsAccount;
import main.java.dal.emp.EmployeeUserDao;
import main.java.dal.users.User;
import main.java.dal.users.customers.Individual;
import main.java.dal.users.employees.Admin;
import main.java.dal.users.employees.Employee;
import main.java.repositories.AccountRepository;
import main.java.repositories.EmployeeRepository;
import main.java.repositories.UserRepository;


@Service
public class UserServiesImpl implements IUserServices {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private EmployeeRepository emprepository;
	@Autowired
	EmployeeUserDao dao;
	
	@Override
	public void createUser() {
		Individual customer = new Individual("John", null, "Doe", "jhdoe", null, "password", "4804808000", "abc@abc", "somewhere", "123456409", "Super", null);
		Account account = new SavingsAccount(100000.0, 2.0);
		accountRepository.save(account);
		List<Account> accs = customer.getAccountsList();
		if (accs == null)
			accs = new ArrayList<Account>();
		accs.add(account);
		System.out.println("hello");
		System.out.println();
		userRepository.save(customer);
		
	}

	@Override
	public Admin validateEmpUser(String userId, String password) throws Exception{
		
		Admin admin=new Admin("Pranay", null, "Jagtap", "psjagtap", null, "password", "4804808000", "admin@abc", "G01091383","somewhere", "852811234", "Super", null);
		userRepository.save(admin);
		System.out.println(userRepository);
		System.out.println("Saved Pranay");
		
		
		Admin emp = dao.checkLogin(userId,password);
		
		//If user does not exist return null object. Login will fail 
		if(emp==null) {
			return null;
		}
		//If user exists and username and password match return the user.
		else {
			return emp;
		}
		
	}
		
		
		
	

}
