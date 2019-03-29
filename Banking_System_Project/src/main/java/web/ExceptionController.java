package main.java.web;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import main.java.business.exceptions.*;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(AccountNotFoundException.class)
	public ModelAndView HandleAccountNotFoundException(HttpSession session, AccountNotFoundException e) {

		ModelMap model = new ModelMap();
		session.setAttribute("errorMsg","Incorrect Account Information bro!");
		return new ModelAndView(("redirect:/accinfo"), model);
		//        return ResponseEntity
		//                .status(HttpStatus.FORBIDDEN)
		//                .body("Error Message");
	}     

	@ExceptionHandler(TransactionFailedException.class)
	public ModelAndView handleTransactionFailedException(HttpSession session, TransactionFailedException e) {

		ModelMap model = new ModelMap();
		session.setAttribute("errorMsg","Transacation could not be processed! Please try again");
		return new ModelAndView(("redirect:/accinfo"), model);
	}    

	@ExceptionHandler(CustomerNotFoundException.class)
	public ModelAndView handleCustomerNotFoundException(HttpSession session, CustomerNotFoundException e) {

		ModelMap model = new ModelMap();
		session.setAttribute("errorMsg","User Not found by Email or Phone Number! Please try again");
		return new ModelAndView(("redirect:/accinfo"), model);
	}   


	@ExceptionHandler(MerchantPaymentUnmatchedException.class)
	public ModelAndView handleMerchantPaymentUnmatchedException(HttpSession session, MerchantPaymentUnmatchedException e) {

		ModelMap model = new ModelMap();
		session.setAttribute("errorMsg","Merchant not authorized or Wrong Customer Credentials");
		return new ModelAndView(("redirect:/accinfo"), model);
	} 

	@ExceptionHandler(CashierCheckNotFoundException.class)
	public ModelAndView handleCashierCheckNotFoundException(HttpSession session, CashierCheckNotFoundException e) {

		ModelMap model = new ModelMap();
		session.setAttribute("errorMsg","Cashier's Check not found or not issued to this customer!");
		return new ModelAndView(("redirect:/accinfo"), model);
	}  

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> generalExceptionHandler(Exception e) {

		return ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.body("<b>Unauthorized Access! Login Again</b>");
	}   

	@ExceptionHandler(InvalidData.class)
	public ModelAndView handleInvalidData(HttpSession session, InvalidData e) {

		ModelMap model = new ModelMap();
		session.setAttribute("errorMsg","Corrupt Data Entry!");
		return new ModelAndView(("redirect:/accinfo"), model);
	}   

}
