package main.java.dal.users.customers;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Merchant extends Customer {

	public Merchant() {
	}
	
	public Merchant(String firstName, String middleName, String lastName, String username, Date dateOfBirth, String password,
			String phoneNumber, String email, String address, String ssn, String seqQuestion, String seqQuestion2) {
		super(firstName, middleName, lastName, username, dateOfBirth, password, phoneNumber, email, address, ssn, seqQuestion, seqQuestion2);
		// TODO Auto-generated constructor stub
	}

}
