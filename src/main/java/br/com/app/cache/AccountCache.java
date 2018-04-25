package br.com.app.cache;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.LRUMap;

import br.com.app.model.Account;

@Service
public class AccountCache {

	private final LRUMap<String, Account> cache;

	public AccountCache() {
		cache = new com.fasterxml.jackson.databind.util.LRUMap<>(16, 64);
	}

	public Account getAccount(String user) {
		return cache.get(user);
	}

	public void putAccount(String user, Account account) {
		cache.put(user, account);
	}

	public void removeAccount(String user) {
		cache.put(user, null);
	}

}
