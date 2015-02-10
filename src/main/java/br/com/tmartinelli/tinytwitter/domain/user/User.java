package br.com.tmartinelli.tinytwitter.domain.user;


import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.tmartinelli.tinytwitter.domain.Cryptography;
import br.com.tmartinelli.tinytwitter.domain.tweet.Tweet;
import br.com.tmartinelli.tinytwitter.domain.tweet.TweetRepository;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "follow",
	            joinColumns = { @JoinColumn(name = "following_id")}, 
	            inverseJoinColumns={@JoinColumn(name="follower_id")})
	private Set<User> following;
	
	@ManyToMany(mappedBy="following")
	private Set<User> followers;

	@Transient
	private TweetRepository tweetRepository;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void encryptPassword(Cryptography cryptography) {
		password = cryptography.encrypt(password);
	}
	
	public List<Tweet> getTimeLine() {
		return tweetRepository.getTimeLineBy(this);
	}
	
	//TODO: search method to inject components
	public void setTweetRepository(TweetRepository tweetRepository) {
		this.tweetRepository = tweetRepository;
	}
}
