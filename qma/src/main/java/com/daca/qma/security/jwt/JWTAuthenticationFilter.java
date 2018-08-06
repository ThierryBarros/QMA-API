package com.daca.qma.security.jwt;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.daca.qma.aluno.Aluno;
import com.daca.qma.aluno.AlunoService;
import com.daca.qma.security.SecurityConstants;
import com.daca.qma.security.UserCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AlunoService alunoservice;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		
		try {
			UserCredentials aluno = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(aluno.getUsername(), aluno.getPassword()));
			
		} catch (IOException e) {
			throw new RuntimeException("Autenficação Falhou!");
		}
	}
	

	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		ZonedDateTime expirantionTimeUTC = ZonedDateTime.now(ZoneOffset.UTC).plus(SecurityConstants.EXPIRATION_TIME,ChronoUnit.MILLIS);
		
		String token = Jwts.builder().setSubject(((User)authResult.getPrincipal()).getUsername())
				.setExpiration(Date.from(expirantionTimeUTC.toInstant()))
				.signWith(SignatureAlgorithm.HS256,SecurityConstants.SECRET)
				.compact();
		response.getWriter().write(token);
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
	}
}

