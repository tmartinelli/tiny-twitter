package br.com.tmartinelli.tinytwitter.domain.user;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.tmartinelli.tinytwitter.domain.BaseEntity;
import br.com.tmartinelli.tinytwitter.domain.Cryptography;
import br.com.tmartinelli.tinytwitter.domain.tweet.Tweet;
import br.com.tmartinelli.tinytwitter.domain.tweet.TweetRepository;

@Entity
@Table(name = "user")
public class User extends BaseEntity{

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;
	
	@ManyToMany
	@JoinTable(name = "follow",
	            joinColumns = { @JoinColumn(name = "follower_id")}, 
	            inverseJoinColumns={@JoinColumn(name="following_id")})
	private Set<User> following;
	
	@ManyToMany(mappedBy="following")
	private Set<User> followers;
	
	@Column(nullable = false)
	private String avatar;
	
	@Transient
	private TweetRepository tweetRepository;

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
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public void encryptPassword(Cryptography cryptography) {
		password = cryptography.encrypt(password);
	}
	
	public List<Tweet> getTweets() {
		return tweetRepository.findBy(this);
	}
	
	public boolean isFollowedBy(User user) {
		return followers.contains(user);
	}
	
	public void follow(User user) {
		following.add(user);
	}
	
	public void unfollow(User user) {
		following.remove(user);
	}
	
	//TODO: search method to inject components
	public void setTweetRepository(TweetRepository tweetRepository) {
		this.tweetRepository = tweetRepository;
	}
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
}
