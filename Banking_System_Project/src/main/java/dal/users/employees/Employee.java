package main.java.dal.users.employees;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import main.java.dal.users.User;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Employee extends User{

	
	private String employeeID;
	private String address;
	@Column(name = "ssn", nullable = false, unique = true)
	private String ssn;
	private String seqQuestion;
	private String seqQuestion2;
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

	public Employee() {
	}
	
	public Employee(String firstName, String middleName, String lastName, String username, Date dateOfBirth, String password,
			String phoneNumber, String email, String employeeI,String address,
			String ssn,
			String seqQuestion,
			String seqQuestion2) {
		
		super(firstName, middleName, lastName, username, dateOfBirth, password, phoneNumber, email);
		this.employeeID = employeeID;
		this.address = address;
		this.ssn = ssn;
		this.seqQuestion = seqQuestion;
		this.seqQuestion2 = seqQuestion2;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	

	
}
