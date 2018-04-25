package br.com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.app.model.Livro;
import br.com.app.repository.LivroRepository;

@Service
public class LivroService {

	Gson gson = new Gson();

	@Autowired
	LivroRepository livroRepository;

	public void save(String livro) {
		Livro livroObj = gson.fromJson(livro, Livro.class);
		livroRepository.save(livroObj);
	}

	public void delete(String livro) {
		Livro livroObj = gson.fromJson(livro, Livro.class);
		livroRepository.delete(livroObj);
	}

	public String findAll() {
		return gson.toJson(livroRepository.findAll());
	}

}