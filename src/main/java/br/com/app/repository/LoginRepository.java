package br.com.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.app.model.Login;

public interface LoginRepository extends MongoRepository<Login, String> {

	Login findByUsername(String username);

	Login findByUsernameAndPassword(String username, String password);

}
