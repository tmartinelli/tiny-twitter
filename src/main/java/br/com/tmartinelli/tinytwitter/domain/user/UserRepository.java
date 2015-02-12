package br.com.tmartinelli.tinytwitter.domain.user;

import java.util.List;

public interface UserRepository {

	User findByEmailAndPassword(User user);
	
	void add(User user);

	User findById(Long id);

	List<User> findByName(String name);

	User findFullById(Long id);
}
