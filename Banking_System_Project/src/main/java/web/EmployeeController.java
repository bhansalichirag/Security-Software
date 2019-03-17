package main.java.web;


import java.text.ParseException;
import java.util.Date;

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
import main.java.dal.users.employees.Admin;

@Controller
public class EmployeeController {
	
	@Autowired
	IUserServices userServices;

	@RequestMapping(value="/AdminHome", method = RequestMethod.GET)
    public ModelAndView AdminHome(HttpServletRequest request, HttpSession session){
    	ModelMap model = new ModelMap();
        Admin Admin_emp = (Admin) session.getAttribute("AdminObject");
        if (Admin_emp == null)
        {
        	return new ModelAndView("Employee_Login");
        }
        return new ModelAndView(("Tier3Home"), model);
    }
    
    @RequestMapping(value="/EmployeeRegister", method = RequestMethod.GET)
    public ModelAndView EmployeeRegister(HttpServletRequest request, HttpSession session){
    	ModelMap model = new ModelMap();
        Admin Admin_emp = (Admin) session.getAttribute("AdminObject");
        if (Admin_emp == null)
        {
        	return new ModelAndView("EmployeeLogin");
        }
        return new ModelAndView(("EmployeeInsert"), model);
    }
    
    @RequestMapping(value="/EmployeeDelete", method = RequestMethod.GET)
    public ModelAndView EmployeeDelete(HttpServletRequest request, HttpSession session){
    	ModelMap model = new ModelMap();
        Admin Admin_emp = (Admin) session.getAttribute("AdminObject");
        if (Admin_emp == null)
        {
        	return new ModelAndView("EmployeeLogin");
        }
        return new ModelAndView(("EmployeeDelete"), model);
    }
    
    @RequestMapping(value = "/emp_insert", method = RequestMethod.POST)
	  public ModelAndView EmployeeInsert(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		String firstname = (String) request.getParameter("firstname");
		String middlename = (String) request.getParameter("middlename");
		String lastname = (String) request.getParameter("lastname");
		String username = (String) request.getParameter("username");
		//String password = (String) request.getParameter("password");
		//String confirmpassword = (String) request.getParameter("confirmpassword");
		String password = username;	//initial password is same as the username, after initial login the tier1or2 user has to change the password
		String confirmpassword = username;
		String phonenumber = (String) request.getParameter("phonenumber");
		String email = (String) request.getParameter("email");
		String access = (String)request.getParameter("designation");	
		String DOB = (String)request.getParameter("date_of_birth");
		String address = (String)request.getParameter("Address");
		//String SSN = (String)request.getParameter("ssn");
		String SSN = "000000000000";
		
		ModelAndView mav = null;
		
		if(("").equals(firstname)) {
			mav = new ModelAndView("redirect:/EmployeeRegister");
		    mav.addObject("message", "first name cannot be empty");
		}
		else if(("").equals(lastname)){
			mav = new ModelAndView("redirect:/EmployeeRegister");
		    mav.addObject("message", "last name cannot be empty");
		}
		else if(("").equals(middlename)){
			mav = new ModelAndView("redirect:/EmployeeRegister");
		    mav.addObject("message", "middle name cannot be empty");
		}
		else if(("").equals(username)){
			mav = new ModelAndView("redirect:/EmployeeRegister");
		    mav.addObject("message", "user name cannot be empty");
		}
		else if(("").equals(password)){
			mav = new ModelAndView("redirect:/EmployeeRegister");
		    mav.addObject("message", "password cannot be empty");
		}
		else if(!password.equals(confirmpassword)){
			mav = new ModelAndView("redirect:/EmployeeRegister");
		    mav.addObject("message", "password and confirm password does not match");
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
			//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd",Locale.US);
			Date date1 = new Date();
			Boolean user = userServices.CreateCustomerUser(access, firstname, middlename, lastname, username,date1, password, phonenumber, email, address,SSN, "abc","def");
			if (user)
			{
				mav =  new ModelAndView("redirect:AdminHome");
				mav.addObject("message","Employee created successfully!!");
			}
			else
			{
				mav = new ModelAndView("redirect:AdminHome");
				mav.addObject("message","Some issue with insertion!!");
			}
		}
	    return mav;
	  }
	
}
