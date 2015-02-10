package br.com.tmartinelli.tinytwitter.infrastructure.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import br.com.tmartinelli.tinytwitter.domain.tweet.Tweet;
import br.com.tmartinelli.tinytwitter.domain.tweet.TweetRepository;
import br.com.tmartinelli.tinytwitter.domain.tweet.Tweet_;
import br.com.tmartinelli.tinytwitter.domain.user.User;
import br.com.tmartinelli.tinytwitter.domain.user.User_;

@RequestScoped
public class TweetDAO extends GenericDAO<Tweet, Long> implements TweetRepository {

	@Inject
	public TweetDAO(EntityManager entityManager) {
		super(entityManager, Tweet.class);
	}

	@Override
	public List<Tweet> getTimeLineBy(User user) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tweet> query = builder.createQuery(Tweet.class);
		Root<Tweet> root = query.from(Tweet.class);
		
		SetJoin<User, User> followingJoin = root.join(Tweet_.user).join(User_.following);
		
		query.where(builder.equal(followingJoin.get(User_.id), user.getId()));
		query.orderBy(builder.desc(root.get(Tweet_.dateTime)));
		
		return entityManager.createQuery(query).getResultList();
	}
}