package main.java.dal.accounts;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import main.java.dal.Transaction;
import main.java.dal.users.employees.Employee;

@Entity(name = "Account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="AccountType",
        discriminatorType = DiscriminatorType.STRING
    )
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
	@OneToOne(fetch = FetchType.EAGER)
	private Employee approver;
	@Temporal(TemporalType.DATE)
	private Date approvalDate;
	private boolean approvalStatus; 
	@OneToMany(fetch=FetchType.EAGER)
	private List<Transaction> Transactions;
	private Integer failedAttempts;
	
	public Account() {
	}
	
	public Account(Double balance,
			Double interest)
	{
		this.creationDate = new Date();
		this.balance = balance;
		this.interest = interest;
		this.failedAttempts = 0;
		this.approvalStatus = false;
	}
	
	public Employee getApprover() {
		return approver;
	}

	public void setApprover(Employee approver) {
		this.approver = approver;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public boolean isApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(boolean approvalStatus) {
		this.approvalStatus = approvalStatus;
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

	public Integer getFailedAttempts() {
		return failedAttempts;
	}

	public void incrementFailedAttempts(Integer failedAttempts) {
		this.failedAttempts++;
	}
	
	public void clearFailedAttempts(Integer failedAttempts) {
		this.failedAttempts = 0;
	}
	
	
}
