package main.java.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.dal.users.customers.Individual;
import main.java.repositories.UserRepository;


@Service
public class UserServiesImpl implements IUserServices {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void createUser() {
		Individual customer = new Individual("John", null, "Doe", "jhdoe", "password", "4804808000", "abc@abc", "somewhere", "123456789", "Super");
		userRepository.save(customer);
	}

}
