package main.java.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.java.dal.users.User;
import main.java.dal.users.employees.Employee;


	
	@Repository
	public interface EmployeeRepository extends CrudRepository<Employee, String> {
		Employee findByusername(String username);
	}
	

