package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import main.java.business.services.IUserServices;
import main.java.repositories.UserRepository;

@SpringBootApplication
@ComponentScan(basePackages = {"main.java.web","main.java.business.services","main.java.business.repositories"})
public class BankingSystemProjectApplication implements CommandLineRunner{

	@Autowired
	IUserServices userServices;
	
	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {   
		SpringApplication.run(BankingSystemProjectApplication.class, args);
	}
	
	@Override
    public void run(String... arg0) throws Exception {
//		userServices.createUser();
//		userServices.ValidateUser("jhdoe","password");
    }

}
