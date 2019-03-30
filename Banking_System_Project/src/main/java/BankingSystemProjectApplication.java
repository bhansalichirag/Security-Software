package main.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import main.java.business.services.AppointmentServices;
import main.java.business.services.ITransactionServices;
import main.java.business.services.IUserServices;
import main.java.business.services.LoggingService;
import main.java.repositories.AccountRepository;
import main.java.repositories.UserRepository;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"main.java.web","main.java.business.services","main.java.business.repositories"})
public class BankingSystemProjectApplication implements CommandLineRunner{

	@Autowired
	IUserServices userServices;
	@Autowired
	ITransactionServices transactionServices;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AppointmentServices appointmentServices;
	@Autowired
	LoggingService logger;
	
	public static void main(String[] args) {  
		SpringApplication.run(BankingSystemProjectApplication.class, args);
		
	}
	
	@Override
    public void run(String... arg0) throws Exception { 
//		userServices.CreateEmployeeUser("Tier1", "Program", "", "Tester", "Ptest12", new Date(), "8412360456", "donotreplyss545@gmail.com");
//		appointmentServices.getAppointmentsForUser("tester.merchant");
//		System.out.println();
//		System.out.println(userServices.DeleteUser("QuaeaterX"));
//		userServices.CreateCustomerUser("Merchant", "Quaky", "Oaty", "Maker", "Merch", new Date(),"passwaad", "12252998","kfc@seller", "asdasdas",
//				"120698755", "sq1", "sq2");
//		@SuppressWarnings("unused")
//		Customer x = (Customer) userRepository.findById("QuaeaterX").get();
//		CreditCard sa = new CreditCard(50.0, 25.0);
//		accountRepository.save(sa);
//		x.addAccount(sa);
//		userRepository.save(x);
//		System.out.println();
//		List<Transaction> transactions = transactionServices.GetAllPendingTransactions();
//		Tier2 approver = (Tier2) userRepository.findById("tier2user").get();
//		transactionServices.DeclineTransactions(approver, transactions);
//		userServices.ValidateUser("jhdoe","password");

    }

}
