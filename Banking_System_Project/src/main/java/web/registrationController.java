package main.java.web;

import java.util.Date;
import java.util.Formatter;

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

import main.java.business.services.IUserServices;
import main.java.dal.users.User;

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
		String phonenumber = (String) request.getParameter("phonenumber");
		String email = (String) request.getParameter("email");
		String access = (String)request.getParameter("access");	
		String DOB = (String)request.getParameter("DOB");
		
		
		int flag =0;
		ModelAndView mav = null;
		System.out.println("hello");
		System.out.println(access);
		
		if(firstname.equals("")) {
			mav = new ModelAndView("redirect:emp_register");
		    mav.addObject("message", "first name cannot be empty");
		}
		else if(lastname.equals("")){
			mav = new ModelAndView("redirect:emp_register");
		    mav.addObject("message", "last name cannot be empty");
		}
		else if(username.equals("")){
			mav = new ModelAndView("redirect:emp_register");
		    mav.addObject("message", "user name cannot be empty");
		}
		else if(password.equals("")){
			mav = new ModelAndView("redirect:emp_register");
		    mav.addObject("message", "password cannot be empty");
		}
		else if(!confirmpassword.equals(password)){
			mav = new ModelAndView("redirect:emp_register");
		    mav.addObject("message", "password and confirm password does not match");
		}
		else if(DOB.equals("")){
			mav = new ModelAndView("redirect:emp_register");
		    mav.addObject("message", "date cannot be empty");
		}
		else if(access == null){
			mav = new ModelAndView("redirect:emp_register");
		    mav.addObject("message", "specify the access granted to the employee");
		}
		else if(phonenumber.equals("")){
			mav = new ModelAndView("redirect:emp_register");
		    mav.addObject("message", "phone number cannot be empty");
		}
		else if(email.equals("")){
			mav = new ModelAndView("redirect:emp_register");
		    mav.addObject("message", "email cannot be empty");
		}
		else {
	     mav =  new ModelAndView("Tier3Home");
		}
	    return mav;
	  }
	 
	 
	 

}