package br.com.tmartinelli.tinytwitter.application.interceptor;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.tmartinelli.tinytwitter.application.LoggedUser;
import br.com.tmartinelli.tinytwitter.application.controller.AuthenticationController;
import br.com.tmartinelli.tinytwitter.domain.user.User;

public class AuthenticationInterceptorTest {

	private LoggedUser loggedUser;
	private MockResult result;
	private AuthenticationInterceptor interceptor;
	
	@Before
	public void setUp() {
		loggedUser = new LoggedUser();
		result = spy(new MockResult());
		interceptor = new AuthenticationInterceptor(loggedUser, result);
	}

	@Test
	public void testUserIsLogged() {
		loggedUser.login(new User());
		
		interceptor.before();
		verify(result, never()).redirectTo(AuthenticationController.class);
	}

	@Test
	public void testUserIsNotLogged() {
		interceptor.before();
		verify(result).redirectTo(AuthenticationController.class);
	}
}
