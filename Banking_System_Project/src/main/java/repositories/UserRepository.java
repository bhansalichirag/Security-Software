package main.java.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import main.java.dal.users.User;
import main.java.dal.users.customers.Individual;
import main.java.dal.users.employees.Admin;
import main.java.dal.users.employees.Employee;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
	public Admin findFirstByUsernameAndPassword(String username, String password);

	
}


