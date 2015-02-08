package br.com.tmartinelli.tinytwitter.infrastructure.cryptography;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class Sha256Test {

	private Sha256 sha256;
	
	@Before
	public void setUp() {
		sha256 = new Sha256();
	}

	@Test
	public void testValidEncryptedText() {
		String encryptedText = sha256.encrypt("asd123");
		assertEquals("54D5CB2D332DBDB4850293CAAE4559CE88B65163F1EA5D4E4B3AC49D772DED14", encryptedText);
	}
}