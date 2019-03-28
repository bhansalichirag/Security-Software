package main.java.web;

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
import main.java.dal.users.customers.Customer;
import main.java.dal.users.customers.Individual;
import main.java.dal.users.employees.Admin;
import main.java.dal.users.employees.Tier1;
import main.java.dal.users.employees.Tier2;

@Controller
public class Login {

	@Autowired
	IUserServices userServices;
	
	@RequestMapping(value= {",","/login"}, method = RequestMethod.GET)
    public String welcome(ModelMap model){
        String name = (String) model.get("name");
        model.put("Login", name);
        return "Login";
	}
	
	@RequestMapping(value="/redirectuser", method = RequestMethod.POST)
    public ModelAndView sortUser(HttpServletRequest request, HttpSession session){
    	String userName = (String) request.getParameter("uname");
		String password = (String) request.getParameter("psw");
		ModelAndView mav =null;
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
        		mav = new ModelAndView("Login");
        		mav.addObject("message","password or username is invalid");
        		return mav;
        	}
        	else 
        	{
        		if(user instanceof Customer) {        	
        			user.setPassword("");
        			if(user instanceof Individual)
        			{
        				session.setAttribute("role", "Individual");
        			}
        			else
        			{
        				session.setAttribute("role", "Merchant");
        			}
        			session.setAttribute("CustomerObject", (Customer)user);
        			return new ModelAndView("redirect:/accinfo");
        		}
        		else if(user instanceof Admin)
        		{
        			session.setAttribute("EmployeeObject", (Admin)user);
        			session.setAttribute("role", "Admin");
        			return new ModelAndView("redirect:/AdminHome");
        		}
        		else if(user instanceof Tier1)
        		{
        			session.setAttribute("EmployeeObject", (Tier1)user);
        			session.setAttribute("role", "Tier1");
        			return new ModelAndView("redirect:/Tier1Dash");
        		}
        		else
        		{
        			session.setAttribute("EmployeeObject", (Tier2)user);
        			session.setAttribute("role", "Tier2");
        			return new ModelAndView("redirect:/Tier2Dash");
        		}
        	}
        }
        //return null;
    }
}
