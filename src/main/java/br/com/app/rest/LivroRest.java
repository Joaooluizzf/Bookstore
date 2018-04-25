package br.com.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.service.LivroService;

@RestController
@CrossOrigin
@RequestMapping("/livro")
public class LivroRest {

	@Autowired
	LivroService livroService;

	@RequestMapping(method = { RequestMethod.POST }, path = "/save")
	public HttpStatus add(@RequestBody String livro) {
		livroService.save(livro);
		return HttpStatus.OK;
	}

	@RequestMapping(method = { RequestMethod.POST }, path = "/delete")
	public HttpStatus del(@RequestBody String livro) {
		livroService.delete(livro);
		return HttpStatus.OK;
	}

	@RequestMapping(method = { RequestMethod.GET }, path = "/findAll")
	public String findAll() {
		return livroService.findAll();
	}

}
