package br.com.tmartinelli.tinytwitter.application.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.tmartinelli.tinytwitter.application.LoggedUser;
import br.com.tmartinelli.tinytwitter.application.Restrict;
import br.com.tmartinelli.tinytwitter.domain.Cryptography;
import br.com.tmartinelli.tinytwitter.domain.user.User;
import br.com.tmartinelli.tinytwitter.domain.user.UserRepository;

@Controller
public class UserController {

	private UserRepository userRepository;
	private Cryptography cryptography;
	private Result result;
	private LoggedUser loggedUser;

	public UserController() {
		this(null, null, null, null);
	}
	
	@Inject
	public UserController(UserRepository userRepository, Cryptography cryptography, Result result, LoggedUser loggedUser) {
		this.userRepository = userRepository;
		this.cryptography = cryptography;
		this.result = result;
		this.loggedUser = loggedUser;
	}
	
	@Get("/users")
	@Restrict
	public void search(String name) {
		List<User> users = userRepository.findByName(name);
		result.use(json()).from(users, "users").serialize();
	}

	@Get("/users/{id}")
	@Restrict
	public void profile(Long id) {
		User user = userRepository.findFullById(id);
		result.include("user", user);
	}
	
	@Post("/following")
	@Restrict
	public void addFollowing(User user) {
		User follower = userRepository.findById(loggedUser.getId());
		follower.follow(user);
		result.redirectTo(this).profile(user.getId());
	}
	
	@Delete("/following/{user.id}")
	@Restrict
	public void removeFollowing(User user) {
		User follower = userRepository.findById(loggedUser.getId());
		follower.unfollow(user);
		result.redirectTo(this).profile(user.getId());
	}
	
	//TODO: Method to add users until creating sign up
	@Post("/users")
	public void add(User user) {
		user.encryptPassword(cryptography);
		userRepository.add(user);
		result.use(json()).from(user).serialize();
	}
}
