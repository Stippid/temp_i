package com.controller.mkyong;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {
	public static void main(String[] args) {
		String password = "123Bisag#";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		
		
		if(!passwordEncoder.matches("123Bisag#", hashedPassword)){
		}else{
		}
	}
	

}