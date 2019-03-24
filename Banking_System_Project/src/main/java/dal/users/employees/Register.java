package main.java.dal.users.employees;

import java.util.Date;

public class Register {
private String firstname, middlename, lastname, username, password, confirmpassword, phonenumber, email, access, gender ;
public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getConfirmpassword() {
	return confirmpassword;
}

public void setConfirmpassword(String confirmpassword) {
	this.confirmpassword = confirmpassword;
}

private Date DOB;

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

public String getAccess() {
	return access;
}

public void setAccess(String access) {
	this.access = access;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPhonenumber() {
	return phonenumber;
}

public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Date getDOB() {
	return DOB;
}

public void setDOB(Date dOB) {
	DOB = dOB;
}

public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public String getMiddlename() {
	return middlename;
}

public void setMiddlename(String middlename) {
	this.middlename = middlename;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

}
