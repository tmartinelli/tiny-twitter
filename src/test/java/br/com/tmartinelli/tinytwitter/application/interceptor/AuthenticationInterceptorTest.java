package br.com.tmartinelli.tinytwitter.application.interceptor;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.tmartinelli.tinytwitter.application.LoggedUser;
import br.com.tmartinelli.tinytwitter.application.controller.AuthenticationController;
import br.com.tmartinelli.tinytwitter.domain.user.User;

public class AuthenticationInterceptorTest {

	private LoggedUser loggedUser;
	private MockResult result;
	private AuthenticationInterceptor interceptor;
	
	@Mock
	private SimpleInterceptorStack stack;
	
	@Before
	public void setUp() {
		initMocks(this);
		loggedUser = new LoggedUser();
		result = spy(new MockResult());
		interceptor = new AuthenticationInterceptor(loggedUser, result);
	}

	@Test
	public void userShouldBeLogged() {
		loggedUser.login(new User());
		
		interceptor.around(stack);
		
		verify(stack).next();
	}

	@Test
	public void userShouldNotBeLogged() {
		interceptor.around(stack);
		
		verify(result).redirectTo(AuthenticationController.class);
	}
}
