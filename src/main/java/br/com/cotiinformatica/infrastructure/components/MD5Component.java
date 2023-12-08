package br.com.cotiinformatica.infrastructure.components;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class MD5Component {
	
	public String encrypt(String input) {
		String md5 = null;
		if (null == input)
			return null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			
			digest.update(input.getBytes(), 0, input.length());
			
			md5 = new BigInteger(1, digest.digest()).toString(16);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}
}