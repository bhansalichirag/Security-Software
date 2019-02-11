package main.java.dal.accounts;

import javax.persistence.Entity;

@Entity
public class CreditCard extends Account {

	public CreditCard() {
	}
	
	public CreditCard(Integer accountNumber, Double balance, Double interest) {
		super(accountNumber, balance, interest);
		// TODO Auto-generated constructor stub
	}

}
