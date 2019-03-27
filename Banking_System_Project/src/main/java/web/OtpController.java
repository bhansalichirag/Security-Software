package main.java.web;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import main.java.web.*;
@Controller
public class OtpController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	public OtpService otpService;
	@Autowired
	public MyEmailService myEmailService;
	
	@GetMapping("/generateOtp")
	public String generateOtp(){
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
//		System.out.println(auth);
//		String username = auth.getName();
		int otp = otpService.generateOTP("psjagtap");
		logger.info("OTP : "+otp);
		//Generate The Template to send OTP 
		EmailTemplate template = new EmailTemplate("SendOtp.html");
		Map<String,String> replacements = new HashMap<String,String>();
		replacements.put("user", "psjagtap");
		replacements.put("otpnum", String.valueOf(otp));
		String message = template.getTemplate(replacements);
<<<<<<< HEAD
		myEmailService.sendOtpMessage("sgpt18@gmail.com", "OTP -SpringBoot", message);
=======
		myEmailService.sendOtpMessage("sgpt18@gmail.com", "OTP -SpringBoot", "Hi Your OTP is "+otp+" .");
>>>>>>> ea082a0942a8de6c3ec87636ebcd08cc634105b9
		
		return "otppage";
	}
	@RequestMapping(value ="/validateOtp", method = RequestMethod.GET)
	public @ResponseBody String validateOtp(@RequestParam("otpnum") int otpnum){
		final String SUCCESS = "Entered Otp is valid";
		final String FAIL = "Entered Otp is NOT valid. Please Retry!";
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		//String username = auth.getName();
		logger.info(" Otp Number : "+otpnum);
		//Validate the Otp 
		if(otpnum >= 0){
			int serverOtp = otpService.getOtp("psjagtap");
			if(serverOtp > 0){
				if(otpnum == serverOtp){
					otpService.clearOTP("psjagtap");
					return ("Entered Otp is valid");
				}else{
					return SUCCESS;
				}
			}else {
				return FAIL;
			}
		}else {
			return FAIL;
		}
	}
}
