package main.java.business.services;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.dal.users.User;
import main.java.dal.users.customers.Customer;
import main.java.dal.users.customers.Individual;
import main.java.dal.users.customers.Merchant;
import main.java.dal.users.employees.Employee;
import main.java.dal.users.employees.Tier1;
import main.java.dal.users.employees.Tier2;
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
	
	public User ValidateUser(String username, String password)
	{
		Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
		if(user.isPresent())
		{
			return user.get();
		}
		return null;
	}
	
	@Override
	public boolean CreateCustomerUser(String customerType, String firstName, String middleName, 
			String lastName, String username, Date dateOfBirth, String password, 
			String phoneNumber, String email, String address, String ssn, String seqQuestion, 
			String seqQuestion2)
	{
		Customer customer = null;
		if("Individual".equals(customerType))
		{
			customer = new Individual(firstName, middleName, lastName, 
					username, dateOfBirth, password, phoneNumber, email, 
					address, ssn, seqQuestion, seqQuestion2);
		}
		else
		{
			customer = new Merchant(firstName, middleName, lastName, 
					username, dateOfBirth, password, phoneNumber, email, 
					address, ssn, seqQuestion, seqQuestion2);
		}
		Optional<User> user = userRepository.findById(username);//added on 23/03/2019 for checking if the username already exists 
		try 
		{
			if(!user.isPresent())
			{
				userRepository.save(customer);
				return true;
			}
			else
			{
				return false;
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean CreateEmployeeUser(String employeeType, String firstName, String middleName, 
			String lastName, String username, Date dateOfBirth, 
			String phoneNumber, String email)
	{
		Employee employee = null;
		if("Tier1".equals(employeeType))
		{
			employee = new Tier1(firstName, middleName, lastName, 
					username, dateOfBirth, null, phoneNumber, email);
		}
		else if("Tier2".equals(employeeType))
		{
			employee = new Tier2(firstName, middleName, lastName, 
					username, dateOfBirth, null, phoneNumber, email);
		}
		Optional<User> user = userRepository.findById(username);//added on 23/03/2019 for checking if the username already exists
		try 
		{
			if(!user.isPresent())
			{
				userRepository.save(employee);
				return true;
			}
			else
			{
				return false;
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean DeleteUser(String username)
	{
		try 
		{
			Optional<User> user = userRepository.findById(username);
			if(user.isPresent() && user.get() instanceof Employee)
			{
				userRepository.deleteById(username);
				return true;
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean isNewUser(String username)
	{
		Optional<User> user = userRepository.findById(username);
		if(user.isPresent() && user.get().getPassword() == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public boolean updatePassword(String username, String oldPassword, String newPassword)
	{
		try 
		{
			Optional<User> user = userRepository.findById(username);
			if(user.isPresent())
			{
				User currentUser = user.get();
				if(currentUser instanceof User && 
						(currentUser.getPassword() == null || currentUser.getPassword().equals(oldPassword)))
				{
					currentUser.setPassword(newPassword);
					userRepository.save(currentUser);
					return true;
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean UpdateUser(String firstName, String middleName, 
			String lastName, String username, Date dateOfBirth, String password, 
			String phoneNumber, String email, String address, String ssn, String seqQuestion, 
			String seqQuestion2)
	{
		Optional<User> user = userRepository.findById(username);
		if(user.isPresent())
		{
			Customer customer = (Customer) user.get();
			if(firstName != null && !"".equals(firstName))
				customer.setFirstName(firstName);
			if(middleName != null && !"".equals(middleName))
				customer.setMiddleName(middleName);
			if(lastName != null && !"".equals(lastName))
				customer.setLastName(lastName);
			if(dateOfBirth != null)
				customer.setDateOfBirth(dateOfBirth);
			if(password != null && !"".equals(password))
				customer.setPassword(password);
			if(phoneNumber != null && !"".equals(phoneNumber))
				customer.setPhoneNumber(phoneNumber);
			if(email != null && !"".equals(email))
				customer.setEmail(email);
			if(address != null && !"".equals(address))
				customer.setAddress(address);
			if(ssn != null && !"".equals(ssn))
				customer.setSsn(ssn);
			if(seqQuestion != null && !"".equals(seqQuestion))
				customer.setSeqQuestion(seqQuestion);
			if(seqQuestion2 != null && !"".equals(seqQuestion2))
				customer.setSeqQuestion2(seqQuestion2);

			try 
			{
				userRepository.save(customer);
				return true;
			} 
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
		
		return false;
	}
	
//	public boolean PopulateAccounts(String username, String password)
//	{
//		Account accnt = accountRepository.findById(288).get();
//		List<Transaction> transacts = accnt.getTransactions();
//		if (transacts == null)
//			transacts = new ArrayList<Transaction>();
//		for(int i = 0; i<10; i++)
//		{
//			Transaction transact = new Transaction(accnt, accountRepository.findById((int)(Math.random()*200) + 77).get(), (int)(Math.random()*800) + 200.0);
//			if(Math.random() < 0.6)
//			{
//				transact.setApprovalStatus(true);
//			}
//			transactionRepository.save(transact);
//			transacts.add(transact);
//		}
//		accountRepository.save(accnt);
//		System.out.println("Done!");
//		return false;
//	}

//	@Override
//	public void createUser() {
//		Individual customer = new Individual("Walther", null, "White", "wwhite75", null, "password", "4804808000", "wwh@wwe", "somewhere", "123476409", "Super", null);
//		Account account = new SavingsAccount(100000.0, 2.0);
//		accountRepository.save(account);
//		List<Account> accs = customer.getAccountsList();
//		if (accs == null)
//			accs = new ArrayList<Account>();
//		accs.add(account);
//		account = new SavingsAccount(20000.0, 5.0);
//		accountRepository.save(account);
//		accs.add(account);
//		account = new CheckingAccount(500.0);
//		accountRepository.save(account);
//		accs.add(account);
//		account = new CreditCard(500.0,-27.5);
//		accountRepository.save(account);
//		accs.add(account);
//		userRepository.save(customer);
//	}

}
