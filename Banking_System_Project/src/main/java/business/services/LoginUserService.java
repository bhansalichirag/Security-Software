package main.java.business.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class LoginUserService {

	private Set<String> loggedInUserNames = new HashSet<String>();
	
	
	public boolean addUserLoggedIn(String Username)
	{
		if(Username != null && !"".equals(Username) && !loggedInUserNames.contains(Username))
		{
			loggedInUserNames.add(Username);
			return true;
		}
		return false;
	}
	
	public boolean removeUserLoggedIn(String Username)
	{
		if(Username != null && !"".equals(Username) && loggedInUserNames.contains(Username))
		{
			loggedInUserNames.remove(Username);
			return true;
		}
		return false;
	}
	
	public void clearAll()
	{
		loggedInUserNames.clear();
	}
	
}
