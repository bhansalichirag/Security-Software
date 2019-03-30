package main.java.web;

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
import main.java.dal.accounts.Account;
import main.java.dal.users.employees.Employee;
import main.java.dal.users.employees.Tier2;
@Controller
public class AuthorizeAccount {

	@Autowired
	IAccountServices accountServices;

	@RequestMapping(value="/accountrequest", method = RequestMethod.GET)
	public ModelAndView accountrequest(HttpServletRequest request, HttpSession session) {
		ModelMap model = new ModelMap();
		try {
			Employee emp = (Employee)session.getAttribute("EmployeeObject");
			if(emp==null)
			{
				return new ModelAndView("redirect:/login");
			}
			else if(emp instanceof Tier2)
			{
				String role = (String) session.getAttribute("role");
				List<Account> accounts=accountServices.getAllPendingAccounts().stream()
						.sorted((a,b) -> b.getAccountNumber().compareTo(a.getAccountNumber()))
						.collect(Collectors.toList());
				session.setAttribute("pendingAccounts", accounts);
				model.addAttribute("accounts", accounts);
				model.addAttribute("role",role);
				return new ModelAndView(("PendingAccounts"), model);
			}
			else
			{
				ModelAndView mav = new ModelAndView("Login");
				mav.addObject("message","not authorized for this task");
				return mav; 
			}
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}

	@RequestMapping(value="/approveaccount", method = RequestMethod.POST)
	public ModelAndView approveaccount(HttpServletRequest request, HttpSession session) {
		ModelMap model = new ModelMap();
		try {
			Employee approver =(Employee)session.getAttribute("EmployeeObject");
			if(approver==null)
			{
				return new ModelAndView("redirect:/login");
			}
			else if(approver instanceof Tier2)
			{
				List<Account> accounts=(List<Account>) session.getAttribute("pendingAccounts");//accountServices.getAllPendingAccounts();
				for(Account account:accounts ) {
					if(account.getAccountNumber()==Integer.parseInt(request.getParameter("accountID"))) {
						if(accountServices.ApproveAccount(approver, account.getAccountNumber())) {
							List<Account> freshaccounts=accountServices.getAllPendingAccounts();
							model.addAttribute("accounts", freshaccounts);
							model.addAttribute("message", "Account request approved");
						}
						else {
							List<Account> freshaccounts=accountServices.getAllPendingAccounts();
							model.addAttribute("accounts", freshaccounts);
							model.addAttribute("message", "Account request not processed");
						}
					}
				}
				return new ModelAndView("PendingAccounts",model);
			}
			else
			{
				ModelAndView mav = new ModelAndView("Login");
				mav.addObject("message","not authorized for this task");
				return mav; 
			}
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}

	@RequestMapping(value="/declineaccount", method = RequestMethod.POST)
	public ModelAndView declineaccount(HttpServletRequest request, HttpSession session) {
		ModelMap model = new ModelMap();
		try {
			Employee approver =(Employee)session.getAttribute("EmployeeObject");
			if(approver==null)
			{
				return new ModelAndView("redirect:/login");
			}
			else if(approver instanceof Tier2)
			{
				List<Account> accounts=(List<Account>) session.getAttribute("pendingAccounts");//accountServices.getAllPendingAccounts();
				for(Account account:accounts ) {
					if(account.getAccountNumber()==Integer.parseInt(request.getParameter("accountID"))) {
						if(accountServices.DeclineAccount(approver, account.getAccountNumber()))
						{
							List<Account> freshaccounts=accountServices.getAllPendingAccounts();
							model.addAttribute("accounts", freshaccounts);
							model.addAttribute("message", "Account request declined");
						}
						else
						{
							List<Account> freshaccounts=accountServices.getAllPendingAccounts();
							model.addAttribute("accounts", freshaccounts);
							model.addAttribute("message", "Account request couldnt be processed");
						}
					}
				}
				
				return new ModelAndView("PendingAccounts",model);
			}
			else
			{
				ModelAndView mav = new ModelAndView("Login");
				mav.addObject("message","not authorized for this task");
				return mav; 
			}
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}

}
