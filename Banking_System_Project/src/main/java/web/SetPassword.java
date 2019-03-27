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
@Controller
public class SetPassword {

	@Autowired
	IUserServices userServices;
	
	@RequestMapping(value= {"/SetPassword"}, method = RequestMethod.GET)
    public String SetupPassword(ModelMap model){
        String name = (String) model.get("name");
        model.put("SetPassword", name);
        return "SetPassword";
    }
	
	@RequestMapping(value="/setpassword",method = RequestMethod.POST)
	public ModelAndView Setpassword(HttpServletRequest request, HttpSession session){
		String userName = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		String confirmpassword = (String) request.getParameter("confirmpassword");
		ModelAndView mav = null;
		if(userServices.isNewUser(userName))
        {
			if(("").equals(password)){
				mav = new ModelAndView("redirect:/SetPassword");
			    mav.addObject("message", "password cannot be empty");
			}
			else if(!password.equals(confirmpassword)){
				mav = new ModelAndView("redirect:/SetPassword");
			    mav.addObject("message", "password and confirm password does not match");
			}
			else
			{
				if(userServices.updatePassword(userName, null, password))
				{
					mav = new ModelAndView("redirect:/login");
				}
				else
				{
					mav = new ModelAndView("redirect:/SetPassword");
				    mav.addObject("message", "password couldnt be updated!!");
				}
			}
        }
		return mav;
	}
	
}
