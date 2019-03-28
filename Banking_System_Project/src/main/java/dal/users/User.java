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

import org.hibernate.annotations.ColumnTransformer;

import main.java.business.util.Constants;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="UserType",
        discriminatorType = DiscriminatorType.STRING
    )
public abstract class User {
	
//	@ColumnTransformer(write="AES_ENCRYPT(?," + Constants.key + ")",
//			read = "AES_DECRYPT(first_name," + Constants.key + ")" )
	private String firstName;
//	@ColumnTransformer(write="AES_ENCRYPT(?," + Constants.key + ")",
//			read = "AES_DECRYPT(middle_name," + Constants.key + ")" )
	private String middleName;
//	@ColumnTransformer(write="AES_ENCRYPT(?," + Constants.key + ")",
//			read = "AES_DECRYPT(last_name," + Constants.key + ")" )
	private String lastName;
	@Id
	@Column(name = "username", updatable = false, nullable = true)
	private String username;
//	@ColumnTransformer(write="AES_ENCRYPT(?," + Constants.key + ")",
//			read = "AES_DECRYPT(password," + Constants.key + ")" )
	private String password;
	@Column(unique = true)
//	@ColumnTransformer(write="AES_ENCRYPT(?," + Constants.key + ")",
//	read = "AES_DECRYPT(phone_number," + Constants.key + ")" )
	private String phoneNumber;
	@Column(unique = true)
//	@ColumnTransformer(write="AES_ENCRYPT(?," + Constants.key + ")",
//	read = "AES_DECRYPT(email," + Constants.key + ")" )
	private String email;
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	private Integer failedAttempts;
	
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
		this.failedAttempts = 0;
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
