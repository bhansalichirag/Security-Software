package main.java.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import main.java.business.services.IAccountServices;
import main.java.business.services.IUserServices;
import main.java.dal.Transaction;
import main.java.dal.accounts.Account;
import main.java.dal.accounts.CheckingAccount;
import main.java.dal.accounts.CreditCard;
import main.java.dal.accounts.SavingsAccount;
import main.java.dal.users.User;
import main.java.dal.users.customers.Customer;
import main.java.dal.users.employees.Admin;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
//import *.service.OtpService;
import main.java.web.*;

@Controller
public class Controllers {

	@Autowired
	IUserServices userServices;
	@Autowired
	IAccountServices accountServices;
	@Autowired
	OtpService otpService;
    
    @RequestMapping(value="/redirectuser", method = RequestMethod.POST)
    public ModelAndView sortUser(HttpServletRequest request, HttpSession session){
    	String userName = (String) request.getParameter("uname");
		String password = (String) request.getParameter("psw");
        User user = userServices.ValidateUser(userName, password);
        if (user == null)
        {
        	return new ModelAndView("Login");
        }
        else 
        {
            if(user instanceof Customer)
            {
            	user.setPassword("");
                session.setAttribute("CustomerObject", (Customer)user);
                return new ModelAndView("redirect:/accinfo");
            }
            else if(user instanceof Admin)
            {
            	session.setAttribute("AdminObject", (Admin)user);
            	return new ModelAndView("redirect:/AdminHome");
            }
        }
		return null;
    }
    
    
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
        for (Account account : customer.getAccountsList()) 
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
        model.addAttribute("checking", checking);
        model.addAttribute("savings", savings);
        model.addAttribute("creditcards", creditcards);
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
    	List<Transaction> transactions = user.getAccountsList()
    			.stream().filter(t -> t.getAccountNumber() == Integer.parseInt(request.getParameter("accountid")))
    			.findFirst().get().getTransactions();
    	model.addAttribute("transactions", transactions);
    	model.addAttribute("accountid", request.getParameter("accountid"));
		return new ModelAndView(("Transactions"), model);
    }

	@RequestMapping(value= {",","/login"}, method = RequestMethod.GET)
    public String welcome(ModelMap model){
        String name = (String) model.get("name");
        model.put("Login", name);
        return "Login";
    }
	
	@RequestMapping(value= {"/payments"}, method = RequestMethod.POST)
    public ModelAndView payments(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		model.addAttribute("accountid", request.getParameter("accountid"));
		session.setAttribute("SelectedAccount", request.getParameter("accountid"));
		return new ModelAndView(("Payments"), model);
    }
	
	@RequestMapping(value= {"/paymentaction"}, method = RequestMethod.POST)
    public ModelAndView paymentaction(HttpServletRequest request, HttpSession session){
		
		ModelMap model = new ModelMap();
//		Customer customer = (Customer) session.getAttribute("CustomerObject");
//		String recipient = (String) request.getParameter("Recipient");
		String accountNumber = (String) request.getParameter("AccountNumber");
		String amount = (String) request.getParameter("Amount");
		int payer = Integer.parseInt(session.getAttribute("SelectedAccount").toString());
		
		accountServices.MakePayment(payer, Integer.parseInt(accountNumber), Integer.parseInt(amount));
		model.addAttribute("accountid", session.getAttribute("SelectedAccount"));
		return new ModelAndView(("redirect:/transactions"), model);
    }
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
    public @ResponseBody String logout(HttpServletRequest request, HttpServletResponse response){
       org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
       if (auth != null){    
       String username = auth.getName();
	//Remove the recently used OTP from server. 
       otpService.clearOTP(username);
       new SecurityContextLogoutHandler().logout(request, response, auth);
       }
   return "redirect:/login?logout";    
    }
	
	
	
}