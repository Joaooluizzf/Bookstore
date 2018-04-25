package br.com.app.cache;

import java.io.Serializable;

public class LoginCacheObject implements Serializable {

	private static final long serialVersionUID = 6802719682517875154L;

	private final String token;
	private final long tokenValidity;

	public LoginCacheObject(String token, long tokenValidity) {
		this.token = token;
		this.tokenValidity = tokenValidity;
	}

	public String getToken() {
		return token;
	}

	public long getTokenValidity() {
		return tokenValidity;
	}

}
