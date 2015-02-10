package br.com.tmartinelli.tinytwitter.application.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.tmartinelli.tinytwitter.application.LoggedUser;
import br.com.tmartinelli.tinytwitter.application.Restrict;
import br.com.tmartinelli.tinytwitter.domain.user.User;
import br.com.tmartinelli.tinytwitter.domain.user.UserRepository;

@Controller
public class HomeController {

	private LoggedUser loggedUser;
	private UserRepository userRepository;
	private Result result;
	
	public HomeController() {
		this(null, null, null);	
	}
	
	@Inject
	public HomeController(LoggedUser loggedUser, UserRepository userRepository, Result result) {
		this.loggedUser = loggedUser;
		this.userRepository = userRepository;
		this.result = result;
	}

	@Get("/")
	@Restrict
	public void home() {
		User user = userRepository.findById(loggedUser.getId());
		result.include("user", user);
	}
}
