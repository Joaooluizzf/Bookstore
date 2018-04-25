package br.com.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.service.PessoaService;

@RestController
@CrossOrigin
@RequestMapping("/pessoa")
public class PessaRest {

	@Autowired
	PessoaService pessoaService;

	@RequestMapping(method = { RequestMethod.POST }, path = "/save")
	public HttpStatus add(@RequestBody String pessoa) {
		pessoaService.save(pessoa);
		return HttpStatus.OK;
	}

	@RequestMapping(method = { RequestMethod.POST }, path = "/delete")
	public HttpStatus del(@RequestBody String pessoa) {
		pessoaService.delete(pessoa);
		return HttpStatus.OK;
	}

	@RequestMapping(method = { RequestMethod.GET }, path = "/findAll")
	public String findAll() {
		return pessoaService.findAll();
	}

}
