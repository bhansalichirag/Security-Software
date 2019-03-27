package main.java.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.business.services.IAccountServices;
import main.java.dal.Transaction;
import main.java.dal.users.customers.Customer;
@Controller
public class CustomerTransactionPayment {

	@Autowired
	IAccountServices accountServices;
	
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
		String accountNumber = (String) request.getParameter("AccountNumber");
		String amount = (String) request.getParameter("Amount");
		int payer = Integer.parseInt(session.getAttribute("SelectedAccount").toString());
		
		accountServices.MakePayment(payer, Integer.parseInt(accountNumber), Integer.parseInt(amount));
		model.addAttribute("accountid", session.getAttribute("SelectedAccount"));
		return new ModelAndView(("redirect:/transactions"), model);
    }
	
}
