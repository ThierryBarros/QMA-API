package com.daca.qma.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AuthenticatedUser implements Authentication {

	private static final long serialVersionUID = 1L;
	private String token;
	private User user;
	
	
	
	public AuthenticatedUser(String token, User user2) {
		super();
		this.token = token;
		this.user = user2;
	}

	public AuthenticatedUser(User user) {
		this.user = user;
	}
	
	public AuthenticatedUser(String token) {
		this.token = token;
	}
	
	@Override
	public String getName() {
		return null;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	public Object getCredentials() {
		return this.token;
	}
	
	@Override
	public Object getDetails() {
		return null;
	}
	
	@Override
	public Object getPrincipal() {
		return null;
	}
	
	@Override
	public boolean isAuthenticated() {
		return this.user != null;
	}
	
	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {}
}
