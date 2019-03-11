package main.java.business.services;

import main.java.dal.users.customers.Customer;

public interface IUserServices {

	public void createUser();
	public boolean ValidateUser(String username, String password);
	public Customer GetCustomerAccountData(String username);
}
