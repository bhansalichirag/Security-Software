package main.java.dal.users.employees;

import javax.persistence.Entity;

@Entity
public class Tier1 extends Employee {

	public Tier1() {
		
	}
	
	public Tier1(String firstName, String middleName, String lastName, String username, String password,
			String phoneNumber, String email, String employeeID) {
		super(firstName, middleName, lastName, username, password, phoneNumber, email, employeeID);
		// TODO Auto-generated constructor stub
	}

}
