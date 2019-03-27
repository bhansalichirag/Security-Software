package main.java.web;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import main.java.business.services.ITransactionServices;
import main.java.dal.Transaction;
import main.java.business.services.IUserServices;
import main.java.business.services.UserServiesImpl;
import main.java.dal.accounts.Account;
import main.java.dal.accounts.CheckingAccount;
import main.java.dal.accounts.CreditCard;
import main.java.dal.accounts.SavingsAccount;
import main.java.dal.users.User;
import main.java.dal.users.customers.Customer;
import main.java.dal.users.employees.Admin;
import main.java.dal.users.employees.Employee;
import main.java.dal.users.employees.Tier1;
import main.java.dal.users.employees.Tier2;
import main.java.repositories.UserRepository;

@Controller
public class EmployeeController {

	@Autowired
	IUserServices userServices;

	@Autowired
	ITransactionServices transactionServices;

	@RequestMapping(value="/AdminHome", method = RequestMethod.GET)
	public ModelAndView AdminHome(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		Admin Admin_emp = (Admin) session.getAttribute("EmployeeObject");
		if (Admin_emp == null)
		{
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView(("Tier3Home"), model);
	}

	@RequestMapping(value= "/TierEmployeeDashboard", method = RequestMethod.GET)
	public ModelAndView TierEmployeeDashboard(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		Admin Tier_emp = (Admin) session.getAttribute("EmployeeObject");
		if (Tier_emp == null)
		{
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView(("Tier3Home"), model);
	}

	@RequestMapping(value= "/Tier2Dash", method = RequestMethod.GET)
	public ModelAndView Tier2EmployeeDash(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		Tier2 Tier_emp = (Tier2) session.getAttribute("EmployeeObject");
		if (Tier_emp == null)
		{
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView(("Tier2Dashboard"), model);
	}

	@RequestMapping(value= "/Tier1Dash", method = RequestMethod.GET)
	public ModelAndView Tier1EmployeeDash(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		Tier1 Tier_emp = (Tier1) session.getAttribute("EmployeeObject");
		if (Tier_emp == null)
		{
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView(("Tier1Dashboard"), model);
	}

	@RequestMapping(value= "/Search", method = RequestMethod.GET)
	public ModelAndView Search(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		Employee emp = (Employee) session.getAttribute("EmployeeObject");
		if (emp == null)
		{
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView(("SearchUser"), model);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView SearchUser(HttpServletRequest request, HttpSession session) throws ParseException {
		ModelMap model = new ModelMap();
		String username = request.getParameter("username");
		User emp = (User)session.getAttribute("EmployeeObject"); 
		if (emp == null)
		{
			return new ModelAndView("Login");
		}

		List<User> checking = new ArrayList<User>();
		checking.add((User)emp);
		model.addAttribute("personal", checking);
		return new ModelAndView(("SearchUser"), model);
	}

	@RequestMapping(value= "/ChangePassword", method = RequestMethod.GET)
	public ModelAndView ChangePasswordPath(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		Employee Tier_emp = (Employee) session.getAttribute("EmployeeObject");
		Customer cust_emp = (Customer) session.getAttribute("CustomerObject"); 
		if (Tier_emp == null && cust_emp==null)
		{
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView(("ChangePassword"), model);
	}

	@RequestMapping(value="/EmployeeRegister", method = RequestMethod.GET)
	public ModelAndView EmployeeRegister(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		Admin Admin_emp = (Admin) session.getAttribute("EmployeeObject");
		if (Admin_emp == null)
		{
			return new ModelAndView("redirect:/login");
		}
		/*if(request.getAttribute("alertMsg")==null)
        {
        	request.setAttribute("alertMsg", "");
        }*/
		return new ModelAndView(("EmployeeInsert"), model);
	}

	@RequestMapping(value="/EmployeeUpdate", method = RequestMethod.GET)
	public ModelAndView EmployeeUpdate(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		Employee emp = (Employee) session.getAttribute("EmployeeObject");
		if (emp == null)
		{
			return new ModelAndView("redirect:/login");
		}
		String abc = emp.getEmail();
		model.addAttribute("empusername",emp.getUsername());
		request.setAttribute("Email",abc);
		request.setAttribute("FirstName",emp.getFirstName());
		String temp_mid = emp.getMiddleName();
		if(temp_mid==null || temp_mid=="")
		{
			request.setAttribute("MiddleName", "");
		}
		else
		{
			request.setAttribute("MiddleName", temp_mid);
		}
		request.setAttribute("LastName", emp.getLastName());
		String temp_phone = emp.getPhoneNumber();
		if(temp_phone==null || temp_phone=="")
		{
			request.setAttribute("Phone", "");
		}
		else
		{
			request.setAttribute("Phone", emp.getPhoneNumber());
		}
		request.setAttribute("EmployeeObject",(Employee)emp);
		return new ModelAndView(("EmployeeUpdate"), model);
	}

	@RequestMapping(value="/EmployeeDelete", method = RequestMethod.GET)
	public ModelAndView EmployeeDelete(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		Admin Admin_emp = (Admin) session.getAttribute("EmployeeObject");
		if (Admin_emp == null)
		{
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView(("EmployeeDelete"), model);
	}

	@RequestMapping(value = "/emp_update", method = RequestMethod.POST)
	public ModelAndView EmployeeUpdateValues(HttpServletRequest request, HttpSession session) throws ParseException {
		Employee emp = (Employee) session.getAttribute("EmployeeObject");
		String username = emp.getUsername();
		String firstname = (String) request.getParameter("firstname");
		String middlename = (String) request.getParameter("middlename");
		String lastname = (String) request.getParameter("lastname");
		String phonenumber = (String) request.getParameter("phone");
		String email = (String) request.getParameter("email");
		String ssn = (String)request.getParameter("ssn");	
		String DOB = (String)request.getParameter("date_of_birth");
		String SeqQuestion1 = (String)request.getParameter("seqquestion1");
		String SeqQuestion2 = (String)request.getParameter("seqquestion2");
		ModelAndView mav =null;
		if(("").equals(firstname)) {
			mav = new ModelAndView("redirect:/EmployeeUpdate");
			mav.addObject("message", "first name cannot be empty");
		}
		else if(("").equals(lastname)){
			mav = new ModelAndView("redirect:/EmployeeUpdate");
			mav.addObject("message", "last name cannot be empty");
		}
		else if(("").equals(DOB)){
			mav = new ModelAndView("redirect:/EmployeeUpdate");
			mav.addObject("message", "date cannot be empty");
		}
		else if(("").equals(phonenumber)){
			mav = new ModelAndView("redirect:/EmployeeUpdate");
			mav.addObject("message", "phone number cannot be empty");
		}
		else if(("").equals(email)){
			mav = new ModelAndView("redirect:/EmployeeUpdate");
			mav.addObject("message", "email cannot be empty");
		}
		else if(("").equals(ssn)) {
			mav = new ModelAndView("redirect:/EmployeeUpdate");
			mav.addObject("message","ssn cannot be empty");
		}
		else if(("").equals(SeqQuestion1)) {
			mav = new ModelAndView("redirect:/EmployeeUpdate");
			mav.addObject("message","ssn cannot be empty");
		}
		else if(("").equals(SeqQuestion2)) {
			mav = new ModelAndView("redirect:/EmployeeUpdate");
			mav.addObject("message","ssn cannot be empty");
		}
		else {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
			Date date = (Date)formatter.parse(DOB);
			Boolean user = userServices.UpdateUser(firstname, middlename, lastname,username, date, "", phonenumber, email, "", ssn, SeqQuestion1, SeqQuestion2);
			if (user)
			{
				mav =  new ModelAndView("redirect:/EmployeeUpdate");
				mav.addObject("message","Employee created successfully!!");
			}
			else
			{
				mav = new ModelAndView("redirect:/EmployeeUpdate");
				mav.addObject("message","Some issue with insertion!!");
			}
		}
		return mav;
	}

	@RequestMapping(value = "/emp_insert", method = RequestMethod.POST)
	public ModelAndView EmployeeInsert(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		String firstname = (String) request.getParameter("firstname");
		String middlename = (String) request.getParameter("middlename");
		String lastname = (String) request.getParameter("lastname");
		String username = (String) request.getParameter("username");
		String phonenumber = (String) request.getParameter("phone");
		String email = (String) request.getParameter("email");
		String access = (String)request.getParameter("designation");	
		String DOB = (String)request.getParameter("date_of_birth");
		//String SSN = (String)request.getParameter("ssn");

		ModelAndView mav = null;

		if(("").equals(firstname)) {
			mav = new ModelAndView("redirect:/EmployeeRegister");
			mav.addObject("message", "first name cannot be empty");
		}
		else if(("").equals(lastname)){
			mav = new ModelAndView("redirect:/EmployeeRegister");
			mav.addObject("message", "last name cannot be empty");
		}
		else if(("").equals(username)){
			mav = new ModelAndView("redirect:/EmployeeRegister");
			mav.addObject("message", "user name cannot be empty");
		}
		else if(("").equals(DOB)){
			mav = new ModelAndView("redirect:/EmployeeRegister");
			mav.addObject("message", "date cannot be empty");
		}
		else if(access == null){
			mav = new ModelAndView("redirect:/EmployeeRegister");
			mav.addObject("message", "specify the access granted to the employee");
		}
		else if(("").equals(phonenumber)){
			mav = new ModelAndView("redirect:/EmployeeRegister");
			mav.addObject("message", "phone number cannot be empty");
		}
		else if(("").equals(email)){
			mav = new ModelAndView("redirect:/EmployeeRegister");
			mav.addObject("message", "email cannot be empty");
		}
		else {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
			Date date = (Date)formatter.parse(DOB);
			Boolean user = userServices.CreateEmployeeUser(access, firstname, middlename, lastname, username, date, phonenumber, email);
			if (user)
			{
				mav =  new ModelAndView("redirect:/AdminHome");
				mav.addObject("message","Employee created successfully!!");
				//request.setAttribute("alertMsg", "Employee created successfully");
			}
			else
			{
				mav = new ModelAndView("redirect:/AdminHome");
				mav.addObject("message","Some issue with insertion!!");
			}
		}
		return mav;
	}

	@RequestMapping(value = "/emp_delete", method = RequestMethod.POST)
	public ModelAndView deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
		String firstname = (String) request.getParameter("firstname");
		String lastname = (String) request.getParameter("lastname");
		String username = (String) request.getParameter("username");
		ModelAndView mav = null;
		if(firstname.equals("")) {
			mav = new ModelAndView("redirect:/EmployeeDelete");
			mav.addObject("message", "first name cannot be empty");
		}
		else if(lastname.equals("")){
			mav = new ModelAndView("redirect:/EmployeeDelete");
			mav.addObject("message", "last name cannot be empty");
		}
		else if(username.equals("")){
			mav = new ModelAndView("redirect:/EmployeeDelete");
			mav.addObject("message", "user name cannot be empty");
		}
		else {
			Boolean user = userServices.DeleteUser(username);
			if (user)
			{
				mav =  new ModelAndView("redirect:/EmployeeDelete");
				mav.addObject("message","Employee username deleted successfully!!");

			}
			else
			{
				mav = new ModelAndView("redirect:/EmployeeDelete");
				mav.addObject("message","Username doesnt exist!!");

			}
		}

		return mav;
	}

	//Authorize transaction changes--->
	@RequestMapping(value="/checker", method = RequestMethod.GET)
	public ModelAndView transactions(HttpServletRequest request, HttpSession session) {
		ModelMap model = new ModelMap();

		List<Transaction> transactions=transactionServices.GetAllPendingTransactions();
		session.setAttribute("pendingTransaction", transactions);
		model.addAttribute("transactions", transactions);

		return new ModelAndView(("pendingTransaction"), model);

	}

	@RequestMapping(value="/authorize", method = RequestMethod.POST)
	public ModelAndView authorize(HttpServletRequest request, HttpSession session) {
		ModelMap model = new ModelMap();


		//System.out.println(request.getParameter("transaction1"));

		Employee approver =(Employee)session.getAttribute("EmployeeObject");
		List<Transaction> transactions=(List<Transaction>) request.getSession().getAttribute("pendingTransaction");//transactionServices.GetAllPendingTransactions();
		for(Transaction transaction:transactions ) {
			if(transaction.getTransactionID()==Integer.parseInt(request.getParameter("transaction1"))) {
				transactionServices.ApproveTransaction(approver, transaction);
			}
		}
		List<Transaction> freshtransactions=transactionServices.GetAllPendingTransactions();
		model.addAttribute("transactions", freshtransactions);
		return new ModelAndView("pendingTransaction",model);

	}
	//<-----

}
