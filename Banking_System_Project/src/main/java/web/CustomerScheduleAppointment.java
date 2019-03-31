package main.java.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.business.services.AppointmentServices;
import main.java.business.services.UserServiesImpl;
import main.java.dal.users.User;
import main.java.dal.users.customers.Customer;
@Controller
public class CustomerScheduleAppointment {

	@Autowired
	AppointmentServices appointmentservices;

	@RequestMapping(value= {"/ScheduleAppointment"}, method = RequestMethod.POST)
	public ModelAndView ScheduleAppointment(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		try{Customer user_cust = (Customer) session.getAttribute("CustomerObject");
		if (user_cust == null && session.getAttribute("OtpValid") == null)
		{
			return new ModelAndView("redirect:/login");
		}
		session.setAttribute("OtpValid", null);
		return new ModelAndView(("ScheduleAppointment"), model);
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}

	@RequestMapping(value= {"/appointment"}, method = RequestMethod.POST)
	public ModelAndView BookAppointment(HttpServletRequest request, HttpSession session){

		ModelMap model = new ModelMap();
		try
		{
			User user_cust = (User) session.getAttribute("CustomerObject");
			if(user_cust==null)
			{
				return new ModelAndView(("redirect:/login"), model);
			}
			else
			{
				if(user_cust instanceof Customer)
				{
					String schedule_date = (String)request.getParameter("schedule_date").trim();
					String reason = (String) request.getParameter("appointment").trim();
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
					Date date = (Date)formatter.parse(schedule_date);
					if(appointmentservices.bookOnDate((Customer)user_cust, date, reason))
					{
						model.addAttribute("message", "Appointment booked");
						return new ModelAndView(("ScheduleAppointment"), model);
					}
					else
					{
						model.addAttribute("message", "All appointment slots are booked for today");
						return new ModelAndView(("ScheduleAppointment"), model);
					}
				}
				else
				{
					model.addAttribute("message", "not authorized");
					return new ModelAndView(("Login"), model);
				}
			}
		}
		catch(Exception ex)
		{
			return new ModelAndView("Login");
		}
	}

}
