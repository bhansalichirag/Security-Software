package main.java.business.services;

import main.java.dal.accounts.Account;
import main.java.dal.users.customers.Individual;
import main.java.dal.users.employees.Employee;

public interface ICashiersCheckService {

	boolean OrderCashiersCheck(String firstName, String middleName, String lastName, Account account, double amount);

	boolean DepositCashiersCheck(String cashiersCheckID, Individual customer, Account account);

	boolean IssueCashiersCheck(String cashiersCheckID, Employee issuer);

}
