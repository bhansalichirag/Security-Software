package main.java.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.dal.users.User;
import main.java.dal.users.employees.Employee;
@Controller
public class SearchCustomerEmployeeDetails {

	@RequestMapping(value= "/Search", method = RequestMethod.GET)
    public ModelAndView Search(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
        Employee emp = (Employee) session.getAttribute("EmployeeObject");
        if (emp == null)
        {
        	return new ModelAndView("redirect:/login");
        }
        String role = (String)session.getAttribute("role");
        model.addAttribute("role",role);
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
	
}
