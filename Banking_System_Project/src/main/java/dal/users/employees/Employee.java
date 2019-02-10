package main.java.dal.users.employees;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import main.java.dal.users.User;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Employee extends User{

	
	private String employeeID;
	
	public Employee(String firstName, String middleName, String lastName, String username, String password,
			Integer phoneNumber, String email, String employeeID) {
		
		super(firstName, middleName, lastName, username, password, phoneNumber, email);
		this.employeeID = employeeID;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	

	
}
