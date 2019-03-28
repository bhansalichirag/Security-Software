package main.java.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.business.services.IUserServices;
@Controller
public class ForgotPassword {

	@Autowired
	IUserServices userServices;
	
	@RequestMapping(value = "/ForgotPassword", method = RequestMethod.GET)
	 public ModelAndView PasswordForgotten(HttpServletRequest request, HttpServletResponse response) {
		
		    ModelAndView mav = new ModelAndView("ForgotPassword");
		    mav.addObject("ForgotPassword");
		    mav.addObject("message",request.getParameter("message"));
	    
	    return mav;
	  }
	
	@RequestMapping(value="/forgotpassword", method=RequestMethod.POST)
	public ModelAndView ForgotPasswordfn(HttpServletRequest request, HttpSession session){
		ModelAndView mav = new ModelAndView();
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		/*User user = userServices.UserFinder(username);
		if(user.getEmail()!= email)
		{
			mav = new ModelAndView("ForgotPassword");
			mav.addObject("message", "Email not matching");
		}
		else
		{
			//OTP part
		}*/
		session.setAttribute("username", username);
		//mav = new ModelAndView("ForgotPasswordOTP");//uncomment after otp is resolved
		mav = new ModelAndView("NewPassword");//comment this after otp is resolved
		return mav;
	}
	
	
	@RequestMapping(value="/newpassword", method=RequestMethod.POST)
	public ModelAndView NewPassword(HttpServletRequest request, HttpSession session){
		ModelAndView mav = new ModelAndView();
		String userName = (String)session.getAttribute("username");
		String newpassword = (String) request.getParameter("newpassword");
		String confirmpassword = (String) request.getParameter("confirmpassword");
		if(!newpassword.equals(confirmpassword)){
			mav = new ModelAndView("redirect:/ChangePassword");
		    mav.addObject("message", "new password and confirm password does not match");
		}
		else
		{
			if(userServices.updatePassword(userName, null, newpassword))
			{
				mav = new ModelAndView("redirect:/login");
			}
			else
			{
				mav = new ModelAndView("redirect:/ChangePassword");
			    mav.addObject("message", "password couldnt be updated!!");
			}
		}
		return mav;
	}
	
}
