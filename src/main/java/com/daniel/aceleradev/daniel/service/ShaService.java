package com.daniel.aceleradev.daniel.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class ShaService {

	public String transform(String text) throws NoSuchAlgorithmException {
		MessageDigest mass = MessageDigest.getInstance("SHA-1");
		byte[] messageDigest = mass.digest(text.getBytes());
		BigInteger big = new BigInteger(1, messageDigest);
		String text1 = big.toString(16);

		while (text1.length() < 32) {
			text1 = "0" + text1;
		}
		return text1;
	}
}
