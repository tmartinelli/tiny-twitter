package br.com.tmartinelli.tinytwitter.application.controller;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.tmartinelli.tinytwitter.application.LoggedUser;
import br.com.tmartinelli.tinytwitter.application.Restrict;
import br.com.tmartinelli.tinytwitter.domain.tweet.Tweet;
import br.com.tmartinelli.tinytwitter.domain.tweet.TweetRepository;

@Controller
public class HomeController {

	private LoggedUser loggedUser;
	private TweetRepository tweetRepository;
	private Result result;
	
	public HomeController() {
		this(null, null, null);	
	}
	
	@Inject
	public HomeController(LoggedUser loggedUser, TweetRepository tweetRepository, Result result) {
		this.loggedUser = loggedUser;
		this.tweetRepository = tweetRepository;
		this.result = result;
	}

	@Get("/")
	@Restrict
	public void home() {
		List<Tweet> timeline = tweetRepository.getTimelineBy(loggedUser.getUser());
		result.include("timeline", timeline);
	}
}
