package main.java.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.business.services.IUserServices;
import main.java.dal.users.User;
import main.java.dal.users.employees.Admin;
import main.java.dal.users.employees.Employee;
import main.java.dal.users.employees.Tier1;
import main.java.dal.users.employees.Tier2;
@Controller
public class EmployeeCRUDOperation {

	@Autowired
	IUserServices userServices;

	@RequestMapping(value="/EmployeeRegister", method = RequestMethod.GET)
	public ModelAndView EmployeeRegister(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		try {
			Admin Admin_emp = (Admin) session.getAttribute("EmployeeObject");
			if (Admin_emp == null)
			{
				return new ModelAndView("redirect:/login");
			}
			return new ModelAndView(("EmployeeInsert"), model);
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}

	@RequestMapping(value="/EmployeeUpdate", method = RequestMethod.GET)
	public ModelAndView EmployeeUpdate(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		try {
			Employee emp = (Employee) session.getAttribute("EmployeeObject");
			if (emp == null)
			{
				return new ModelAndView("redirect:/login");
			}
			/*String abc = emp.getEmail();
			request.setAttribute("EmployeeObject",(Employee)emp);
			*/
			request.setAttribute("Email","");
			request.setAttribute("FirstName","");
			request.setAttribute("MiddleName", "");
			request.setAttribute("Phone", "");
			request.setAttribute("LastName", "");
			return new ModelAndView(("EmployeeUpdate"), model);
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}

	@RequestMapping(value="/EmployeeDelete", method = RequestMethod.GET)
	public ModelAndView EmployeeDelete(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		try {
			Admin Admin_emp = (Admin) session.getAttribute("EmployeeObject");
			if (Admin_emp == null)
			{
				return new ModelAndView("redirect:/login");
			}
			return new ModelAndView(("EmployeeDelete"), model);
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}

	@RequestMapping(value="/emp_update_search", method= RequestMethod.POST)
	public ModelAndView EmployeeSearch(HttpServletRequest request, HttpSession session) throws ParseException {
		ModelMap model = new ModelMap();
		try {
			String username = request.getParameter("username_search").trim();
			User emp = (User)session.getAttribute("EmployeeObject"); 
			if (emp == null)
			{
				return new ModelAndView("Login");
			}
			if(username!="")
			{
				User user = (User)userServices.GetCustomerByUsername(username);
				if(user!=null)
				{
					if(user instanceof Employee && emp instanceof Admin)
					{
						model.addAttribute("empusername",user.getUsername());
						request.setAttribute("Email",user.getEmail());
						request.setAttribute("FirstName",user.getFirstName());
						String temp_mid = user.getMiddleName();
						if(temp_mid==null || temp_mid=="")
						{
							request.setAttribute("MiddleName", "");
						}
						else
						{
							request.setAttribute("MiddleName", temp_mid);
						}
						request.setAttribute("LastName", user.getLastName());
						String temp_phone = user.getPhoneNumber();
						if(temp_phone==null || temp_phone=="")
						{
							request.setAttribute("Phone", "");
						}
						else
						{
							request.setAttribute("Phone", user.getPhoneNumber());
						}
						return new ModelAndView(("EmployeeUpdate"), model);
					}
					else
					{
						model.addAttribute("message", "You are not authorized to see this users info");
						return new ModelAndView(("EmployeeUpdate"), model);
					}
				}
				else
				{
					model.addAttribute("message", "No such user exists currently");
					return new ModelAndView(("EmployeeUpdate"), model);
				}
			}
			else
			{
				model.addAttribute("message", "please enter a username");
				return new ModelAndView(("EmployeeUpdate"), model);
			}
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
		
	}
	
	@RequestMapping(value = "/emp_update", method = RequestMethod.POST)
	public ModelAndView EmployeeUpdateValues(HttpServletRequest request, HttpSession session) throws ParseException {
		try {
			Employee emp = (Employee) session.getAttribute("EmployeeObject");
			String username = (String) request.getParameter("empusername");
			String firstname = (String) request.getParameter("firstname");
			String middlename = (String) request.getParameter("middlename");
			String lastname = (String) request.getParameter("lastname");
			String phonenumber = (String) request.getParameter("phone");
			String email = (String) request.getParameter("email");
			//String ssn = (String)request.getParameter("ssn");	
			String DOB = (String)request.getParameter("date_of_birth");
			//String SeqQuestion1 = (String)request.getParameter("seqquestion1");
			//String SeqQuestion2 = (String)request.getParameter("seqquestion2");
			ModelAndView mav =null;
			if(("").equals(username)) {
				mav = new ModelAndView("EmployeeUpdate");
				mav.addObject("message", "first name cannot be empty");
			}
			if(("").equals(firstname)) {
				mav = new ModelAndView("EmployeeUpdate");
				mav.addObject("message", "first name cannot be empty");
			}
			else if(("").equals(lastname)){
				mav = new ModelAndView("EmployeeUpdate");
				mav.addObject("message", "last name cannot be empty");
			}
			else if(("").equals(DOB)){
				mav = new ModelAndView("EmployeeUpdate");
				mav.addObject("message", "date cannot be empty");
			}
			else if(("").equals(phonenumber)){
				mav = new ModelAndView("EmployeeUpdate");
				mav.addObject("message", "phone number cannot be empty");
			}
			else if(("").equals(email)){
				mav = new ModelAndView("EmployeeUpdate");
				mav.addObject("message", "email cannot be empty");
			}
			/*else if(("").equals(ssn)) {
				mav = new ModelAndView("EmployeeUpdate");
				mav.addObject("message","ssn cannot be empty");
			}
			else if(("").equals(SeqQuestion1)) {
				mav = new ModelAndView("redirect:/EmployeeUpdate");
				mav.addObject("message","ssn cannot be empty");
			}
			else if(("").equals(SeqQuestion2)) {
				mav = new ModelAndView("redirect:/EmployeeUpdate");
				mav.addObject("message","ssn cannot be empty");
			}*/
			else {
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
				Date date = (Date)formatter.parse(DOB);
				Boolean user = userServices.UpdateEmployee(firstname, middlename, lastname, username, date, "", phonenumber, email);
				if (user)
				{
					mav =  new ModelAndView("EmployeeUpdate");
					mav.addObject("message","Employee updated successfully!!");
				}
				else
				{
					mav = new ModelAndView("EmployeeUpdate");
					mav.addObject("message","Some issue with insertion!!");
				}
			}
			return mav;
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}

	@RequestMapping(value = "/emp_insert", method = RequestMethod.POST)
	public ModelAndView EmployeeInsert(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		try {
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
				mav = new ModelAndView("EmployeeInsert");
				mav.addObject("message", "first name cannot be empty");
			}
			else if(("").equals(lastname)){
				mav = new ModelAndView("EmployeeInsert");
				mav.addObject("message", "last name cannot be empty");
			}
			else if(("").equals(username)){
				mav = new ModelAndView("EmployeeInsert");
				mav.addObject("message", "user name cannot be empty");
			}
			else if(("").equals(DOB)){
				mav = new ModelAndView("EmployeeInsert");
				mav.addObject("message", "date cannot be empty");
			}
			else if(access == null){
				mav = new ModelAndView("EmployeeInsert");
				mav.addObject("message", "specify the access granted to the employee");
			}
			else if(("").equals(phonenumber)){
				mav = new ModelAndView("EmployeeInsert");
				mav.addObject("message", "phone number cannot be empty");
			}
			else if(("").equals(email)){
				mav = new ModelAndView("EmployeeInsert");
				mav.addObject("message", "email cannot be empty");
			}
			else {
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
				Date date = (Date)formatter.parse(DOB);
				Boolean user = userServices.CreateEmployeeUser(access, firstname, middlename, lastname, username, date, phonenumber, email);
				if (user)
				{
					mav =  new ModelAndView("EmployeeInsert");
					mav.addObject("message","Employee created successfully!!");
					//request.setAttribute("alertMsg", "Employee created successfully");
				}
				else
				{
					mav = new ModelAndView("EmployeeInsert");
					mav.addObject("message","Some issue with insertion!!");
				}
			}
			return mav;
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}

	@RequestMapping(value = "/emp_delete", method = RequestMethod.POST)
	public ModelAndView deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
		try {
			String firstname = (String) request.getParameter("firstname");
			String lastname = (String) request.getParameter("lastname");
			String username = (String) request.getParameter("username");
			ModelAndView mav = null;
			if(firstname.equals("")) {
				mav = new ModelAndView("EmployeeDelete");
				mav.addObject("message", "first name cannot be empty");
			}
			else if(lastname.equals("")){
				mav = new ModelAndView("EmployeeDelete");
				mav.addObject("message", "last name cannot be empty");
			}
			else if(username.equals("")){
				mav = new ModelAndView("EmployeeDelete");
				mav.addObject("message", "user name cannot be empty");
			}
			else {
				Boolean user = userServices.DeleteUser(username);
				if (user)
				{
					mav =  new ModelAndView("EmployeeDelete");
					mav.addObject("message","Employee username deleted successfully!!");

				}
				else
				{
					ModelMap model = new ModelMap();
					model.addAttribute("message","Username doesnt exist!!");
					mav = new ModelAndView("EmployeeDelete", model);
				}
			}

			return mav;
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}

}
