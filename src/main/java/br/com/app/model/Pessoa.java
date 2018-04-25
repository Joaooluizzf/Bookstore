package br.com.app.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pessoa")
public class Pessoa {

	public String Nome;
	public String Cpf;
	public String Email;
	public String Senha;
	public String Sexo;
	public String Data;
	public String Telefone;
	public String Endereco;

}
