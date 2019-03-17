package main.java.web;

import java.text.ParseException;
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
	
	@RequestMapping(value = "jsp/RegistrationExternal", method = RequestMethod.GET)
	 public ModelAndView Register(HttpServletRequest request, HttpServletResponse response) {
		
		    ModelAndView mav = new ModelAndView("RegistrationExternal");
		    mav.addObject("RegistrationExternal", new Registration());
		    mav.addObject("message",request.getParameter("message"));
	    
	    return mav;
	  }


	@RequestMapping(value = "/externalregister", method = RequestMethod.POST)
	  public ModelAndView externalregister(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		String firstname = (String) request.getParameter("firstname");
		String middlename = (String) request.getParameter("middlename");
		String lastname = (String) request.getParameter("lastname");
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		String confirmpassword = (String) request.getParameter("confirmpassword");
		String phonenumber = (String) request.getParameter("phonenumber");
		String email = (String) request.getParameter("email");
		String access = (String)request.getParameter("designation");	
		String DOB = (String)request.getParameter("date_of_birth");
		String address = (String)request.getParameter("address");
		String SSN = (String)request.getParameter("ssn");
		
		ModelAndView mav = null;
		
		if(("").equals(firstname)) {
			mav = new ModelAndView("redirect:jsp/RegistrationExternal.jsp");
		    mav.addObject("message", "first name cannot be empty");
		}
		else if(("").equals(lastname)){
			mav = new ModelAndView("redirect:jsp/RegistrationExternal.jsp");
		    mav.addObject("message", "last name cannot be empty");
		}
		else if(("").equals(middlename)){
			mav = new ModelAndView("redirect:jsp/RegistrationExternal.jsp");
		    mav.addObject("message", "middle name cannot be empty");
		}
		else if(("").equals(username)){
			mav = new ModelAndView("redirect:jsp/RegistrationExternal.jsp");
		    mav.addObject("message", "user name cannot be empty");
		}
		else if(("").equals(password)){
			mav = new ModelAndView("redirect:jsp/RegistrationExternal.jsp");
		    mav.addObject("message", "password cannot be empty");
		}
		else if(!password.equals(confirmpassword)){
			mav = new ModelAndView("redirect:jsp/RegistrationExternal.jsp");
		    mav.addObject("message", "password and confirm password does not match");
		}
		else if(("").equals(DOB)){
			mav = new ModelAndView("redirect:jsp/RegistrationExternal.jsp");
		    mav.addObject("message", "date cannot be empty");
		}
		else if(access == null){
			mav = new ModelAndView("redirect:jsp/RegistrationExternal.jsp");
		    mav.addObject("message", "specify the access granted to the employee");
		}
		else if(("").equals(phonenumber)){
			mav = new ModelAndView("redirect:jsp/RegistrationExternal.jsp");
		    mav.addObject("message", "phone number cannot be empty");
		}
		else if(("").equals(email)){
			mav = new ModelAndView("redirect:jsp/RegistrationExternal.jsp");
		    mav.addObject("message", "email cannot be empty");
		}
		else {
			//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd",Locale.US);
			Date date1 = new Date();
			Boolean user = userServices.CreateCustomerUser(access, firstname, middlename, lastname, username,date1, password, phonenumber, email, address,SSN, "abc","def");
			if (user)
			{
				mav =  new ModelAndView("redirect:jsp/Login.jsp");
				mav.addObject("message","Use your username and password to login");
			}
			else
			{
				mav = new ModelAndView("redirect:jsp/RegsitrationExternal.jsp");
				mav.addObject("message",user+" Try again!!");
			}
		}
	    return mav;
	  }
	
}
