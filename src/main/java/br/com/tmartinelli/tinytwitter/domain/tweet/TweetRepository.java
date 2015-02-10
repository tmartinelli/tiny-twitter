package br.com.tmartinelli.tinytwitter.domain.tweet;

import java.util.List;

import br.com.tmartinelli.tinytwitter.domain.user.User;

public interface TweetRepository {

	void add(Tweet tweet);

	List<Tweet> getTimeLineBy(User user);
}
