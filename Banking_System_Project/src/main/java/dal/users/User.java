package main.java.dal.users;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="UserType",
        discriminatorType = DiscriminatorType.STRING
    )
public abstract class User {
	
	private String firstName;
	private String middleName;
	private String lastName;
	@Id
	@Column(name = "username", updatable = false, nullable = true)
	private String username;
	private String password;
	private String phoneNumber;
	private String email;
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	public User() {
		
	}
	
	public User(String firstName,
			String middleName,
			String lastName,
			String username,
			Date dateOfBirth,
			String password,
			String phoneNumber,
			String email)
	{
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.username = username;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
