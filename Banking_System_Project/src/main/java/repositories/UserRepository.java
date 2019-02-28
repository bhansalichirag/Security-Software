package main.java.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import main.java.dal.users.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
	public Iterable<User> findByUsernameAndPassword(String username, String password);
	
}
