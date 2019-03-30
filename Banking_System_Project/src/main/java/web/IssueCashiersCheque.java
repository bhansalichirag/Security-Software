package main.java.web;

import java.text.ParseException;
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

import main.java.business.services.ICashiersCheckService;
import main.java.dal.users.User;
import main.java.dal.users.employees.Admin;
import main.java.dal.users.employees.Employee;
import main.java.dal.users.employees.Tier1;

@Controller
public class IssueCashiersCheque {

	@Autowired
	ICashiersCheckService cashierservice;
	
	@RequestMapping(value= "/IssueCheque", method = RequestMethod.GET)
	public ModelAndView IssueCheque(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		try {
			Employee emp = (Employee) session.getAttribute("EmployeeObject");
			if (emp == null)
			{
				return new ModelAndView("redirect:/login");
			}
			else if(emp instanceof Tier1)
			{
			String role = (String)session.getAttribute("role");
			model.addAttribute("role",role);
			return new ModelAndView(("IssueCashiersCheque"), model);
			}
			else
			{
				model.addAttribute("message","not authorized for this page");
				return new ModelAndView("Login",model);
			}
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}
	
	@RequestMapping(value = "/searchcheque", method = RequestMethod.POST)
	public ModelAndView Cheques(HttpServletRequest request, HttpSession session) throws ParseException {
		ModelMap model = new ModelMap();
		try {
			String chequeid = request.getParameter("chequeid").trim();
			User emp = (User)session.getAttribute("EmployeeObject"); 
			if (emp == null)
			{
				return new ModelAndView("Login");
			}
			if(chequeid!="")
			{
				
					if(emp instanceof Tier1)
					{
						ModelAndView mav = new ModelAndView(("IssueCashiersCheque"), model);
						if(cashierservice.IssueCashiersCheck(chequeid, (Employee)emp))
						{
							mav.addObject("message", "Cheque Issued");
						}
						else
						{
							mav.addObject("message", "Cheque couldnt be issued");
						}
						return mav;
					}
					else
					{
						model.addAttribute("message", "You are not authorized to see this users info");
						return new ModelAndView(("Login"), model);
					}
			}
			else
			{
				model.addAttribute("message", "please enter a cheque id");
				return new ModelAndView(("IssueCashiersCheque"), model);
			}
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}
	
}
