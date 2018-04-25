package br.com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.authentication.AccountBasicAuth;
import br.com.app.authentication.AccountTokenAuth;
import br.com.app.authentication.SecurityHelper;
import br.com.app.cache.AccountCache;
import br.com.app.cache.LoginCacheObject;
import br.com.app.model.Account;
import br.com.app.repository.AccountRepository;


@Service
public class AppService {

	@Autowired
	AccountCache accountCache;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	SecurityService securityService;

	public AccountBasicAuth doLogin(AccountBasicAuth auth) {
		Account account = accountCache.getAccount(auth.getUser());
		if (account != null) {
			if (account.getPassword().equals(auth.getPassword())) {
				return new AccountBasicAuth(auth.getUser(), auth.getPassword());
			}
		}

		if (account == null) {
			account = accountRepository.findOne(auth.getUser());
			if (account != null && account.getPassword().equals(auth.getPassword())) {
				accountCache.putAccount(auth.getUser(), account);
				return new AccountBasicAuth(auth.getUser(), auth.getPassword());
			}
		}

		return null;
	}

	public AccountTokenAuth doLoginByToken(String user, String token) {
		LoginCacheObject loginCacheObject = new LoginCacheObject(token, SecurityHelper.getTokenValidity(token));
		if (loginCacheObject != null) {
			if (loginCacheObject.getToken().equals(token) && securityService.isValidAppToken(loginCacheObject)) {
				return new AccountTokenAuth(user, token);
			}
		}
		return null;
	}

}
