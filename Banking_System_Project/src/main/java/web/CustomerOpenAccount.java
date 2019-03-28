package main.java.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.business.services.IAccountServices;
import main.java.dal.accounts.Account;
import main.java.dal.users.customers.Customer;
@Controller
public class CustomerOpenAccount {

	@Autowired
	IAccountServices accountServices;

	@RequestMapping(value= {"/OpenAccount"}, method = RequestMethod.GET)
	public ModelAndView OpenAccount(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		try {
			Customer user_cust = (Customer) session.getAttribute("CustomerObject");
			if (user_cust == null)
			{
				return new ModelAndView("redirect:/login");
			}
			String role = (String) session.getAttribute("role");
			model.addAttribute("role",role);
			return new ModelAndView(("CreateNewAccount"), model);
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}

	@RequestMapping(value= {"/openaccount"}, method = RequestMethod.POST)
	public ModelAndView AccountOpening(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		try{Customer user_cust = (Customer) session.getAttribute("CustomerObject");
		String account_type = (String) request.getParameter("account_type");
		String dob = (String) request.getParameter("date_of_birth");
		String firstname = (String) request.getParameter("firstname");
		String lastname = (String)request.getParameter("lastname");
		String email = (String)request.getParameter("email");
		String secquestion1 = (String) request.getParameter("secquestion1");
		String secquestion2 = (String) request.getParameter("secquestion2");
		if(user_cust.getDateOfBirth().equals(dob) && user_cust.getEmail().equals(email) && user_cust.getFirstName().equals(firstname) 
				&& user_cust.getLastName().equals(lastname) && user_cust.getSeqQuestion().equals(secquestion1) 
				&& user_cust.getSeqQuestion2().equals(secquestion2))
		{
			Account account = accountServices.CreateAccount(user_cust, account_type);
			model.addAttribute("account", (Account)account);
			return new ModelAndView(("redirect:/accinfo"),model);
		}
		else
		{
			ModelAndView mav = new ModelAndView(("redirect:/OpenAccount"),model);
			mav.addObject("message","Wrong details entered");
			return mav;
		}
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}

}
