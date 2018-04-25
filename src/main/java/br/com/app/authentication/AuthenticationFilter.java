package br.com.app.authentication;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;

import br.com.app.service.AppService;

public class AuthenticationFilter extends OncePerRequestFilter {

	private final static Logger LOG = Logger.getLogger(AuthenticationFilter.class);

	private static final String AUTH_KEY = "Authorization";

	private final AppService appService;

	public AuthenticationFilter(AppService service) {
		this.appService = service;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (HttpMethod.OPTIONS.matches(request.getMethod())) {
			filterChain.doFilter(request, response);
			return;
		}
		
		SecurityContext context = SecurityContextHolder.getContext();
		String authorization = request.getHeader(AUTH_KEY);

		if (authorization == null) {
			LOG.info("No credentials found.");
			response.sendError(401);
			return;
		}

		if (authorization.startsWith("Basic")) {
			// new
			// String(Base64.encodeBase64("communicator-account:htERk9Ns6CzM2s".getBytes()));

			byte[] decodeBase64 = Base64.decodeBase64(authorization.replace("Basic ", ""));
			String string = new String(decodeBase64, "UTF-8");
			String[] authorizationInfo = string.split(":");
			AccountBasicAuth loggin = appService
					.doLogin(new AccountBasicAuth(authorizationInfo[0], authorizationInfo[1]));

			if (loggin == null) {
				LOG.info("Invalid login.");
				response.sendError(401);
				return;
			}

			LOG.debug("Setting authentication on session.");
			Authentication authentication = new AuthenticationBasic(loggin);
			context.setAuthentication(authentication);
			filterChain.doFilter(request, response);

		} else if (authorization.startsWith("Token ")) {
			Gson gson = new Gson();
			AccountTokenAuth auth = gson.fromJson(authorization.substring(6), AccountTokenAuth.class);
			String user = auth.getUser();
			AccountTokenAuth loggin = appService.doLoginByToken(user, auth.getToken());

			if (loggin == null) {
				LOG.info("Invalid login.");
				response.sendError(401);
				return;
			}

			LOG.debug("Setting authentication on session.");
			Authentication authentication = new AuthenticationToken(loggin);
			context.setAuthentication(authentication);
			filterChain.doFilter(request, response);

		}

	}

}
