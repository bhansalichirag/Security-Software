package main.java.dal.accounts;

import javax.persistence.Entity;

@Entity
public class CheckingAccount extends Account {

	public CheckingAccount() {
	}
	
	public CheckingAccount(Double balance) {
		super(balance, 0.0);
		// TODO Auto-generated constructor stub
	}

}
