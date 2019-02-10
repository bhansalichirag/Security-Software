package main.java.dal.users.customers;

import javax.persistence.Entity;

@Entity
public class Merchant extends Customer {

	public Merchant(String firstName, String middleName, String lastName, String username, String password,
			Integer phoneNumber, String email, String address, String ssn, String seqQuestion) {
		super(firstName, middleName, lastName, username, password, phoneNumber, email, address, ssn, seqQuestion);
		// TODO Auto-generated constructor stub
	}

}
