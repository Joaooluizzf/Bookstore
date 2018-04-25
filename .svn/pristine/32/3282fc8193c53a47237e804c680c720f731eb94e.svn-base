package br.com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.app.model.Pessoa;
import br.com.app.repository.PessoaRepository;

@Service
public class PessoaService {

	Gson gson = new Gson();

	@Autowired
	PessoaRepository pessoaRepository;

	public void save(String pessoa) {
		Pessoa pessoaObj = gson.fromJson(pessoa, Pessoa.class);
		pessoaRepository.save(pessoaObj);
	}

	public void delete(String pessoa) {
		Pessoa pessoaObj = gson.fromJson(pessoa, Pessoa.class);
		pessoaRepository.delete(pessoaObj);
	}

	public String findAll() {
		return gson.toJson(pessoaRepository.findAll());
	}

}