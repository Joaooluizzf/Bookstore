package br.com.app.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	public CustomAuthenticationProvider() {
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		AuthenticationToken auth = (AuthenticationToken) authentication;
		return auth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return AuthenticationToken.class.isAssignableFrom(authentication);
	}

}
