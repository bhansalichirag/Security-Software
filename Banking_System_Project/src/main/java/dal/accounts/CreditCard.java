package main.java.dal.accounts;

import javax.persistence.Entity;

@Entity
public class CreditCard extends Account {

	public CreditCard() {
	}
	
	public CreditCard(Double balance, Double interest) {
		super(balance, interest);
		// TODO Auto-generated constructor stub
	}

}
