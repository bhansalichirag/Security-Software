package main.java.web;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.business.exceptions.CashierCheckNotFoundException;
import main.java.business.services.ICashiersCheckService;
import main.java.dal.accounts.Account;
import main.java.dal.accounts.CreditCard;
import main.java.dal.users.customers.Customer;
import main.java.dal.users.customers.Individual;


@Controller
public class ServiceRequestController {

	
	@Autowired
	ICashiersCheckService cashiersCheckService;
	
	@RequestMapping(value="/ServiceRequest", method=RequestMethod.GET)
	public String ServiceRequest(){

		return "ServiceRequests/ServiceRequests";
	}

	@RequestMapping(value="/OrderCCheck", method=RequestMethod.GET)
	public ModelAndView OrderCCheck(HttpServletRequest request, HttpSession session){

		ModelMap model = new ModelMap();
		try 
		{
			Individual customer = (Individual) session.getAttribute("CustomerObject");
			List<Integer> accounts = customer.getAccountsList().stream()
					.filter(t -> !(t instanceof CreditCard))
					.map(e -> e.getAccountNumber()).distinct()
					.collect(Collectors.toList());
			model.addAttribute("accounts", accounts);
		}
		catch (Exception e) 
		{
			return new ModelAndView("Login");
		}

		return new ModelAndView(("ServiceRequests/CashiersCheckOrder"), model);
	}

	@RequestMapping(value= {"/CCheckOrderAction"}, method = RequestMethod.POST)
	public ModelAndView ccheckOrderAction(HttpServletRequest request, HttpSession session){

		ModelMap model = new ModelMap();
		String firstName = (String) request.getParameter("Recipient's First Name");
		String middleName = null;
		if(!"".equals(request.getParameter("Recipient's Middle Name")))
			middleName = (String) request.getParameter("Recipient's Middle Name");
		String lastName = (String) request.getParameter("Recipient's Last Name");
		Integer account = Integer.parseInt(request.getParameter("Account"));
		double amount = Double.parseDouble(request.getParameter("Amount"));
		Optional<Account> matches;
		try 
		{
			Customer customer = (Customer) session.getAttribute("CustomerObject");
			matches = customer.getAccountsList().stream().distinct().filter(e -> {
				if(e.getAccountNumber().equals(account)
						&& !(e instanceof CreditCard))
					return true;
				else
					return false;
			}).findFirst();
		}
		catch (Exception e) 
		{
			return new ModelAndView("Login");
		}
		if(matches.isPresent() && amount > 0)
		{
			cashiersCheckService.OrderCashiersCheck(firstName, middleName, lastName, matches.get(), amount);
		}
		else
		{
			return new ModelAndView("Login");
		}
		
		return new ModelAndView(("redirect:/accinfo"), model);
	}
	
	@RequestMapping(value= {"/PrimeAccount"}, method = RequestMethod.GET)
	public ModelAndView PrimeAccount(HttpServletRequest request, HttpSession session){

		ModelMap model = new ModelMap();
		try 
		{
			Customer customer = (Customer) session.getAttribute("CustomerObject");
			List<Integer> accounts = customer.getAccountsList().stream().distinct().map(t -> t.getAccountNumber())
					.filter(t -> !t.equals(customer.getPrimaryAccount().getAccountNumber()))
					.collect(Collectors.toList());
			model.addAttribute("accounts", accounts);
			model.addAttribute("prime_account", customer.getPrimaryAccount().getAccountNumber());
		}
		catch (Exception e) 
		{
			return new ModelAndView("Login");
		}
		
		return new ModelAndView(("ServiceRequests/PrimaryAccount"), model);
	}
	
	@RequestMapping(value= {"/ccheckDepositAction"}, method = RequestMethod.POST)
	public ModelAndView ccheckDepositAction(HttpServletRequest request, HttpSession session) throws CashierCheckNotFoundException{

		ModelMap model = new ModelMap();
		String ccNumber = (String) request.getParameter("Cashier's Check Number");
		Integer account = Integer.parseInt(request.getParameter("Account"));
		Optional<Account> matches = null;
		try 
		{
			Customer customer = (Customer) session.getAttribute("CustomerObject");
			matches = customer.getAccountsList().stream().distinct().filter(e -> {
				if(e.getAccountNumber().equals(account)
						&& !(e instanceof CreditCard))
					return true;
				else
					return false;
			}).findFirst();
			
			if(matches.isPresent())
			{
				cashiersCheckService.DepositCashiersCheck(ccNumber, (Individual)customer, matches.get());
			}
			else
			{
				return new ModelAndView("Login");
			}
		}
		catch (CashierCheckNotFoundException cashierCheckNotFoundException) 
		{
			throw cashierCheckNotFoundException;
		}
		catch (Exception e) 
		{
			return new ModelAndView("Login");
		}
		
		
		return new ModelAndView(("redirect:/accinfo"), model);
	}

}
