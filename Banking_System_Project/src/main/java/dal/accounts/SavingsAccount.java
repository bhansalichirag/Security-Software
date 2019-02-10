package main.java.dal.accounts;

import javax.persistence.Entity;

@Entity
public class SavingsAccount extends Account {

	public SavingsAccount(Integer accountNumber, Double balance, Double interest) {
		super(accountNumber, balance, interest);
		// TODO Auto-generated constructor stub
	}

}
