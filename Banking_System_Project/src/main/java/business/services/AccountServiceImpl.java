package main.java.business.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.dal.Transaction;
import main.java.dal.accounts.Account;
import main.java.dal.users.employees.Employee;
import main.java.repositories.AccountRepository;
import main.java.repositories.TransactionRepository;
import main.java.repositories.UserRepository;

@Service
public class AccountServiceImpl implements IAccountServices {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private ITransactionServices transactionService;
	
	@Override
	public boolean MakePayment(int payerAccount, int recipientAccount, double amount)
	{
		Optional<Account> payerWrapper = accountRepository.findById(payerAccount);
		Optional<Account> payeeWrapper = accountRepository.findById(recipientAccount);
		if(payerWrapper.isPresent() && payeeWrapper.isPresent())
		{
			Account payer = payerWrapper.get();
			Account payee = payeeWrapper.get();
			Transaction transaction = new Transaction(payer, payee, amount);
			transactionRepository.save(transaction);
			if(amount < 1000.0)
			{
				transaction = transactionService.ApproveTransaction((Employee) userRepository.findById("sysadmin").get(), transaction);
			}
			if(transaction != null)
			{
				payer.addTransaction(transaction);
				payee.addTransaction(transaction);
				accountRepository.save(payer);
				accountRepository.save(payee);
				return true;
			}
		}
		return false;
	}
	
	
}
