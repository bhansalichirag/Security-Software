package main.java.dal.users.employees;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Tier2 extends Employee {

	public Tier2() {
		
	}
	public Tier2(String firstName, String middleName, String lastName, String username, Date dateOfBirth, String password,
			String phoneNumber, String email) {
		super(firstName, middleName, lastName, username, dateOfBirth, password, phoneNumber, email);
		// TODO Auto-generated constructor stub
	}

}
