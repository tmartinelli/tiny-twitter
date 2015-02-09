package br.com.tmartinelli.tinytwitter.infrastructure.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.tmartinelli.tinytwitter.domain.tweet.Tweet;
import br.com.tmartinelli.tinytwitter.domain.tweet.TweetRepository;

@RequestScoped
public class TweetDAO extends GenericDAO<Tweet, Long> implements TweetRepository {

	@Inject
	public TweetDAO(EntityManager entityManager) {
		super(entityManager, Tweet.class);
	}
}
