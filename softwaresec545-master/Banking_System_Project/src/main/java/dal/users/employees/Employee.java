package main.java.dal.users.employees;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import main.java.dal.users.User;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Employee extends User{

	
	private String employeeID;
	
	public Employee() {
	}
	
	public Employee(String firstName, String middleName, String lastName, String username, Date dateOfBirth, String password,
			String phoneNumber, String email) {
		
		super(firstName, middleName, lastName, username, dateOfBirth, password, phoneNumber, email);
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	

	
}
