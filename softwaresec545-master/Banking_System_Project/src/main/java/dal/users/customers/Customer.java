package main.java.dal.users.customers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import main.java.dal.accounts.Account;
import main.java.dal.users.User;

@Entity(name = "Customer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Customer extends User{

	@OneToMany(fetch=FetchType.EAGER)
	private List<Account> accountsList;
	private String address;
	@Column(name = "ssn", nullable = false, unique = true, columnDefinition = "string default \"0\"")
	private String ssn;
	private String seqQuestion;
	private String seqQuestion2;
	@OneToOne(fetch=FetchType.EAGER)
	private Account primaryAccount;
	
	public Customer() {
	}
	
	public Customer(String firstName,
			String middleName,
			String lastName,
			String username,
			Date dateOfBirth,
			String password,
			String phoneNumber,
			String email,
			String address,
			String ssn,
			String seqQuestion,
			String seqQuestion2)
	{
		super(firstName,middleName,lastName,username,
				dateOfBirth,password,phoneNumber,email);
		this.accountsList = new ArrayList<Account>();
		this.address = address;
		this.ssn = ssn;
		this.seqQuestion = seqQuestion;
		this.seqQuestion2 = seqQuestion2;
	}
	
	public void addAccount(Account account) {
		accountsList.add(account);
	}
	
	public List<Account> getAccountsList() {
		return accountsList;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getSeqQuestion() {
		return seqQuestion;
	}
	public void setSeqQuestion(String seqQuestion) {
		this.seqQuestion = seqQuestion;
	}

	public String getSeqQuestion2() {
		return seqQuestion2;
	}

	public void setSeqQuestion2(String seqQuestion2) {
		this.seqQuestion2 = seqQuestion2;
	}

	public Account getPrimaryAccount() {
		return primaryAccount;
	}

	public void setPrimaryAccount(Account primaryAccount) {
		this.primaryAccount = primaryAccount;
	}
}
