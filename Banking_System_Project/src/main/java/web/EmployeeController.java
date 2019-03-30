package main.java.web;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.business.services.IAccountServices;
import main.java.business.services.ITransactionServices;
import main.java.dal.Transaction;
import main.java.business.services.IUserServices;
import main.java.business.services.UserServiesImpl;
import main.java.dal.accounts.Account;
import main.java.dal.accounts.CheckingAccount;
import main.java.dal.accounts.CreditCard;
import main.java.dal.accounts.SavingsAccount;
import main.java.dal.users.User;
import main.java.dal.users.customers.Customer;
import main.java.dal.users.employees.Admin;
import main.java.dal.users.employees.Employee;
import main.java.dal.users.employees.Tier1;
import main.java.dal.users.employees.Tier2;
import main.java.repositories.UserRepository;

@Controller
public class EmployeeController {

	@Autowired
	IUserServices userServices;

	@Autowired
	ITransactionServices transactionServices;


	@Autowired
	IAccountServices accountServices;

	@RequestMapping(value="/AdminHome", method = RequestMethod.GET)
	public ModelAndView AdminHome(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		try {
			Admin Admin_emp = (Admin) session.getAttribute("EmployeeObject");
			if (Admin_emp == null)
			{
				return new ModelAndView("redirect:/login");
			}
			return new ModelAndView(("Tier3Home"), model);
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}
	

	@RequestMapping(value="/signinhistory", method = RequestMethod.GET)
	public ModelAndView signinhistory(HttpServletRequest request, HttpSession session) {
		ModelMap model = new ModelMap();
		try{
			Employee emp = (Employee) session.getAttribute("EmployeeObject");
			if (emp instanceof Admin)
			{
				List<String> logLines = new ArrayList<String>();
				File file = ResourceUtils.getFile("classpath:logger.log");
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = reader.readLine();
				while (line != null) {
					logLines.add(line);
					line = reader.readLine();
				}
				reader.close();
				model.addAttribute("loglines",logLines);
				return new ModelAndView("ServiceRequests/LogHistory", model);
			}
			else
			{
				ModelAndView mav = new ModelAndView("redirect:/login");	
				mav.addObject("message","not authorized");
				return mav;
			}
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}

	
	@RequestMapping(value= "/TierEmployeeDashboard", method = RequestMethod.GET)
	public ModelAndView TierEmployeeDashboard(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		try {
			Admin Tier_emp = (Admin) session.getAttribute("EmployeeObject");
			if (Tier_emp == null)
			{
				return new ModelAndView("redirect:/login");
			}
			return new ModelAndView(("Tier3Home"), model);
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}

	@RequestMapping(value= "/Tier2Dash", method = RequestMethod.GET)
	public ModelAndView Tier2EmployeeDash(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		try {
			Tier2 Tier_emp = (Tier2) session.getAttribute("EmployeeObject");
			if (Tier_emp == null)
			{
				return new ModelAndView("redirect:/login");
			}
			return new ModelAndView(("Tier2Dashboard"), model);
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}

	@RequestMapping(value= "/Tier1Dash", method = RequestMethod.GET)
	public ModelAndView Tier1EmployeeDash(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		try {
			Tier1 Tier_emp = (Tier1) session.getAttribute("EmployeeObject");
			if (Tier_emp == null)
			{
				return new ModelAndView("redirect:/login");
			}
			return new ModelAndView(("Tier1Dashboard"), model);
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}

}
