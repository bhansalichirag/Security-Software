package main.java.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.java.dal.accounts.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

}
