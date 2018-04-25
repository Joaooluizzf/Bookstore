package br.com.app.authentication;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AuthenticationBasic implements Authentication {

	private static final long serialVersionUID = 1228958168403689073L;

	private final AccountBasicAuth accountBasicAuth;

	public AuthenticationBasic(AccountBasicAuth accountBasicAuth) {
		this.accountBasicAuth = accountBasicAuth;
	}

	@Override
	public String getName() {
		return accountBasicAuth.getUser();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.<GrantedAuthority> emptyList();
	}

	@Override
	public Object getCredentials() {
		return accountBasicAuth;
	}

	@Override
	public Object getDetails() {
		return accountBasicAuth.getPassword();
	}

	@Override
	public Object getPrincipal() {
		return accountBasicAuth.getUser();
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

	}

}
