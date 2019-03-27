package main.java.business.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.dal.Transaction;
import main.java.dal.users.employees.Employee;
import main.java.repositories.AccountRepository;
import main.java.repositories.TransactionRepository;

@Service
public class TransactionServicesImpl implements ITransactionServices{

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private AccountRepository accountRepository;
	
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
			transactionRepository.save(transaction);
			transaction.getPayee().creditAmount(transaction.getAmount());
			transaction.getPayer().debitAmount(transaction.getAmount());
			accountRepository.save(transaction.getPayee());
			accountRepository.save(transaction.getPayer());
			return transaction;
		}
		catch (Exception exception)
		{
			
		}
		return null;
	}
	
	@Override
	public Transaction DeclineTransaction(Employee approver, Transaction transaction) 
	{
		try 
		{
			transaction.setApprovalDate(new Date());
			transaction.setApprovalStatus(false);
			transaction.setApprover(approver);
			if(transactionRepository.save(transaction) != null)
			{
				return transaction;
			}
		} 
		catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public List<Boolean> DeclineTransactions(Employee approver, List<Transaction> transactions) 
	{
		List<Boolean> results = new ArrayList<Boolean>();
		for( Transaction transaction : transactions)
		{
			boolean status = false;
			if(this.DeclineTransaction(approver, transaction) != null)
			{
				status = true;
			}
			results.add(status);
		}
		return results;
	}

	@Override
	public List<Boolean> ApproveTransactions(Employee approver, List<Transaction> transactions) 
	{
		List<Boolean> results = new ArrayList<Boolean>();
		for( Transaction transaction : transactions)
		{
			boolean status = false;
			if(this.ApproveTransaction(approver, transaction) != null)
			{
				status = true;
			}
			results.add(status);
		}
		return results;
	}

	@Override
	public List<Transaction> GetAllPendingTransactions() {
		
		List<Transaction> transactionList = new ArrayList<Transaction>();
		Iterable<Transaction> transactions = transactionRepository.findAllByApprovalStatusAndApprovalDate(false, null);
		for ( Transaction item : transactions) 
		{
			transactionList.add(item);
		}
		return transactionList;
	}
	
}
