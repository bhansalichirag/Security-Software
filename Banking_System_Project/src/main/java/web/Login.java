package main.java.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.business.services.IUserServices;
import main.java.business.services.LoggingService;
import main.java.business.services.LoginUserService;
import main.java.dal.users.User;
import main.java.dal.users.customers.Customer;
import main.java.dal.users.customers.Individual;
import main.java.dal.users.employees.Admin;
import main.java.dal.users.employees.Employee;
import main.java.dal.users.employees.Tier1;
import main.java.dal.users.employees.Tier2;

@Controller
public class Login {

	@Autowired
	IUserServices userServices;
	@Autowired
	LoginUserService loginUserService;
	@Autowired
	LoggingService loggingService;
	
	@RequestMapping(value= {"/","/login"}, method = RequestMethod.GET)
    public String welcome(ModelMap model, HttpServletRequest request){
        String name = (String) model.get("name");
        model.put("Login", name);
        loggingService.log("Login Attempt from IP: " + request.getRemoteAddr() + " recieved.");
        return "Login";
    }
	
	@RequestMapping(value= {"/logout"}, method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, ModelMap model, HttpSession session){
		try {

	        loggingService.log("Logout Request from IP: " + request.getRemoteAddr() + " recieved.");
			if(session.getAttribute("CustomerObject") !=null  && (session.getAttribute("CustomerObject") instanceof Customer))
			{
				if(loginUserService.removeUserLoggedIn(((Customer)session.getAttribute("CustomerObject")).getUsername()))
				{
					String username = ((Customer)session.getAttribute("CustomerObject")).getUsername();
					session.setAttribute("CustomerObject", null);
					loggingService.log("Customer with username: " + username + " was logged out.");
				}
			}
			if(session.getAttribute("EmployeeObject") !=null  && (session.getAttribute("EmployeeObject") instanceof Employee))
			{
				if(loginUserService.removeUserLoggedIn(((Customer)session.getAttribute("EmployeeObject")).getUsername()))
				{
					String username = ((Customer)session.getAttribute("EmployeeObject")).getUsername();
					session.setAttribute("EmployeeObject", null);
					loggingService.log("Employee with username: " + username + " was logged out.");
				}
			}
		}
		catch(Exception e)
		{
			
		}
        return new ModelAndView("Login");
    }
	
	@RequestMapping(value="/redirectuser", method = RequestMethod.POST)
    public ModelAndView sortUser(HttpServletRequest request, HttpSession session){
    	try {
		String userName = (String) request.getParameter("uname");
		String password = (String) request.getParameter("psw");
        if(userServices.isNewUser(userName))
        {
        	session.setAttribute("EmployeeUsername", userName);
        	return new ModelAndView("redirect:/SetPassword");
        }
        else
        {
        	
        	User user = userServices.ValidateUser(userName, password);
        	if (user == null)
        	{
        		ModelAndView mav = new ModelAndView("Login");
        		
        		if(userServices.isUserEnabled(userName))
        		{
        			if(userServices.incrementFailedAttempts(userName))
            		{
            			mav.addObject("message", "Wrong Password entered!!");
            		}
        		}
        		else
        		{
        			mav.addObject("message", "Account is locked.Please contact the bank");
        		}
        		loggingService.log("Login Attempt from IP: " + request.getRemoteAddr() + " failed.");
        		return mav;
        	}
        	else 
        	{
        		Object oldUser = session.getAttribute("CustomerObject");
        		if(oldUser != null && oldUser instanceof Customer)
        		{
        			loginUserService.removeUserLoggedIn(((Customer)oldUser).getUsername());
        		}
        		else
        		{
        			oldUser = session.getAttribute("EmployeeObject");
        			if(oldUser != null && oldUser instanceof Employee)
            		{
            			loginUserService.removeUserLoggedIn(((Customer)oldUser).getUsername());
            		}
        		}
        		if(!loginUserService.addUserLoggedIn(user.getUsername()))
        		{
        			ModelAndView mav = new ModelAndView("Login");
        			mav.addObject("message", "User is already logged In");
        			return mav;
        		}
        		if(userServices.isUserEnabled(userName) && userServices.resetFailedAttempts(userName))
        		{
        		if(user instanceof Customer)
        		{
        			if(user instanceof Individual)
        			{
        				session.setAttribute("role", "Individual");
                		loggingService.log("Customer of type Individual with username: " + user.getUsername() + " logged In.");
        			}
        			else
        			{
        				session.setAttribute("role", "Merchant");
                		loggingService.log("Customer of type Merchant with username: " + user.getUsername() + " logged In.");
        			}
        			session.setAttribute("CustomerObject", (Customer)user);
        			return new ModelAndView("redirect:/accinfo");
        		}
        		else if(user instanceof Admin)
        		{
        			session.setAttribute("EmployeeObject", (Admin)user);
        			session.setAttribute("role", "Admin");
            		loggingService.log("Employee of privilege Admin with username: " + user.getUsername() + " logged In.");
        			return new ModelAndView("redirect:/AdminHome");
        		}
        		else if(user instanceof Tier1)
        		{
        			session.setAttribute("EmployeeObject", (Tier1)user);
        			session.setAttribute("role", "Tier1");
            		loggingService.log("Employee of privilege Tier1 with username: " + user.getUsername() + " logged In.");
        			return new ModelAndView("redirect:/Tier1Dash");
        		}
        		else
        		{
        			session.setAttribute("EmployeeObject", (Tier2)user);
        			session.setAttribute("role", "Tier2");
            		loggingService.log("Employee of privilege Tier2 with username: " + user.getUsername() + " logged In.");
        			return new ModelAndView("redirect:/Tier2Dash");
        		}
        		}
        		else
        		{
        			ModelAndView mav=new ModelAndView("Login");
        			mav.addObject("message", "Account is locked.Please contact the bank");
        			return mav;
        		}
        	}
        }
    	}
    	catch(Exception ex)
        {
        	return new ModelAndView("Login");
        }//return null;
    }
	
}
