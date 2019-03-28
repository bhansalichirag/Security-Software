package main.java.business.services;

import java.util.Date;

import main.java.dal.users.User;

public interface IUserServices {

	public User ValidateUser(String username, String password);

	boolean CreateCustomerUser(String customerType, String firstName, String middleName, String lastName,
			String username, Date dateOfBirth, String password, String phoneNumber, String email, String address,
			String ssn, String seqQuestion, String seqQuestion2);

	boolean CreateEmployeeUser(String employeeType, String firstName, String middleName, 
			String lastName, String username, Date dateOfBirth, 
			String phoneNumber, String email);
	
	boolean DeleteUser(String username);
	boolean UpdateUser(String firstName, String middleName, String lastName, String username, Date dateOfBirth,
			String password, String phoneNumber, String email, String address, String ssn, String seqQuestion,
			String seqQuestion2);

	boolean updatePassword(String username, String oldPassword, String newPassword);

	boolean isNewUser(String username);

	boolean UpdateEmployee(String firstName, String middleName, String lastName, String username, Date dateOfBirth,
			String password, String phoneNumber, String email);

	boolean AccountExistsAndBelongsToLastName(int accountNumber, String lastName);

	User GetCustomerByUsername(String username);

}
