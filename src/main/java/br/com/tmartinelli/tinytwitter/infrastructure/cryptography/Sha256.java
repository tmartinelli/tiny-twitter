package br.com.tmartinelli.tinytwitter.infrastructure.cryptography;

import java.security.MessageDigest;

import javax.enterprise.context.RequestScoped;

import br.com.tmartinelli.tinytwitter.domain.Cryptography;

@RequestScoped
public class Sha256 implements Cryptography {
	
	@Override
	public String encrypt(String text) {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(text.getBytes("UTF-8"));
			 
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
			  hexString.append(String.format("%02X", 0xFF & b));
			}
			return hexString.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
