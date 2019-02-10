package main.java.dal.accounts;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import main.java.dal.Transaction;


@Entity(name = "Account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Account {

	@CreatedDate
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AccountNumber", updatable = false, nullable = false)
	private Integer accountNumber;
	private Double balance;
	private Double interest;
	@OneToMany(fetch=FetchType.EAGER)
	private List<Transaction> Transactions;
	
	public Account(Integer accountNumber,
			Double balance,
			Double interest)
	{
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interest = interest;
	}
	
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Double getInterest() {
		return interest;
	}
	public void setInterest(Double interest) {
		this.interest = interest;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public Double getBalance() {
		return balance;
	}
	public List<Transaction> getTransactions() {
		return Transactions;
	}
	
	
}
