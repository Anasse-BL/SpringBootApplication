package com.example.sheared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	private final Random RANDOM = new SecureRandom();
	
	private final String ALPAHBET = "01234567899ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	
	
	public String generateStringId(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		
		for(int i = 0; i<length; i++) {
			
			returnValue.append(ALPAHBET.charAt(RANDOM.nextInt(ALPAHBET.length())));
			
		}
		
		return new String(returnValue);
	}
	
	
}
