package br.com.tmartinelli.tinytwitter.application.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.tmartinelli.tinytwitter.application.LoggedUser;
import br.com.tmartinelli.tinytwitter.domain.Cryptography;
import br.com.tmartinelli.tinytwitter.domain.user.User;
import br.com.tmartinelli.tinytwitter.domain.user.UserRepository;

public class AuthenticationControllerTest {
	
	private LoggedUser loggedUser;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private Cryptography cryptography;
	
	private MockValidator validator;
	private MockResult result;
	private AuthenticationController authenticationController;
	
	@Before
	public void setUp() {
		initMocks(this);
		loggedUser = new LoggedUser();
		validator = new MockValidator();
		result = spy(new MockResult());
		authenticationController = new AuthenticationController(loggedUser, userRepository, cryptography, validator, result);
	}
	
	@Test
	public void testAuthenticatedUserSuccessfully() {
		User user = new User();
		
		when(cryptography.encrypt(user.getPassword())).thenReturn("54D5CB2D332DBDB4850293CAAE4559CE88B65163F1EA5D4E4B3AC49D772DED14");
		when(userRepository.findByEmailAndPassword(user)).thenReturn(user);
		
		authenticationController.login(user);
		
		assertFalse(validator.hasErrors());
		assertTrue(loggedUser.isLogged());
		verify(result).redirectTo(HomeController.class);
	}
	
	@Test(expected = ValidationException.class)
	public void testAuthenticationFail() {
		User user = new User();
		
		when(cryptography.encrypt(user.getPassword())).thenReturn("54D5CB2D332DBDB4850293CAAE4559CE88B65163F1EA5D4E4B3AC49D772DED15");
		when(userRepository.findByEmailAndPassword(user)).thenReturn(null);
		
		authenticationController.login(user);
	}
}
