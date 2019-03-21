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

@Controller
public class Tier1Controller {

	@Autowired
	IUserServices userServices;
	@Autowired
	IAccountServices accountServices;
    
	@RequestMapping(value="/Tier1Dashboard", method = RequestMethod.GET)
    public ModelAndView tier1(HttpServletRequest request, HttpSession session){
		return new ModelAndView("Tier1Dashboard");
		
    }
	
	@RequestMapping(value="/Tier2Dashboard", method = RequestMethod.GET)
    public ModelAndView tier2(HttpServletRequest request, HttpSession session){
		return new ModelAndView("Tier2Dashboard");
		
    }
	
}