package main.java.web;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import main.java.dal.accounts.CreditCard;
import main.java.dal.users.customers.Customer;
import main.java.dal.users.customers.Individual;


@Controller
public class ServiceRequestController {

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

	@RequestMapping(value= {"/ccheckOrderAction"}, method = RequestMethod.POST)
	public ModelAndView paymentactionacc(HttpServletRequest request, HttpSession session){

		ModelMap model = new ModelMap();
		String firstName = (String) request.getParameter("Recipient's First Name");
		String middleName = (String) request.getParameter("Recipient's Middle Name");
		String lastName = (String) request.getParameter("Recipient's Last Name");
		Integer account = Integer.parseInt(request.getParameter("Account"));
		double amount = Double.parseDouble(request.getParameter("Amount"));
		boolean matches = false;
		try 
		{
			Customer customer = (Customer) session.getAttribute("CustomerObject");
			matches = customer.getAccountsList().stream().anyMatch(e -> {
				if(e.getAccountNumber() == account
						&& !(e instanceof CreditCard))
					return true;
				else
					return false;
			});
		}
		catch (Exception e) 
		{
			return new ModelAndView("Login");
		}
		if(matches)
		{
			
		}
		

		return new ModelAndView(("redirect:/transactions"), model);
	}

}
