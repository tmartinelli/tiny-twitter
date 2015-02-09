package br.com.tmartinelli.tinytwitter.application.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.tmartinelli.tinytwitter.application.LoggedUser;
import br.com.tmartinelli.tinytwitter.application.Restrict;
import br.com.tmartinelli.tinytwitter.domain.tweet.Tweet;
import br.com.tmartinelli.tinytwitter.domain.tweet.TweetRepository;

@Controller
public class TweetController {

	private TweetRepository tweetRepository;
	private LoggedUser loggedUser;
	private Result result;
	private Validator validator;
	
	public TweetController() {
		this(null, null, null, null);
	}
	
	@Inject
	public TweetController(TweetRepository tweetRepository, LoggedUser loggedUser, Result result, Validator validator) {
		this.tweetRepository = tweetRepository;
		this.loggedUser = loggedUser;
		this.result = result;
		this.validator = validator;
	}

	@Post("/tweets")
	@Restrict
	public void add(String content) {
		Tweet tweet = new Tweet(loggedUser.getUser(), content);
		
		validator.validate(tweet);
		validator.onErrorForwardTo(HomeController.class).home();
		
		tweetRepository.add(tweet);

		result.include("message", "tweet.sent_successfully");
		result.redirectTo(HomeController.class).home();
	}
}
