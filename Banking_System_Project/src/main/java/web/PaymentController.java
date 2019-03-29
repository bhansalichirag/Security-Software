package main.java.web;

import java.util.Optional;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.business.exceptions.AccountNotFoundException;
import main.java.business.exceptions.CustomerNotFoundException;
import main.java.business.exceptions.TransactionFailedException;
import main.java.business.services.IAccountServices;
import main.java.business.services.IUserServices;
import main.java.business.services.OtpService;
import main.java.dal.accounts.Account;
import main.java.dal.accounts.CheckingAccount;
import main.java.dal.accounts.CreditCard;
import main.java.dal.accounts.SavingsAccount;
import main.java.dal.users.User;
import main.java.dal.users.customers.Customer;
import main.java.dal.users.customers.Merchant;


@Controller
public class PaymentController{

	@Autowired
	IUserServices userServices;
	@Autowired
	IAccountServices accountServices;
	@Autowired
	OtpService otpService;
	
	private static final Logger LOG = Logger.getLogger(PaymentController.class.getName());
    
	@RequestMapping(value= {"/payments"}, method = RequestMethod.POST)
	public ModelAndView payments(HttpServletRequest request, HttpSession session){
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
		else if(account instanceof CreditCard)
		{
			model.addAttribute("acctype", "Credit Card");
		}
		return new ModelAndView(("accounts/Payments"), model);
	}
	
	@RequestMapping(value= {"/paymentactionacc"}, method = RequestMethod.POST)
    public ModelAndView paymentactionacc(HttpServletRequest request, HttpSession session) throws NumberFormatException, AccountNotFoundException, TransactionFailedException{
		
		ModelMap model = new ModelMap();
		String deposit = (String) request.getParameter("Deposit");
		String withdraw = (String) request.getParameter("Withdraw");
		String accountNumber = (String) request.getParameter("Recipient Account Number");
		String lastName = (String) request.getParameter("Recipient Last Name");
		String amount = (String) request.getParameter("Amount");
		int payer = Integer.parseInt(session.getAttribute("SelectedAccount").toString());
		boolean matches = false;
		double payAmount;
		try 
		{
			payAmount = Double.parseDouble(amount);
			Customer customer = (Customer) session.getAttribute("CustomerObject");
			matches = customer.getAccountsList().stream().distinct().anyMatch(e -> {
				if(e.getAccountNumber().equals(payer)
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
		if(matches && payAmount > 0)
		{
			if("sys".equals(deposit))
			{
				accountServices.MakePayment(1, payer, payAmount);
			}
			else if("sys".equals(withdraw))
			{
				accountServices.MakePayment(payer, 1, payAmount);
			}
			else if(userServices.AccountExistsAndBelongsToLastName(Integer.parseInt(accountNumber), lastName))
			{
				accountServices.MakePayment(payer, Integer.parseInt(accountNumber), payAmount);
			}
			else
			{
				throw new AccountNotFoundException();
			}
			model.addAttribute("accountid", session.getAttribute("SelectedAccount"));
		}

		else
		{
			return new ModelAndView("Login");
		}
		return new ModelAndView(("redirect:/accinfo"), model);
    }
	
	
	@RequestMapping(value= {"/paymentactionemph"}, method = RequestMethod.POST)
    public ModelAndView paymentactionemph(HttpServletRequest request, HttpSession session) throws TransactionFailedException, CustomerNotFoundException, AccountNotFoundException{
		
		ModelMap model = new ModelMap();
		String email = (String) request.getParameter("Recipient Email Address");
		String phone = (String) request.getParameter("Recipient Phone Number");
		double amount = Double.parseDouble(request.getParameter("Amount"));
		int payer = Integer.parseInt(session.getAttribute("SelectedAccount").toString());
		boolean matches = false;
		try 
		{
			Customer customer = (Customer) session.getAttribute("CustomerObject");
			matches = customer.getAccountsList().stream().distinct().anyMatch(e -> {
				if(e.getAccountNumber().equals(payer)
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

		if(amount > 0 && (matches && ((email != null && !"".equals(email)) || (phone != null && !"".equals(phone)))))
		{
			accountServices.MakePaymentToPrimary(payer, email, phone, amount);
			model.addAttribute("accountid", session.getAttribute("SelectedAccount"));
		}
		else
		{
			return new ModelAndView("Login");
		}
		return new ModelAndView(("redirect:/accinfo"), model);
    }
	
	@RequestMapping(value= {"/OpenPayments"}, method = RequestMethod.POST)
    public ModelAndView OpenPayments(HttpServletRequest request, HttpSession session){
	
		boolean matches;
		ModelMap model = new ModelMap();
		int account = Integer.parseInt(request.getParameter("accountid"));
		try 
		{
			Customer customer = (Customer) session.getAttribute("CustomerObject");
			session.setAttribute("SelectedAccount", account);
			matches = customer.getAccountsList().stream().distinct().anyMatch(e -> {
				if(e.getAccountNumber().equals(account)
						&& (e instanceof CreditCard))
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
			model.addAttribute("role", session.getAttribute("role"));
			return new ModelAndView("accounts/CreditCardPayments",model);
		}
		return new ModelAndView("Login");
	}
	
	
	@RequestMapping(value= {"/paymentcc"}, method = RequestMethod.POST)
    public ModelAndView paymentcc(HttpServletRequest request, HttpSession session){
		
		ModelMap model = new ModelMap();
		String merchant = (String) request.getParameter("Account");
		String merchantID = (String) request.getParameter("MerchantID");
		String cvv = (String) request.getParameter("CVV");
		double amount = Double.parseDouble(request.getParameter("Amount"));
		int payerAccount = Integer.parseInt(session.getAttribute("SelectedAccount").toString());
		Optional<Account> creditcardWrapper;
		CreditCard merchantcard = null;
		CreditCard creditcard = null;
		try 
		{
			Customer customer = (Customer) session.getAttribute("CustomerObject");
			creditcardWrapper = customer.getAccountsList().stream().distinct().filter(e -> {
				if(e.getAccountNumber().equals(payerAccount)
						&& (e instanceof CreditCard) && ((CreditCard)e).getCvv() == Integer.parseInt(cvv))
					return true;
				else
					return false;
			}).findFirst();
			User MerchmerchantCustomer = userServices.GetCustomerByUsername(merchantID);
			
			if(creditcardWrapper.isPresent() && MerchmerchantCustomer != null && MerchmerchantCustomer instanceof Merchant )
			{
				Optional<Account> merchantWrapper = ((Merchant)MerchmerchantCustomer).getAccountsList().stream()
						.filter(t -> t.getAccountNumber()
								.equals(Integer.parseInt(merchant)))
						.findFirst();
				if(merchantWrapper.isPresent())
				{
					merchantcard = (CreditCard) merchantWrapper.get();
				}
				creditcard = (CreditCard) creditcardWrapper.get();
			}
			
		}
		catch (Exception e) 
		{
			return new ModelAndView("Login");
		}

		if(creditcardWrapper.isPresent() && creditcardWrapper.isPresent())
		{
			if(accountServices.MakePaymentToMerchant(creditcard, merchantcard, amount))
			{
				return new ModelAndView(("redirect:/accinfo"), model);
			}
		}
		return new ModelAndView(("Login"), model);
    }
	
	@RequestMapping(value= {"/takepaymentcc"}, method = RequestMethod.POST)
    public ModelAndView takepaymentcc(HttpServletRequest request, HttpSession session) throws AccountNotFoundException, TransactionFailedException{
		
		ModelMap model = new ModelMap();
		String account = (String) request.getParameter("Account");
		String cvv = (String) request.getParameter("CVV");
		double amount = Double.parseDouble(request.getParameter("Amount"));
		int payerAccount = Integer.parseInt(session.getAttribute("SelectedAccount").toString());
		CreditCard customerCard;
		int customercvv;
		CreditCard creditcard = null;
		try 
		{
			Customer customer = (Customer) session.getAttribute("CustomerObject");
			creditcard = (CreditCard) customer.getAccountsList().stream().distinct().filter(e -> {
				if(e.getAccountNumber().equals(payerAccount)
						&& (e instanceof CreditCard))
					return true;
				else
					return false;
			}).findFirst().get();
			customerCard = (CreditCard) accountServices.GetAccount(Integer.parseInt(account));
			customercvv = Integer.parseInt(cvv);
		}
		catch (Exception e) 
		{
			return new ModelAndView("Login");
		}

		if(creditcard !=null && customercvv < 1000 && customercvv > 100)
		{
			accountServices.TakePayment(customerCard.getAccountNumber(), customercvv, creditcard, amount);
		}
		else
		{
			return new ModelAndView("Login");
		}
		return new ModelAndView(("redirect:/accinfo"), model);
    }
	
}
