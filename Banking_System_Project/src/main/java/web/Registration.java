package main.java.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.business.services.IUserServices;

@Controller
public class Registration {
	
	@Autowired
	IUserServices userServices;
	
	@RequestMapping(value = "/NewCustomerRegister", method = RequestMethod.GET)
	 public ModelAndView Register(HttpServletRequest request, HttpServletResponse response) {
		
		    ModelAndView mav = new ModelAndView("RegistrationExternal");
		    mav.addObject("RegistrationExternal", new Registration());
		    mav.addObject("message",request.getParameter("message"));
	    
	    return mav;
	  }


	@SuppressWarnings("finally")
	@RequestMapping(value = "/externalregister", method = RequestMethod.POST)
	  public ModelAndView externalregister(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		ModelAndView mav = null;
		try
		{
		String firstname = (String) request.getParameter("firstname").trim();
		String middlename = (String) request.getParameter("middlename").trim();
		String lastname = (String) request.getParameter("lastname").trim();
		String username = (String) request.getParameter("username").trim();
		String password = (String) request.getParameter("password").trim();
		String confirmpassword = (String) request.getParameter("confirmpassword").trim();
		String phonenumber = (String) request.getParameter("phone");
		String email = (String) request.getParameter("email").trim();
		String access = (String)request.getParameter("designation").trim();	
		String DOB = (String)request.getParameter("date_of_birth").trim();
		String address = (String)request.getParameter("address").trim();
		String SSN = (String)request.getParameter("ssn");
		String SecQuestion1 = (String)request.getParameter("secquestion1").trim();
		String SecQuestion2 = (String)request.getParameter("secquestion2").trim();
		
		
		if(("").equals(firstname)) {
			mav = new ModelAndView("RegistrationExternal");
		    mav.addObject("message", "first name cannot be empty");
		}
		else if(("").equals(lastname)){
			mav = new ModelAndView("RegistrationExternal");
		    mav.addObject("message", "last name cannot be empty");
		}
		else if(("").equals(username)){
			mav = new ModelAndView("RegistrationExternal");
		    mav.addObject("message", "user name cannot be empty");
		}
		else if(("").equals(password)){
			mav = new ModelAndView("RegistrationExternal");
		    mav.addObject("message", "password cannot be empty");
		}
		else if(!password.equals(confirmpassword)){
			mav = new ModelAndView("RegistrationExternal");
		    mav.addObject("message", "password and confirm password does not match");
		}
		else if(("").equals(DOB)){
			mav = new ModelAndView("RegistrationExternal");
		    mav.addObject("message", "date cannot be empty");
		}
		else if(access == null){
			mav = new ModelAndView("RegistrationExternal");
		    mav.addObject("message", "specify the access granted to the employee");
		}
		else if(("").equals(phonenumber)){
			mav = new ModelAndView("RegistrationExternal");
		    mav.addObject("message", "phone number cannot be empty");
		}
		else if(("").equals(email)){
			mav = new ModelAndView("RegistrationExternal");
		    mav.addObject("message", "email cannot be empty");
		}
		else if(("").equals(SecQuestion1)) {
			mav = new ModelAndView("RegistrationExternal");
		    mav.addObject("message", "Please enter value for Security Question 1");
		}
		else if(("").equals(SecQuestion2)) {
			mav = new ModelAndView("RegistrationExternal");
		    mav.addObject("message", "Please enter value for Security Question 2");
		}
		else {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
			Date date = (Date)formatter.parse(DOB);
			Boolean user = userServices.CreateCustomerUser(access, firstname, middlename, lastname, username,date, password, phonenumber, email, address,SSN, SecQuestion1,SecQuestion2);
			if (user)
			{
				mav =  new ModelAndView("Login");
				mav.addObject("message","Use your username and password to login");
			}
			else
			{
				mav = new ModelAndView("RegistrationExternal");
				mav.addObject("message","Username already exist!!");
			}
		}
		
	    
		}
		catch(Exception ex){
			mav = new ModelAndView();
			mav = new ModelAndView("RegistrationExternal");
			mav.addObject("message","Please try after sometime");
		}
		finally {
			return mav;
		}
	  }
}
	
