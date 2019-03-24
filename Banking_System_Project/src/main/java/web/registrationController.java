package main.java.web;

import java.util.Date;
import java.util.Formatter;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import main.java.dal.users.employees.Register;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.boolex.Matcher;
import main.java.business.services.IUserServices;
import main.java.dal.users.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@Controller
public class registrationController {
   
	
	 @RequestMapping(value = "/emp_register", method = RequestMethod.GET)
	 public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		 	System.out.println("helloooo");
		    ModelAndView mav = new ModelAndView("emp_register");
		    mav.addObject("emp_register", new Register());
		    mav.addObject("message",request.getParameter("message"));
	    
	    return mav;
	  }
	 
 
	 @RequestMapping(value = "/index2", method = RequestMethod.POST)
	  public ModelAndView registerProcess(HttpServletRequest request, HttpServletResponse response) {
		String firstname = (String) request.getParameter("firstname");
		String middlename = (String) request.getParameter("middlename");
		String lastname = (String) request.getParameter("lastname");
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		String confirmpassword = (String) request.getParameter("confirmpassword");
		String gender = (String)request.getParameter("gender");
		String phonenumber = (String)request.getParameter("phonenumber");
		String email = (String) request.getParameter("email");
		String access = (String)request.getParameter("access");	
		String DOB = (String)request.getParameter("DOB");
		
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		format.setLenient(false);

		
		Pattern pat = Pattern.compile(emailRegex); 
		
		boolean flag = false;
		ModelAndView mav = null;
		System.out.println("hello");
		System.out.println(access);
		
		if(firstname.equals("")) {
			mav = new ModelAndView("redirect:emp_register");
		    mav.addObject("message", "first name cannot be empty");
		}
		
		  else if(lastname.equals("")){ mav = new
		  ModelAndView("redirect:emp_register"); mav.addObject("message",
		  "last name cannot be empty"); } 
		  else if(username.equals("")){ mav = new
		  ModelAndView("redirect:emp_register"); mav.addObject("message",
		  "user name cannot be empty"); } 
		 
		  else if(password.length()<8 || password.length()>16) {
			  mav = new ModelAndView("redirect:emp_register");
			  mav.addObject("message","password length out of range");
		  }
		  else if(password.length()>=8 && password.length()<=16){
			  final Pattern hasUppercase = Pattern.compile("[A-Z]");
			  final Pattern hasLowercase = Pattern.compile("[a-z]");
			  final Pattern hasNumber = Pattern.compile("\\d");
			  final Pattern hasSpecialChar = Pattern.compile("[^a-zA-Z0-9 ]");
			  int f = 0;
			  if (!hasUppercase.matcher(password).find()) {
		           f =1;
		        }
			  if (!hasLowercase.matcher(password).find()) {
		            f=1;
		        }

		        if (!hasNumber.matcher(password).find()) {
		            f=1;
		        }
		        if (hasSpecialChar.matcher(password).find()) {
		           f=1;
		        }
              if(f ==1) {
            	  mav = new ModelAndView("redirect:emp_register");
    			  mav.addObject("message", "see password requirements"); 
              }
		  }
		  else if(!confirmpassword.equals(password)){
		  mav = new ModelAndView("redirect:emp_register"); mav.addObject("message",
		  "password and confirm password does not match"); } 
		
		  else if(DOB.equals("")){
			  mav = new ModelAndView("redirect:emp_register");
			  mav.addObject("message", "date cannot be empty"); 
		 } 
		
		 /*else if(!DOB.equals("")) {
			  try {
		            format.parse(DOB);
		        } catch (ParseException e) {
		        	 mav = new ModelAndView("redirect:emp_register");
					  mav.addObject("message", "Please check the format of the date entered"); 
		        }
		  }*/
		
		  else if(access == null){ 
			  mav = new ModelAndView("redirect:emp_register");
			  mav.addObject("message","specify the access granted to the employee");
		  }
		 
		else if(phonenumber == null || phonenumber.length()!=10 ){
			mav = new ModelAndView("redirect:emp_register");
		    mav.addObject("message", "please enter a valid phone number");
		}
		
		  /*else if( phonenumber.length() == 10) { 
		   boolean f = true; 
		   for(int i =0;i<phonenumber.length(); i++) { 
		   char a = phonenumber.charAt(i); 
		   flag = Character.isLetter(a); 
		   if(flag) { 
		   f = false; 
		   } 
		   } 
		   if(!f) { 
		   mav = new ModelAndView("redirect:emp_register");
		   mav.addObject("message","please enter a valid phone number"); 
		    }
		    }*/
		 
		else if(email.equals("")){
			mav = new ModelAndView("redirect:emp_register");
		    mav.addObject("message", "email cannot be empty");
		}
		else if(!pat.matcher(email).matches()) {
			mav = new ModelAndView("redirect:emp_register");
		    mav.addObject("message", "Add a valid email");
		}
		
		else {
	     mav =  new ModelAndView("Tier3Home");
		}
	    return mav;
	  }
	 
	 
	 

}