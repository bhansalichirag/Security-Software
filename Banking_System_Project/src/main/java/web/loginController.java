package main.java.web;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import main.java.dal.users.employees.Login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.business.services.IUserServices;
import main.java.dal.users.User;

@Controller
public class loginController {
   
	@Autowired
	  IUserServices userService;


	 @RequestMapping(value = "/emp_login", method = RequestMethod.GET)
	 public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		    ModelAndView mav = new ModelAndView("emp_login");
		    mav.addObject("emp_login", new Login());
		    mav.addObject("message",request.getParameter("message"));
	    return mav;
	    
	    
	  }
	 
 
	 @RequestMapping(value = "/Tier3Home", method = RequestMethod.POST)
	  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response) {
		// System.out.println(model);
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		System.out.println(username+" "+password);
	    ModelAndView mav = null;
	  if (username.equals("System") && password.contentEquals("pass")) {
		System.out.println("you are logged in as a tier 3 employee");
	    mav = new ModelAndView("Tier3Home");
	    mav.addObject("message", username);
	    } 
	  else {
	    	System.out.println("wrong username or password");
	    mav = new ModelAndView("redirect:emp_login");
	    mav.addObject("message", "Username or Password is wrong!!");
	    }
	    return mav;
	  }
}

