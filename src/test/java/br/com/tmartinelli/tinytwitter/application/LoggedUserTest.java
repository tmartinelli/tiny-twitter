package br.com.tmartinelli.tinytwitter.application;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.tmartinelli.tinytwitter.domain.user.User;

import br.com.tmartinelli.tinytwitter.application.LoggedUser;

public class LoggedUserTest {

	private LoggedUser loggedUser;

	@Before
	public void setUp() {
		loggedUser = new LoggedUser();
	}
	
	@Test
	public void userShouldBeLogged() {
		loggedUser.login(new User());
		assertTrue(loggedUser.isLogged());
	}

	@Test
	public void userShouldNotBeLogged() {
		assertFalse(loggedUser.isLogged());
	}
	
	@Test
	public void userShouldNotBeLoggedWhenLogout() {
		loggedUser.login(new User());
		loggedUser.logout();
		assertFalse(loggedUser.isLogged());
	}
}
