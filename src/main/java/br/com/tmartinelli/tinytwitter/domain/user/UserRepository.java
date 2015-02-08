package br.com.tmartinelli.tinytwitter.domain.user;

public interface UserRepository {

	User findByEmailAndPassword(User user);
	
	void add(User user);
}
