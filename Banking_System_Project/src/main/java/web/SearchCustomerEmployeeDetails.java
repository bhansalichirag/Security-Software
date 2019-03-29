package main.java.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Convert;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.business.services.IAccountServices;
import main.java.business.services.IUserServices;
import main.java.dal.accounts.Account;
import main.java.dal.users.User;
import main.java.dal.users.customers.Customer;
import main.java.dal.users.customers.Individual;
import main.java.dal.users.employees.Admin;
import main.java.dal.users.employees.Employee;
import main.java.dal.users.employees.Tier1;
import main.java.dal.users.employees.Tier2;
@Controller
public class SearchCustomerEmployeeDetails {

	@Autowired
	IUserServices userservices;
	
	@Autowired
	IAccountServices accountservices;

	@RequestMapping(value= "/Search", method = RequestMethod.GET)
	public ModelAndView Search(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		try {
			Employee emp = (Employee) session.getAttribute("EmployeeObject");
			if (emp == null)
			{
				return new ModelAndView("redirect:/login");
			}
			else if(emp instanceof Admin)
			{
			String role = (String)session.getAttribute("role");
			model.addAttribute("role",role);
			return new ModelAndView(("SearchUser"), model);
			}
			else
			{
				model.addAttribute("message","not authorized for this page");
				return new ModelAndView("Login",model);
			}
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}
	
	@RequestMapping(value= "/DeleteAccount", method = RequestMethod.GET)
	public ModelAndView DeleteAccount(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		try {
			Employee emp = (Employee) session.getAttribute("EmployeeObject");
			if (emp == null)
			{
				return new ModelAndView("redirect:/login");
			}
			else if(emp instanceof Tier2)
			{
			String role = (String)session.getAttribute("role");
			model.addAttribute("role",role);
			return new ModelAndView(("DeleteAccount"), model);
			}
			else
			{
				model.addAttribute("message","not authorized for this page");
				return new ModelAndView("Login",model);
			}
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}
	
	@RequestMapping(value= "/SearchAccount", method = RequestMethod.GET)
	public ModelAndView SearchAccount(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		try {
			Employee emp = (Employee) session.getAttribute("EmployeeObject");
			if (emp == null)
			{
				return new ModelAndView("redirect:/login");
			}
			else if(emp instanceof Tier1 || emp instanceof Tier2)
			{
			String role = (String)session.getAttribute("role");
			model.addAttribute("role",role);
			return new ModelAndView(("SearchAccount"), model);
			}
			else
			{
				model.addAttribute("message","not authorized for this page");
				return new ModelAndView("Login",model);
			}
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}
	

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView SearchUser(HttpServletRequest request, HttpSession session) throws ParseException {
		ModelMap model = new ModelMap();
		try {
			String username = request.getParameter("username").trim();
			User emp = (User)session.getAttribute("EmployeeObject"); 
			if (emp == null)
			{
				return new ModelAndView("Login");
			}
			if(username!="")
			{
				User user = (User)userservices.GetCustomerByUsername(username);
				if(user!=null)
				{
					if(user instanceof Employee && emp instanceof Admin)
					{
						List<User> personal = new ArrayList<User>();
						personal.add((User)user);
						model.addAttribute("personal", personal);
						return new ModelAndView(("SearchUser"), model);
					}
					else
					{
						model.addAttribute("message", "You are not authorized to see this users info");
						return new ModelAndView(("SearchUser"), model);
					}
				}
				else
				{
					model.addAttribute("message", "No such user exists currently");
					return new ModelAndView(("SearchUser"), model);
				}
			}
			else
			{
				model.addAttribute("message", "please enter a username");
				return new ModelAndView(("SearchUser"), model);
			}
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}
	
	
	@RequestMapping(value = "/searchaccount", method = RequestMethod.POST)
	public ModelAndView SearchAccountdetails(HttpServletRequest request, HttpSession session) throws ParseException {
		ModelMap model = new ModelMap();
		try {
			Integer accountnumber = new Integer(request.getParameter("accountnumber"));
			User emp = (User)session.getAttribute("EmployeeObject"); 
			if (emp == null)
			{
				return new ModelAndView("Login");
			}
			if(accountnumber.toString()!="")
			{
				Account account = (Account)accountservices.GetAccount(accountnumber);
				if(account!=null)
				{
					if(emp instanceof Tier1 || emp instanceof Tier2)
					{
						List<Account> accountinfo = new ArrayList<Account>();
						accountinfo.add((Account)account);
						model.addAttribute("accountinfo", accountinfo);
						return new ModelAndView(("SearchAccount"), model);
					}
					else
					{
						model.addAttribute("message", "You are not authorized to see this users info");
						return new ModelAndView(("SearchAccount"), model);
					}
				}
				else
				{
					model.addAttribute("message", "No such account exists currently");
					return new ModelAndView(("SearchAccount"), model);
				}
			}
			else
			{
				model.addAttribute("message", "please enter an account number");
				return new ModelAndView(("SearchAccount"), model);
			}
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}
	
	@RequestMapping(value = "/deleteaccount", method = RequestMethod.POST)
	public ModelAndView deleteAccountdetails(HttpServletRequest request, HttpSession session) throws ParseException {
		ModelMap model = new ModelMap();
		try {
			Integer accountnumber = new Integer(request.getParameter("accountnumber"));
			Employee emp = (Employee)session.getAttribute("EmployeeObject"); 
			if (emp == null)
			{
				return new ModelAndView("Login");
			}
			if(accountnumber.toString()!="")
			{
				Account account = (Account)accountservices.GetAccount(accountnumber);
				if(account!=null)
				{
					if(emp instanceof Tier2)
					{
						if(accountservices.DeclineAccount((Employee)emp,accountnumber)) {
							model.addAttribute("message", "Account deleted");
							return new ModelAndView(("DeleteAccount"), model);
						}
						else
						{
							model.addAttribute("message", "Account could not be deleted");
							return new ModelAndView(("DeleteAccount"), model);
						}
					}
					else
					{
						model.addAttribute("message", "You are not authorized to see this users info");
						return new ModelAndView(("DeleteAccount"), model);
					}
				}
				else
				{
					model.addAttribute("message", "No such account exists currently");
					return new ModelAndView(("DeleteAccount"), model);
				}
			}
			else
			{
				model.addAttribute("message", "please enter an account number");
				return new ModelAndView(("DeleteAccount"), model);
			}
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}
	
}
