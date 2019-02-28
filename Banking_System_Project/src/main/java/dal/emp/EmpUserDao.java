package main.java.dal.emp;

import org.springframework.stereotype.Repository;

import main.java.dal.users.employees.Admin;



public interface EmpUserDao {
	public  Admin checkLogin(String username, String password)throws Exception;
}
