package main.java.business.services;

public interface IUserServices {

	public void createUser();
	
	public boolean validateUser(String username, String password);
	
}
