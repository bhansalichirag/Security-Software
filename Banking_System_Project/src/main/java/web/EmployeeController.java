package main.java.web;


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
		Admin Admin_emp = (Admin) session.getAttribute("EmployeeObject");
		if (Admin_emp == null)
		{
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView(("Tier3Home"), model);
	}

	@RequestMapping(value= "/TierEmployeeDashboard", method = RequestMethod.GET)
	public ModelAndView TierEmployeeDashboard(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		Admin Tier_emp = (Admin) session.getAttribute("EmployeeObject");
		if (Tier_emp == null)
		{
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView(("Tier3Home"), model);
	}

	@RequestMapping(value= "/Tier2Dash", method = RequestMethod.GET)
	public ModelAndView Tier2EmployeeDash(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		Tier2 Tier_emp = (Tier2) session.getAttribute("EmployeeObject");
		if (Tier_emp == null)
		{
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView(("Tier2Dashboard"), model);
	}

	@RequestMapping(value= "/Tier1Dash", method = RequestMethod.GET)
	public ModelAndView Tier1EmployeeDash(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		Tier1 Tier_emp = (Tier1) session.getAttribute("EmployeeObject");
		if (Tier_emp == null)
		{
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView(("Tier1Dashboard"), model);
	}

}
