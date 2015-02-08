package br.com.tmartinelli.tinytwitter.application.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.tmartinelli.tinytwitter.application.LoggedUser;
import br.com.tmartinelli.tinytwitter.domain.Cryptography;
import br.com.tmartinelli.tinytwitter.domain.user.User;
import br.com.tmartinelli.tinytwitter.domain.user.UserRepository;

@Controller
public class AuthenticationController {

	private LoggedUser loggedUser;
	private UserRepository userRepository;
	private Cryptography cryptography;
	private Validator validator;
	private Result result;

	public AuthenticationController() {
		this(null, null, null, null, null);
	}
	
	@Inject
	public AuthenticationController(LoggedUser loggedUser, UserRepository userRepository, Cryptography cryptography, Validator validator, Result result) {
		this.loggedUser = loggedUser;
		this.userRepository = userRepository;
		this.cryptography = cryptography;
		this.validator = validator;
		this.result = result;
	}
	
	@Get("/login")
	public void login() {

	}
	
	@Post("/login")
	public void login(User user) {
		user.encryptPassword(cryptography);
		User loadedUser = userRepository.findByEmailAndPassword(user);
		
		validator.addIf(loadedUser == null, new I18nMessage("login", "login.invalid"));
		validator.onErrorForwardTo(this).login();
		
		loggedUser.login(loadedUser);
		result.redirectTo(HomeController.class).home();
	}
	
	@Get("/logout")
	public void logout() {
		loggedUser.logout();
		result.redirectTo(this).login();
	}
}
