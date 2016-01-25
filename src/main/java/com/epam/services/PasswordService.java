package com.epam.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
@Service
public class PasswordService {

	public PasswordService() {
	}

	public String encrypt(String plaintext) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("There is no such algorithm.");
		}
		try {
			md.update(plaintext.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println("UTF-8 is not supported.");
		}
		byte[] raw = md.digest();
		String hash = (new BASE64Encoder()).encode(raw);
		return hash;
	}
}
