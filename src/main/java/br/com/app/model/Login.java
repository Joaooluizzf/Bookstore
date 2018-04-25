package br.com.app.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "login")
public class Login {

	public String username;
	public String password;
	public String email;

}
