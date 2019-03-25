package main.java.repositories;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.java.dal.Transaction;
import main.java.dal.accounts.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

	public Iterable<Account> findAllByApprovalStatus(boolean approvalStatus);
	public Iterable<Account> findAllByApprovalStatusAndApprovalDate(boolean approvalStatus, Date approvalDate);
	public Iterable<Account> findAllByApprovalStatusAndApprovalDateNotNull(boolean approvalStatus);
	
}
