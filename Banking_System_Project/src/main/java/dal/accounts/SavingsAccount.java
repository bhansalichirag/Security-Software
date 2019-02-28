package main.java.dal.accounts;

import javax.persistence.Entity;

@Entity
public class SavingsAccount extends Account {

	public SavingsAccount() {
	}
	
	public SavingsAccount(Double balance, Double interest) {
		super(balance, interest);
		// TODO Auto-generated constructor stub
	}

}
