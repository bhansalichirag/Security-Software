package main.java.dal.users.customers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import main.java.dal.users.User;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Customer extends User{

	private List<Object> accountsList;
	private String address;
	private String ssn;
	private String seqQuestion;
	
	public Customer(String firstName,
			String middleName,
			String lastName,
			String username,
			String password,
			Integer phoneNumber,
			String email,
			String address,
			String ssn,
			String seqQuestion)
	{
		super(firstName,middleName,lastName,username,password,phoneNumber,firstName);
		this.accountsList = new ArrayList<Object>();
		this.address = address;
		this.ssn = ssn;
		this.seqQuestion = seqQuestion;
	}
	
	
	public List<Object> getAccountsList() {
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
}
