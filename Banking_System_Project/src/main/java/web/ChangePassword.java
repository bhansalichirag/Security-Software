package main.java.web;

import javax.servlet.http.HttpServletRequest;
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
import main.java.dal.users.employees.Employee;
@Controller
public class ChangePassword {

	@Autowired
	IUserServices userServices;

	@RequestMapping(value= "/ChangePassword", method = RequestMethod.GET)
	public ModelAndView ChangePasswordPath(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		try{
			Employee Tier_emp = (Employee) session.getAttribute("EmployeeObject");
			Customer cust_emp = (Customer) session.getAttribute("CustomerObject"); 
			if (Tier_emp == null && cust_emp==null)
			{
				return new ModelAndView("redirect:/login");
			}
			String role = (String)session.getAttribute("role");
			model.addAttribute("role",role);
			return new ModelAndView(("ChangePassword"), model);
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}
	@RequestMapping(value="/changepassword", method = RequestMethod.POST)
	public ModelAndView Changethepassword(HttpServletRequest request, HttpSession session){
		ModelAndView mav = new ModelAndView();
		try {
			String userName = (String) request.getParameter("username").trim();
			String oldpassword = (String) request.getParameter("oldpassword").trim();
			String newpassword = (String) request.getParameter("newpassword").trim();
			String confirmpassword = (String) request.getParameter("confirmpassword").trim();
			if(userName=="" || oldpassword=="" || newpassword=="" || confirmpassword=="")
			{
				mav = new ModelAndView("ChangePassword");
				mav.addObject("message", "Please enter proper values for all");
			}
			else {
				User user = userServices.ValidateUser(userName, oldpassword);
				if (user == null)
				{
					mav = new ModelAndView("ChangePassword");
					mav.addObject("message", "Please enter a valid old password or username");
				}
				else 
				{
					if(!newpassword.equals(confirmpassword)){
						mav = new ModelAndView("ChangePassword");
						mav.addObject("message", "new password and confirm password does not match");
					}
					else
					{
						if(userServices.updatePassword(userName, oldpassword, newpassword))
						{
							mav = new ModelAndView("Login");
							mav.addObject("message", "use your new password for login");
						}
						else
						{
							mav = new ModelAndView("ChangePassword");
							mav.addObject("message", "password couldnt be updated!!");
						}
					}
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
