package main.java.dal.accounts;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CreditCard extends Account {

	@Column(nullable = true)
	private int cvv;
	
	public CreditCard() {
	}
	
	public CreditCard(Double balance, Double interest) {
		super(balance, interest);
		this.cvv = 0;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

}
