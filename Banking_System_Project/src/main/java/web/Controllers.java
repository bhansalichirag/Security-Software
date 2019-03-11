package main.java.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import main.java.business.services.IUserServices;
import main.java.dal.Transaction;
import main.java.dal.accounts.Account;
import main.java.dal.accounts.CheckingAccount;
import main.java.dal.accounts.CreditCard;
import main.java.dal.accounts.SavingsAccount;
import main.java.dal.users.User;
import main.java.dal.users.customers.Customer;

@Controller
public class Controllers {

	@Autowired
	IUserServices userServices;
    
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
    			.stream().filter(t -> t.getAccountNumber() == Integer.parseInt(request.getParameter("transactionid")))
    			.findFirst().get().getTransactions();
    	model.addAttribute("transactions", transactions);
		return new ModelAndView(("Transactions"), model);
    }

	@RequestMapping(value= {",","/login"}, method = RequestMethod.GET)
    public String welcome(ModelMap model){
        String name = (String) model.get("name");
        model.put("Login", name);
        return "Login";
    }
}