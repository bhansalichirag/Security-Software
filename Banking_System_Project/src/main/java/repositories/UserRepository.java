package main.java.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import main.java.dal.users.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
	public Optional<User> findByUsernameAndPassword(String username, String password);
	public boolean existsByLastNameOrFirstName(String lastname, String firstname);
	
}
