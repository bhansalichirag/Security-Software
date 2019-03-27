package main.java.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import main.java.business.services.IAccountServices;
import main.java.business.services.IUserServices;
import main.java.business.services.OtpService;
import main.java.dal.Transaction;
import main.java.dal.accounts.Account;
import main.java.dal.accounts.CheckingAccount;
import main.java.dal.accounts.CreditCard;
import main.java.dal.accounts.SavingsAccount;
import main.java.dal.users.User;
import main.java.dal.users.customers.Customer;
import main.java.dal.users.employees.Admin;
import main.java.dal.users.employees.Tier1;
import main.java.dal.users.employees.Tier2;

@Controller
public class Controllers {

	@Autowired
	IUserServices userServices;
	@Autowired
	IAccountServices accountServices;
	@Autowired
	OtpService otpService;
    
	
	@RequestMapping(value="/setpassword",method = RequestMethod.POST)
	public ModelAndView Setpassword(HttpServletRequest request, HttpSession session){
		String userName = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		String confirmpassword = (String) request.getParameter("confirmpassword");
		ModelAndView mav = null;
		if(userServices.isNewUser(userName))
        {
			if(("").equals(password)){
				mav = new ModelAndView("redirect:/SetPassword");
			    mav.addObject("message", "password cannot be empty");
			}
			else if(!password.equals(confirmpassword)){
				mav = new ModelAndView("redirect:/SetPassword");
			    mav.addObject("message", "password and confirm password does not match");
			}
			else
			{
				if(userServices.updatePassword(userName, null, password))
				{
					mav = new ModelAndView("redirect:/login");
				}
				else
				{
					mav = new ModelAndView("redirect:/SetPassword");
				    mav.addObject("message", "password couldnt be updated!!");
				}
			}
        }
		return mav;
	}
	
	@RequestMapping(value="/changepassword", method = RequestMethod.POST)
    public ModelAndView Changethepassword(HttpServletRequest request, HttpSession session){
    	ModelAndView mav = new ModelAndView();
		String userName = (String) request.getParameter("username");
		String oldpassword = (String) request.getParameter("oldpassword");
		User user = userServices.ValidateUser(userName, oldpassword);
    	if (user == null)
    	{
    		return new ModelAndView("Login");
    	}
    	else 
    	{
    		String newpassword = (String) request.getParameter("newpassword");
    		String confirmpassword = (String) request.getParameter("confirmpassword");
    		if(!newpassword.equals(confirmpassword)){
				mav = new ModelAndView("redirect:/ChangePassword");
			    mav.addObject("message", "new password and confirm password does not match");
			}
			else
			{
				if(userServices.updatePassword(userName, oldpassword, newpassword))
				{
					mav = new ModelAndView("redirect:/login");
				}
				else
				{
					mav = new ModelAndView("redirect:/ChangePassword");
				    mav.addObject("message", "password couldnt be updated!!");
				}
			}
    	}
    	return mav;
	}
	
	@RequestMapping(value="/redirectuser", method = RequestMethod.POST)
    public ModelAndView sortUser(HttpServletRequest request, HttpSession session){
    	String userName = (String) request.getParameter("uname");
		String password = (String) request.getParameter("psw");
        if(userServices.isNewUser(userName))
        {
        	session.setAttribute("EmployeeUsername", userName);
        	return new ModelAndView("redirect:/SetPassword");
        }
        else
        {
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
        			session.setAttribute("EmployeeObject", (Admin)user);
        			return new ModelAndView("redirect:/AdminHome");
        		}
        		else if(user instanceof Tier1)
        		{
        			session.setAttribute("EmployeeObject", (Tier1)user);
        			return new ModelAndView("redirect:/Tier1Dash");
        		}
        		else
        		{
        			session.setAttribute("EmployeeObject", (Tier2)user);
        			return new ModelAndView("redirect:/Tier2Dash");
        		}
        	}
        }
        //return null;
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
        List<Account> accounts = customer.getAccountsList();
        for (Account account : accounts) 
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
        model.addAttribute("user", customer.getFirstName() + " " + customer.getLastName());
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
    	
    	Account account = user.getAccountsList()
    			.stream().filter(t -> t.getAccountNumber() == Integer.parseInt(request.getParameter("accountid")))
    			.findFirst().get();
    	List<Transaction> transactions = account.getTransactions();
    	model.addAttribute("transactions", transactions);
    	model.addAttribute("accountid", account.getAccountNumber());
    	if(account instanceof SavingsAccount)
    		model.addAttribute("accountType", "Savings Account");
    	else if(account instanceof CheckingAccount)
    		model.addAttribute("accountType", "Checking Account");
    	model.addAttribute("accountid", account.getAccountNumber());
    	model.addAttribute("balance", account.getBalance());
		return new ModelAndView(("accounts/Transactions"), model);
    }

	@RequestMapping(value= {",","/login"}, method = RequestMethod.GET)
    public String welcome(ModelMap model){
        String name = (String) model.get("name");
        model.put("Login", name);
        return "Login";
    }
	
	@RequestMapping(value= {"/ScheduleAppointment"}, method = RequestMethod.GET)
	public ModelAndView ScheduleAppointment(HttpServletRequest request, HttpSession session){
    	ModelMap model = new ModelMap();
        Customer user_cust = (Customer) session.getAttribute("CustomerObject");
        if (user_cust == null)
        {
        	return new ModelAndView("redirect:/login");
        }
        return new ModelAndView(("ScheduleAppointment"), model);
    }
	
	@RequestMapping(value= {"/OpenAccount"}, method = RequestMethod.GET)
	public ModelAndView OpenAccount(HttpServletRequest request, HttpSession session){
    	ModelMap model = new ModelMap();
        Customer user_cust = (Customer) session.getAttribute("CustomerObject");
        if (user_cust == null)
        {
        	return new ModelAndView("redirect:/login");
        }
        return new ModelAndView(("CreateNewAccount"), model);
    }
	
	@RequestMapping(value= {"/appointment"}, method = RequestMethod.POST)
    public ModelAndView BookAppointment(HttpServletRequest request, HttpSession session){
		
		ModelMap model = new ModelMap();
		Customer user_cust = (Customer) session.getAttribute("CustomerObject");
		String username = (String)user_cust.getUsername();
		String DOB = (String)request.getParameter("schedule_date").trim();
		String reason = (String) request.getParameter("appointment").trim();
		
		return new ModelAndView(("redirect:/ScheduleAppointment"), model);
    }
	
	@RequestMapping(value= {"/openaccount"}, method = RequestMethod.POST)
    public ModelAndView AccountOpening(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		Customer user_cust = (Customer) session.getAttribute("CustomerObject");
		String account_type = (String) request.getParameter("account_type");
		String dob = (String) request.getParameter("date_of_birth");
		String firstname = (String) request.getParameter("firstname");
		String lastname = (String)request.getParameter("lastname");
		String email = (String)request.getParameter("email");
		String secquestion1 = (String) request.getParameter("secquestion1");
		String secquestion2 = (String) request.getParameter("secquestion2");
		if(user_cust.getDateOfBirth().equals(dob) && user_cust.getEmail().equals(email) && user_cust.getFirstName().equals(firstname) 
				&& user_cust.getLastName().equals(lastname) && user_cust.getSeqQuestion().equals(secquestion1) 
				&& user_cust.getSeqQuestion2().equals(secquestion2))
		{
			Account account = accountServices.CreateAccount(user_cust, account_type);
			model.addAttribute("account", (Account)account);
			return new ModelAndView(("redirect:/accinfo"),model);
		}
		else
		{
			ModelAndView mav = new ModelAndView(("redirect:/OpenAccount"),model);
			mav.addObject("message","Wrong details entered");
			return mav;
		}
    }
	
	@RequestMapping(value= {"/SetPassword"}, method = RequestMethod.GET)
    public String SetupPassword(ModelMap model){
        String name = (String) model.get("name");
        model.put("SetPassword", name);
        return "SetPassword";
    }
	
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
	
	@RequestMapping(value = "/ForgotPassword", method = RequestMethod.GET)
	 public ModelAndView Register(HttpServletRequest request, HttpServletResponse response) {
		
		    ModelAndView mav = new ModelAndView("ForgotPassword");
		    mav.addObject("ForgotPassword");
		    mav.addObject("message",request.getParameter("message"));
	    
	    return mav;
	  }
	
	@RequestMapping(value="/forgotpassword", method=RequestMethod.POST)
	public ModelAndView ForgotPassword(HttpServletRequest request, HttpSession session){
		ModelAndView mav = new ModelAndView();
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		/*User user = userServices.UserFinder(username);
		if(user.getEmail()!= email)
		{
			mav = new ModelAndView("ForgotPassword");
			mav.addObject("message", "Email not matching");
		}
		else
		{
			//OTP part
		}*/
		session.setAttribute("username", username);
		//mav = new ModelAndView("ForgotPasswordOTP");//uncomment after otp is resolved
		mav = new ModelAndView("NewPassword");//comment this after otp is resolved
		return mav;
	}
	
	
	@RequestMapping(value="/newpassword", method=RequestMethod.POST)
	public ModelAndView NewPassword(HttpServletRequest request, HttpSession session){
		ModelAndView mav = new ModelAndView();
		String userName = (String)session.getAttribute("username");
		String newpassword = (String) request.getParameter("newpassword");
		String confirmpassword = (String) request.getParameter("confirmpassword");
		if(!newpassword.equals(confirmpassword)){
			mav = new ModelAndView("redirect:/ChangePassword");
		    mav.addObject("message", "new password and confirm password does not match");
		}
		else
		{
			if(userServices.updatePassword(userName, null, newpassword))
			{
				mav = new ModelAndView("redirect:/login");
			}
			else
			{
				mav = new ModelAndView("redirect:/ChangePassword");
			    mav.addObject("message", "password couldnt be updated!!");
			}
		}
		return mav;
	}
	
}