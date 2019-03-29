package main.java.business.services;

import java.util.List;

import main.java.dal.Transaction;
import main.java.dal.users.employees.Employee;

public interface ITransactionServices {

	Transaction ApproveTransaction(Employee approver, Transaction transaction);
	Transaction DeclineTransaction(Employee approver, Transaction transaction);
	List<Boolean> DeclineTransactions(Employee approver, List<Transaction> transactions);
	List<Boolean> ApproveTransactions(Employee approver, List<Transaction> transactions);
	List<Transaction> GetAllPendingTransactions();
	Transaction GetTransaction(int transactionid);

}
