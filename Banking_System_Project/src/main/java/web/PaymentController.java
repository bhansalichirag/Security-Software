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
import main.java.business.services.IUserServices;
import main.java.business.services.OtpService;
import main.java.dal.accounts.CreditCard;
import main.java.dal.users.customers.Customer;


@Controller
public class PaymentController {

	@Autowired
	IUserServices userServices;
	@Autowired
	IAccountServices accountServices;
	@Autowired
	OtpService otpService;
    
	
	@RequestMapping(value= {"/paymentactionacc"}, method = RequestMethod.POST)
    public ModelAndView paymentactionacc(HttpServletRequest request, HttpSession session){
		
		ModelMap model = new ModelMap();
		String accountNumber = (String) request.getParameter("Recipient Account Number");
		String lastName = (String) request.getParameter("Recipient Last Name");
		String amount = (String) request.getParameter("Amount");
		int payer = Integer.parseInt(session.getAttribute("SelectedAccount").toString());
		boolean matches = false;
		try 
		{
			Customer customer = (Customer) session.getAttribute("CustomerObject");
			matches = customer.getAccountsList().stream().anyMatch(e -> {
				if(e.getAccountNumber() == payer 
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

		if(matches && userServices.AccountExistsAndBelongsToLastName(Integer.parseInt(accountNumber), lastName))
		{
			accountServices.MakePayment(payer, Integer.parseInt(accountNumber), Integer.parseInt(amount));
			model.addAttribute("accountid", session.getAttribute("SelectedAccount"));
		}
		else
		{
			return new ModelAndView("Login");
		}
		return new ModelAndView(("redirect:/transactions"), model);
    }
	
	
	@RequestMapping(value= {"/paymentactionemph"}, method = RequestMethod.POST)
    public ModelAndView paymentactionemph(HttpServletRequest request, HttpSession session){
		
		ModelMap model = new ModelMap();
		String email = (String) request.getParameter("Recipient Email Address");
		String phone = (String) request.getParameter("Recipient Phone Number");
		double amount = Double.parseDouble(request.getParameter("Amount"));
		int payer = Integer.parseInt(session.getAttribute("SelectedAccount").toString());
		boolean matches = false;
		try 
		{
			Customer customer = (Customer) session.getAttribute("CustomerObject");
			matches = customer.getAccountsList().stream().anyMatch(e -> {
				if(e.getAccountNumber() == payer 
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

		if(matches && ((email != null && !"".equals(email)) || (phone != null && !"".equals(phone))))
		{
			accountServices.MakePaymentToPrimary(payer, email, phone, amount);
			model.addAttribute("accountid", session.getAttribute("SelectedAccount"));
		}
		else
		{
			return new ModelAndView("Login");
		}
		return new ModelAndView(("redirect:/transactions"), model);
    }
	
}
