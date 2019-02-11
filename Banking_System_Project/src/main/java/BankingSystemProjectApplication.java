package main.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import main.java.business.services.IUserServices;

@SpringBootApplication
public class BankingSystemProjectApplication implements CommandLineRunner{

	@Autowired
	IUserServices userServices;
	
	public static void main(String[] args) {   
		SpringApplication.run(BankingSystemProjectApplication.class, args);
	}
	
	@Override
    public void run(String... arg0) throws Exception {
		userServices.createUser();
    }

}

