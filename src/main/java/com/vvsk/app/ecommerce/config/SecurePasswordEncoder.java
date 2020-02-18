package com.vvsk.app.ecommerce.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurePasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		/*
		 * rawPassword.chars().forEach(character -> { character++; });
		 */
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		/*
		 * rawPassword.chars().forEach(character -> { character++; });
		 */
		return rawPassword.toString().equals(encodedPassword);
	}

}
