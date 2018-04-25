package br.com.app.rest;

import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.authentication.SecurityHelper;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginRest {

	@RequestMapping(method = { RequestMethod.GET }, produces = MediaType.TEXT_PLAIN_VALUE)
	public String select() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return SecurityHelper.generateAppToken(name);
	}

}
