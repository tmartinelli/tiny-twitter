package br.com.tmartinelli.tinytwitter.domain.tweet;

import java.time.LocalDateTime;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.com.tmartinelli.tinytwitter.domain.user.User;

@StaticMetamodel(Tweet.class)
public class Tweet_ {

	public static volatile SingularAttribute<Tweet, Long> id;
	public static volatile SingularAttribute<Tweet, User> user;
	public static volatile SingularAttribute<Tweet, String> content;
	public static volatile SingularAttribute<Tweet, LocalDateTime> dateTime;
}
