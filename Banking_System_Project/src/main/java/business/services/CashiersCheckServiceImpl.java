package main.java.business.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.business.exceptions.AccountNotFoundException;
import main.java.business.exceptions.CashierCheckNotFoundException;
import main.java.business.exceptions.TransactionFailedException;
import main.java.dal.CashiersCheck;
import main.java.dal.accounts.Account;
import main.java.dal.accounts.CreditCard;
import main.java.dal.users.User;
import main.java.dal.users.customers.Customer;
import main.java.dal.users.customers.Individual;
import main.java.dal.users.customers.Merchant;
import main.java.dal.users.employees.Employee;
import main.java.repositories.CashiersCheckRepository;
import main.java.repositories.UserRepository;

@Service
public class CashiersCheckServiceImpl implements ICashiersCheckService {


	@Autowired
	private CashiersCheckRepository cashiersCheckRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IAccountServices accountServices;
	
	@Override
	public boolean OrderCashiersCheck(String firstName, String middleName, String lastName, Account accountNumber, double amount)
	{
		Iterable<User> customers = userRepository.findAllByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName);
		
		for( User customer: customers) 
		{
			if(customer instanceof Customer)
			{
				Individual indCustomer = (Individual) customer;
				Account issuingAccount = accountNumber;
				CashiersCheck cashiersCheck = new CashiersCheck(issuingAccount, indCustomer, amount);
				cashiersCheckRepository.save(cashiersCheck);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean IssueCashiersCheck(String cashiersCheckID, Employee issuer)
	{
		Optional<CashiersCheck> cashiersCheckWrapper = cashiersCheckRepository.findById(cashiersCheckID);
		if(cashiersCheckWrapper.isPresent())
		{
			CashiersCheck cashiersCheck = cashiersCheckWrapper.get();
			if(cashiersCheck.isCashed() || cashiersCheck.isIssued())
				return false;
			
			cashiersCheck.setIssued(true);
			cashiersCheck.setIssueDate(new Date());
			cashiersCheck.setIssuer(issuer);
			cashiersCheckRepository.save(cashiersCheck);
			return true;
		}
		return false;
	}

	@Override
	public boolean DepositCashiersCheck(String cashiersCheckID, Individual customer, Account account) throws AccountNotFoundException, TransactionFailedException, CashierCheckNotFoundException
	{
		Optional<CashiersCheck> cashiersCheckWrapper = cashiersCheckRepository.findById(cashiersCheckID);
		if(cashiersCheckWrapper.isPresent())
		{
			CashiersCheck cashiersCheck = cashiersCheckWrapper.get();
			boolean match = customer.getAccountsList().stream().anyMatch(e -> e.getAccountNumber() == account.getAccountNumber());
			if(cashiersCheck.getRecievingCustomer().getFirstName() == customer.getFirstName() && 
					cashiersCheck.getRecievingCustomer().getMiddleName() == customer.getMiddleName() &&
					cashiersCheck.getRecievingCustomer().getLastName() == customer.getLastName() &&
					!(account instanceof CreditCard) && 
					match && cashiersCheck.isIssued() && !cashiersCheck.isCashed())
			{
				accountServices.MakePayment(cashiersCheck.getIssuingAccount().getAccountNumber(), account.getAccountNumber(), cashiersCheck.getAmount());
				cashiersCheck.setCashed(true);
				cashiersCheck.setCashingDate(new Date());
				cashiersCheckRepository.save(cashiersCheck);
				return true;
			}
			else
			{
				throw new CashierCheckNotFoundException();
			}
		}
		else
		{
			throw new CashierCheckNotFoundException();
		}
	}
	
}




