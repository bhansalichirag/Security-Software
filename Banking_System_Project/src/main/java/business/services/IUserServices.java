package main.java.business.services;

import main.java.dal.users.User;
import main.java.dal.users.employees.Admin;
import main.java.dal.users.employees.Employee;

public interface IUserServices {

	public void createUser();
	
	public Admin validateEmpUser(String username, String password) throws Exception;
}
