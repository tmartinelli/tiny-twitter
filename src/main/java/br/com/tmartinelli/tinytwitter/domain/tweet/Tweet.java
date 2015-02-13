package br.com.tmartinelli.tinytwitter.domain.tweet;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.tmartinelli.tinytwitter.domain.BaseEntity;
import br.com.tmartinelli.tinytwitter.domain.user.User;

@Entity
@Table(name = "tweet")
public class Tweet extends BaseEntity {

	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;
	
	@Column(nullable = false, length = 140)
	@NotNull(message = "{tweet.content.empty}")
	@Size(min = 1, max = 140, message = "{tweet.content.length}")
	private String content;
	
	@Column(name = "date_time", nullable = false)
	private LocalDateTime dateTime;

	public Tweet() {
		this(null, null);
	}
	
	public Tweet(User user, String content) {
		this.user = user;
		this.content = content;
		dateTime = LocalDateTime.now();
	}
	
	public User getUser() {
		return user;
	}
	
	public String getContent() {
		return content;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}
}
