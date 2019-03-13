package main.java.business.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.dal.Transaction;
import main.java.dal.users.employees.Employee;
import main.java.repositories.TransactionRepository;

@Service
public class TransactionServicesImpl implements ITransactionServices{

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public Transaction ApproveTransaction(Employee approver, Transaction transaction)
	{
		transaction.setApprovalDate(new Date());
		transaction.setApprovalStatus(true);
		transaction.setApprover(approver);
		try
		{
			if(transaction.getAmount() > transaction.getPayer().getBalance())
			{
				return null;
			}
			transaction.getPayee().creditAmount(transaction.getAmount());
			transaction.getPayer().debitAmount(transaction.getAmount());
			transactionRepository.save(transaction);
			return transaction;
		}
		catch (Exception exception)
		{
			
		}
		return null;
	}
}
