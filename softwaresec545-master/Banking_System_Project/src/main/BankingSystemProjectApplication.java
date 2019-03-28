package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import main.java.business.services.ITransactionServices;
import main.java.business.services.IUserServices;
import main.java.repositories.UserRepository;

@SpringBootApplication
@ComponentScan(basePackages = {"main.java.web","main.java.business.services","main.java.business.repositories"})
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})//bypass this spring boot security mechanism.
public class BankingSystemProjectApplication implements CommandLineRunner{

	@Autowired
	IUserServices userServices;
	@Autowired
	ITransactionServices transactionServices;
	
	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {  
		SpringApplication.run(BankingSystemProjectApplication.class, args);
		
	}
	
	@Override
    public void run(String... arg0) throws Exception { 
//		List<Transaction> transactions = transactionServices.GetAllPendingTransactions();
//		Tier2 approver = (Tier2) userRepository.findById("tier2user").get();
//		transactionServices.DeclineTransactions(approver, transactions);
//		userServices.CreateEmployeeUser("Tier1", "Quaker", "Oats", "Eater", "Quaeater", new Date(), "12256998", "asdasdas");
		System.out.println(userServices.DeleteUser("Quaeater"));
//		userServices.ValidateUser("jhdoe","password");

    }

}