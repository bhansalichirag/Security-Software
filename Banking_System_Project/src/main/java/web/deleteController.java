package main.java.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.dal.users.employees.DeleteEmp;

@Controller
public class deleteController {
	
	
		 @RequestMapping(value = "/emp_delete", method = RequestMethod.GET)
		 public ModelAndView Delete_emp(HttpServletRequest request, HttpServletResponse response) {
			
			    ModelAndView mav = new ModelAndView("emp_delete");
			    mav.addObject("emp_delete", new DeleteEmp());
			    mav.addObject("message",request.getParameter("message"));
		    
		    return mav;
		  }
		 
		 @RequestMapping(value = "/index3", method = RequestMethod.POST)
		  public ModelAndView deleteProcess(HttpServletRequest request, HttpServletResponse response) {
			String firstname = (String) request.getParameter("firstname");
			String lastname = (String) request.getParameter("lastname");
			String username = (String) request.getParameter("username");
			ModelAndView mav = null;
			if(firstname.equals("")) {
				mav = new ModelAndView("redirect:emp_delete");
			    mav.addObject("message", "first name cannot be empty");
			}
			else if(lastname.equals("")){
				mav = new ModelAndView("redirect:emp_delete");
			    mav.addObject("message", "last name cannot be empty");
			}
			else if(username.equals("")){
				mav = new ModelAndView("redirect:emp_delete");
			    mav.addObject("message", "user name cannot be empty");
			}
			else {
			  mav =  new ModelAndView("Tier3Home");
			
			}
			
			return mav;
		  }

}
