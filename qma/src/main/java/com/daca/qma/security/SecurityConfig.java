package com.daca.qma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.daca.qma.security.jwt.JWTAuthenticationFilter;
import com.daca.qma.security.jwt.JWTAuthorizationFilter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final AutenticacaoService customUserDetailsService;
	
	@Autowired
	public SecurityConfig(AutenticacaoService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/alunos").permitAll()
			.antMatchers("/ajudas").hasRole("ALUNO")
			.antMatchers("/tutores").hasRole("TUTOR")
			.antMatchers("/avaliacoes").hasRole("ALUNO")
			
			.antMatchers("/logar").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
			.antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
			.antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
			.antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
			.antMatchers(HttpMethod.OPTIONS, "**").permitAll()
			
			.anyRequest()
				.authenticated().and()
					.addFilter(new JWTAuthenticationFilter(authenticationManager()))
					.addFilter(new JWTAuthorizationFilter(authenticationManager(),customUserDetailsService));
	    
	}
}
