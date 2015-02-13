package br.com.tmartinelli.tinytwitter.infrastructure.jpa.listener;

import javax.enterprise.inject.spi.CDI;
import javax.persistence.PostLoad;

import br.com.tmartinelli.tinytwitter.domain.tweet.TweetRepository;
import br.com.tmartinelli.tinytwitter.domain.user.User;

public class UserListener {

	@PostLoad
	public void postLoad(Object obj) {
		if (obj instanceof User) {
			TweetRepository tweetRepository = CDI.current().select(TweetRepository.class).get();
			User user = (User) obj;
			user.setTweetRepository(tweetRepository);
		}
	}
}
