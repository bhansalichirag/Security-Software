package main.java.dal;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import main.java.dal.accounts.Account;
import main.java.dal.users.customers.Customer;
import main.java.dal.users.employees.Employee;

@Entity(name = "CashiersCheck")
public class CashiersCheck {

	@Id
	@Column(updatable = false, nullable = false)
	private String cashierscheckid;
	@OneToOne(fetch = FetchType.EAGER)
	private Account issuingAccount;
	@OneToOne(fetch = FetchType.LAZY)
	private Customer recievingCustomer;
	private double amount;
	@OneToOne(fetch = FetchType.EAGER)
	private Employee issuer;
	@Temporal(TemporalType.DATE)
	private Date requestedDate;
	@Temporal(TemporalType.DATE)
	private Date issueDate;
	@Temporal(TemporalType.DATE)
	private Date cashingDate;
	private boolean isIssued;
	private boolean isCashed;
	
	public CashiersCheck()
	{
		
	}
	
	public CashiersCheck(Account issuingAccount, Customer recievingCustomer, double amount) 
	{
		this.issuingAccount = issuingAccount;
		this.amount = amount;
		this.recievingCustomer = recievingCustomer;
		this.requestedDate = new Date();
		this.cashierscheckid = issuingAccount.getAccountNumber() + "CCX" + (requestedDate.getTime()/1000);
	}

	public Employee getIssuer() {
		return issuer;
	}


	public void setIssuer(Employee issuer) {
		this.issuer = issuer;
	}


	public Date getIssueDate() {
		return issueDate;
	}


	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}


	public Date getCashingDate() {
		return cashingDate;
	}


	public void setCashingDate(Date cashingDate) {
		this.cashingDate = cashingDate;
	}


	public boolean isIssued() {
		return isIssued;
	}


	public void setIssued(boolean isIssued) {
		this.isIssued = isIssued;
	}


	public boolean isCashed() {
		return isCashed;
	}


	public void setCashed(boolean isCashed) {
		this.isCashed = isCashed;
	}


	public String getCashierscheckid() {
		return cashierscheckid;
	}


	public Account getIssuingAccount() {
		return issuingAccount;
	}


	public Customer getRecievingCustomer() {
		return recievingCustomer;
	}


	public Date getRequestedDate() {
		return requestedDate;
	}

	public double getAmount() {
		return amount;
	}
	
}
