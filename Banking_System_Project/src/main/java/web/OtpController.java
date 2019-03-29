package main.java.web;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import main.java.business.EmailTemplate;
import main.java.business.services.EmailService;
import main.java.business.services.IUserServices;
import main.java.business.services.OtpService;
import main.java.dal.users.User;


@Controller
public class OtpController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	public OtpService otpService;
	@Autowired
	public EmailService myEmailService;
	@Autowired
	public IUserServices userServices;

	@RequestMapping(value ="/generateAccountOtp", method = RequestMethod.GET)
	public ModelAndView generateLoginOtp(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		
		User user = (User) session.getAttribute("CustomerObject");
		if(user == null)
		{
			return new ModelAndView("Login");
		}
		String username = user.getUsername();
		int otp = otpService.generateOTP(username);
		logger.info("OTP : "+otp);
		//Generate The Template to send OTP 
		EmailTemplate template = new EmailTemplate("emailTemplate.html");
		Map<String,String> replacements = new HashMap<String,String>();
		replacements.put("user", username);
		replacements.put("otpnum", String.valueOf(otp));
		String message = "Your Otp is : " + String.valueOf(otp) + " . Please enter it exactly";
		myEmailService.sendOtpMessage("do.no.reply.ss545@gmail.com", "OTP for Login", message);

		return new ModelAndView("OtpPage",model);
	}
	
	@RequestMapping(value ="/generateAppointmentOtp", method = RequestMethod.GET)
	public ModelAndView generateAppointmentOtp(HttpServletRequest request, HttpSession session){
		ModelMap model = new ModelMap();
		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		
		User user = (User) session.getAttribute("CustomerObject");
		if(user == null)
		{
			return new ModelAndView("Login");
		}
		String username = user.getUsername();
		int otp = otpService.generateOTP(username);
		logger.info("OTP : "+otp);
		//Generate The Template to send OTP 
		EmailTemplate template = new EmailTemplate("emailTemplate.html");
		Map<String,String> replacements = new HashMap<String,String>();
		replacements.put("user", username);
		replacements.put("otpnum", String.valueOf(otp));
		String message = "Your Otp is : " + String.valueOf(otp) + " . Please enter it exactly";
		myEmailService.sendOtpMessage("do.no.reply.ss545@gmail.com", "OTP for Login", message);

		return new ModelAndView("OtpPageAppointment",model);
	}
	
	@RequestMapping(value ="/validateOtp", method = RequestMethod.GET)
	public @ResponseBody String validateOtp(@RequestParam("otpnum") int otpnum, HttpSession session){
		final String SUCCESS = "Entered Otp is valid";
		final String FAIL = "Entered Otp is NOT valid. Please Retry!";
		logger.info(" Otp Number : "+otpnum); 
		//Validate the Otp 
		if(otpnum >= 0){
			int serverOtp = otpService.getOtp("QuaeaterX");
			if(serverOtp > 0){
				if(otpnum == serverOtp){
					session.setAttribute("OtpValid", serverOtp);
					otpService.clearOTP("QuaeaterX");
					
					return SUCCESS;
				}else{
					return FAIL;
				}
			}else {
				return FAIL;
			}
		}else {
			return FAIL;
		}

	}
}
