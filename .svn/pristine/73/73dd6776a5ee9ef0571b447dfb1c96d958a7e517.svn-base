package br.com.app.authentication;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AuthenticationToken implements Authentication {

	private static final long serialVersionUID = 1228958168403689073L;

	private final AccountTokenAuth accountTokenAuth;

	public AuthenticationToken(AccountTokenAuth accountTokenAuth) {
		this.accountTokenAuth = accountTokenAuth;
	}

	@Override
	public String getName() {
		return accountTokenAuth.getUser();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.<GrantedAuthority> emptyList();
	}

	@Override
	public Object getCredentials() {
		return accountTokenAuth;
	}

	@Override
	public Object getDetails() {
		return accountTokenAuth.getToken();
	}

	@Override
	public Object getPrincipal() {
		return accountTokenAuth.getUser();
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

	}

}
