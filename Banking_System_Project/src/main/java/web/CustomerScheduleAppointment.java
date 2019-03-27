package main.java.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.dal.users.customers.Customer;
@Controller
public class CustomerScheduleAppointment {

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
	
	@RequestMapping(value= {"/appointment"}, method = RequestMethod.POST)
    public ModelAndView BookAppointment(HttpServletRequest request, HttpSession session){
		
		ModelMap model = new ModelMap();
		Customer user_cust = (Customer) session.getAttribute("CustomerObject");
		String username = (String)user_cust.getUsername();
		String DOB = (String)request.getParameter("schedule_date").trim();
		String reason = (String) request.getParameter("appointment").trim();
		
		return new ModelAndView(("redirect:/ScheduleAppointment"), model);
    }
	
}
