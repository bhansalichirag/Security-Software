package main.java.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import main.java.dal.users.employees.Admin;

@Controller
public class EmployeeController {

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
    
    
	
}
