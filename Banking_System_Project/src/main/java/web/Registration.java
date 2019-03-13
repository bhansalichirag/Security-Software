package main.java.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Registration {
	
	@RequestMapping(value = "jsp/RegistrationExternal", method = RequestMethod.GET)
	 public ModelAndView Register(HttpServletRequest request, HttpServletResponse response) {
		
		    ModelAndView mav = new ModelAndView("RegistrationExternal");
		    mav.addObject("RegistrationExternal", new Registration());
		    mav.addObject("message",request.getParameter("message"));
	    
	    return mav;
	  }


	@RequestMapping(value = "/externalregister", method = RequestMethod.POST)
	  public ModelAndView externalregister(HttpServletRequest request, HttpServletResponse response) {
		String firstname = (String) request.getParameter("firstname");
		String middlename = (String) request.getParameter("middlename");
		String lastname = (String) request.getParameter("lastname");
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		String confirmpassword = (String) request.getParameter("confirmpassword");
		String phonenumber = (String) request.getParameter("phonenumber");
		String email = (String) request.getParameter("email");
		String access = (String)request.getParameter("designation");	
		String DOB = (String)request.getParameter("DOB");
		
		
		int flag =0;
		ModelAndView mav = null;
		System.out.println("hello");
		System.out.println(firstname);
		
		if(("").equals(firstname)) {
			mav = new ModelAndView("redirect:jsp/Registration");
		    mav.addObject("message", "first name cannot be empty");
		}
		else if(("").equals(lastname)){
			mav = new ModelAndView("redirect:jsp/Registration");
		    mav.addObject("message", "last name cannot be empty");
		}
		else if(("").equals(middlename)){
			mav = new ModelAndView("redirect:jsp/Registration");
		    mav.addObject("message", "middle name cannot be empty");
		}
		else if(("").equals(username)){
			mav = new ModelAndView("redirect:jsp/Registration");
		    mav.addObject("message", "user name cannot be empty");
		}
		else if(("").equals(password)){
			mav = new ModelAndView("redirect:jsp/Registration");
		    mav.addObject("message", "password cannot be empty");
		}
		else if(!password.equals(confirmpassword)){
			mav = new ModelAndView("redirect:jsp/Registration");
		    mav.addObject("message", "password and confirm password does not match");
		}
		else if(("").equals(DOB)){
			mav = new ModelAndView("redirect:jsp/Registration");
		    mav.addObject("message", "date cannot be empty");
		}
		else if(access == null){
			mav = new ModelAndView("redirect:jsp/Registration");
		    mav.addObject("message", "specify the access granted to the employee");
		}
		else if(("").equals(phonenumber)){
			mav = new ModelAndView("redirect:jsp/Registration");
		    mav.addObject("message", "phone number cannot be empty");
		}
		else if(("").equals(email)){
			mav = new ModelAndView("redirect:jsp/Registration");
		    mav.addObject("message", "email cannot be empty");
		}
		else {
	     mav =  new ModelAndView("redirect:jsp/Login.jsp");
		}
	    return mav;
	  }
	
}
