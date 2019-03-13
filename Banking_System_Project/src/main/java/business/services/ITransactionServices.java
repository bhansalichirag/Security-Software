package main.java.business.services;

import main.java.dal.Transaction;
import main.java.dal.users.employees.Employee;

public interface ITransactionServices {

	Transaction ApproveTransaction(Employee approver, Transaction transaction);

}
