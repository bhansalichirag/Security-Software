package main.java.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import main.java.dal.users.employees.Employee;
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


	@RequestMapping(value = "/banklogin", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response) {
		// System.out.println(model);
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("username");
		System.out.println(username+password);


		ModelAndView mav = null;
		User emp = null;
		try {
			emp = userService.validateEmpUser(username,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (emp!=null) {
			
			mav = new ModelAndView("open");
			mav.addObject("message", username);
		} else {
			System.out.println("Shachi");
			mav = new ModelAndView("redirect:emp_login");
			System.out.println("User Information not found.");
			mav.addObject("message", "Username or Password is wrong!!");
		}
		return mav;
	}




}