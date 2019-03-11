package main.java.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import main.java.business.services.IUserServices;
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
	
    @RequestMapping(value="/caccounts", method = RequestMethod.GET)
    public String caccounts(ModelMap model) {
        String name = (String) model.get("name");
        model.put("open", name);
        Customer user = userServices.GetCustomerAccountData("wwhite75");
        List<CheckingAccount> checking = new ArrayList<CheckingAccount>();
        List<SavingsAccount> savings = new ArrayList<SavingsAccount>();
        List<CreditCard> creditcards = new ArrayList<CreditCard>();
        for (Account account : user.getAccountsList()) 
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
        return "CustomerAccounts";
    }
    
    @RequestMapping(value="/transactions", method = RequestMethod.GET)
    public String transactions(ModelMap model) {
    	Customer user = userServices.GetCustomerAccountData("wwhite75");
    	List<Transaction> transactions = user.getAccountsList()
    			.stream().filter(t -> t.getAccountNumber() == 288)
    			.findFirst().get().getTransactions();
    	model.addAttribute("transactions", transactions);
		return "Transactions";
    	
    }
    
    @RequestMapping(value="/accinfo", method = RequestMethod.GET)
    public String accinfo(ModelMap model){
        String name = (String) model.get("name");
        model.put("open", name);
        Customer user = userServices.GetCustomerAccountData("wwhite75");
        List<CheckingAccount> checking = new ArrayList<CheckingAccount>();
        List<SavingsAccount> savings = new ArrayList<SavingsAccount>();
        List<CreditCard> creditcards = new ArrayList<CreditCard>();
        for (Account account : user.getAccountsList()) 
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
        return "MainCustomerPage";
    }
}