package br.com.tmartinelli.tinytwitter.application;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.tmartinelli.tinytwitter.domain.user.User;

@SessionScoped
@Named("loggedUser")
public class LoggedUser implements Serializable {

	private static final long serialVersionUID = 1645544273668095544L;

	private User user;
	
	public void login(User user) {
		this.user = user;
	}
	
	public Long getId() {
		return user != null ?  user.getId() : null;
	}
	
	public String getName() {
		return user != null ? user.getName() : null;
	}
	
	public User getUser() {
		return user;
	}
	
	public boolean isLogged() {
		return user != null;
	}

	public void logout() {
		user = null;
	}
}
