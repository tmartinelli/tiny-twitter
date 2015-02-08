package br.com.tmartinelli.tinytwitter.application.controller;

import static br.com.caelum.vraptor.view.Results.*;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.tmartinelli.tinytwitter.domain.Cryptography;
import br.com.tmartinelli.tinytwitter.domain.user.User;
import br.com.tmartinelli.tinytwitter.domain.user.UserRepository;

@Controller
public class UserController {

	private UserRepository userRepository;
	private Cryptography cryptography;
	private Result result;

	public UserController() {
		this(null, null, null);
	}
	
	@Inject
	public UserController(UserRepository userRepository, Cryptography cryptography, Result result) {
		this.userRepository = userRepository;
		this.cryptography = cryptography;
		this.result = result;
	}


	@Post("/users")
	public void add(User user) {
		user.encryptPassword(cryptography);
		userRepository.add(user);
		result.use(json()).from(user).serialize();
	}
}
