package main.java.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import main.java.business.services.OtpService;
import main.java.dal.Transaction;
import main.java.dal.accounts.Account;
import main.java.dal.accounts.CheckingAccount;
import main.java.dal.accounts.CreditCard;
import main.java.dal.accounts.SavingsAccount;
import main.java.dal.users.customers.Customer;

@Controller
public class Controllers {

	@Autowired
	IUserServices userServices;
	@Autowired
	IAccountServices accountServices;
	@Autowired
	OtpService otpService;


	@RequestMapping(value="/accinfo", method = RequestMethod.GET)
	public ModelAndView accinfo(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		Customer customer = (Customer) session.getAttribute("CustomerObject");
		if (customer == null)
		{
			return new ModelAndView("Login");
		}

		List<CheckingAccount> checking = new ArrayList<CheckingAccount>();
		List<SavingsAccount> savings = new ArrayList<SavingsAccount>();
		List<CreditCard> creditcards = new ArrayList<CreditCard>();
		List<Account> accounts = customer.getAccountsList().stream().distinct()
				.filter(e -> e.isApprovalStatus())
				.collect(Collectors.toList());
		for (Account account : accounts) 
		{
			if(account instanceof CheckingAccount)
			{
				checking.add((CheckingAccount) account);
			}
			else if (account instanceof SavingsAccount) 
			{
				savings.add((SavingsAccount) account);
			}
			else 
			{
				account.setInterest((-1.0) * account.getInterest());
				creditcards.add((CreditCard)account);
			}
		}
		String role = (String)session.getAttribute("role");
		model.addAttribute("user", customer.getFirstName() + " " + customer.getLastName());
		model.addAttribute("checking", checking);
		model.addAttribute("savings", savings);
		model.addAttribute("creditcards", creditcards);
		model.addAttribute("role",role);
		return new ModelAndView(("MainCustomerPage"), model);
	}

	@RequestMapping(value="/transactions", method = RequestMethod.POST)
	public ModelAndView transactions(HttpServletRequest request, HttpSession session) {
		ModelMap model = new ModelMap();
		Customer user = (Customer) session.getAttribute("CustomerObject");
		if(user == null)
		{
			return new ModelAndView("Login");
		}
		else
		{
			user = (Customer) userServices.GetCustomerByUsername(user.getUsername());
			if(user != null)
			{
				session.setAttribute("CustomerObject", user);
			}
		}

		Account account = user.getAccountsList()
				.stream().filter(t -> t.getAccountNumber().equals(Integer.parseInt(request.getParameter("accountid")))
						&& t.isApprovalStatus())
				.findFirst().get();
		List<Transaction> transactions = account.getTransactions().stream()
				.filter(t -> !(!t.isApprovalStatus() && t.getApprover() != null))
				.collect(Collectors.toList());
		model.addAttribute("transactions", transactions);
		model.addAttribute("accountid", account.getAccountNumber());
		if(account instanceof SavingsAccount)
			model.addAttribute("accountType", "Savings Account");
		else if(account instanceof CheckingAccount)
			model.addAttribute("accountType", "Checking Account");
		model.addAttribute("accountid", account.getAccountNumber());
		model.addAttribute("balance", account.getBalance());
		return new ModelAndView(("accounts/Transactions"), model);
	}
	
	@RequestMapping(value= {"/depositwithdrawal"}, method = RequestMethod.POST)
	public ModelAndView depositwithdrawal(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		Account account = null;
		try {
			Customer customer = (Customer) session.getAttribute("CustomerObject");
			account = customer.getAccountsList().stream()
					.filter(e -> e.getAccountNumber() == Integer.parseInt(request.getParameter("accountid"))).findFirst().get();

			model.addAttribute("balance", account.getBalance());
			model.addAttribute("accountid", account.getAccountNumber());
			session.setAttribute("SelectedAccount", account.getAccountNumber());
		}
		catch(Exception e)
		{
			return new ModelAndView("Login");
		}
		if(account instanceof SavingsAccount)
		{
			model.addAttribute("acctype", "Savings Account");
		}
		else if(account instanceof CheckingAccount)
		{
			model.addAttribute("acctype", "Checking Account");
		}
		return new ModelAndView(("accounts/DepositWithdrawal"), model);
	}
}