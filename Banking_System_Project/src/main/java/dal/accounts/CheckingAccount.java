package main.java.dal.accounts;

import javax.persistence.Entity;

@Entity
public class CheckingAccount extends Account {

	public CheckingAccount(Integer accountNumber, Double balance, Double interest) {
		super(accountNumber, balance, interest);
		// TODO Auto-generated constructor stub
	}

}
