package br.com.app.service;

import org.springframework.stereotype.Service;

import br.com.app.authentication.FixedUser;
import br.com.app.authentication.SecurityHelper;
import br.com.app.cache.LoginCacheObject;

@Service
public class SecurityService {

	public String encrypt(String text) {
		return SecurityHelper.encrypt(text);
	}

	public String decrypt(String text) {
		return SecurityHelper.decrypt(text);
	}

	public String generateAdminServerToken(String user, String pass) {
		return SecurityHelper.generateAdminServerToken(user, pass);
	}

	public boolean isValidAdminServerToken(String token, FixedUser admin) {
		return SecurityHelper.isValidAdminServerToken(token, admin);
	}

	public String generateAppToken(String cnpj) {
		return SecurityHelper.generateAppToken(cnpj);
	}

	public boolean isValidAppToken(String token, String cnpj) {
		return SecurityHelper.isValidAppToken(token, cnpj);
	}

	public boolean isValidAppToken(LoginCacheObject loginCacheObject) {
		return SecurityHelper.isValidAppToken(loginCacheObject);
	}

}
