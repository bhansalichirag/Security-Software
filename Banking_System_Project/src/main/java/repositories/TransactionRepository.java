package main.java.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import main.java.dal.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}
