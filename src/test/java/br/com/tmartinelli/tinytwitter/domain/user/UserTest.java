package br.com.tmartinelli.tinytwitter.domain.user;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.tmartinelli.tinytwitter.infrastructure.cryptography.Sha256;

public class UserTest {

	private User user;

	@Before
	public void setUp() {
		user = new User();
	}
	
	@Test
	public void passwordShouldBeEncryptedSuccessfully() {
		user.setPassword("asd123");
		user.encryptPassword(new Sha256());
		assertEquals("54D5CB2D332DBDB4850293CAAE4559CE88B65163F1EA5D4E4B3AC49D772DED14", user.getPassword());
	}
}
