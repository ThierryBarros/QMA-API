package com.daca.qma.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.daca.qma.exceptions.FailedAuthenticationException;
import com.daca.qma.security.AutenticacaoController;
import com.daca.qma.security.AutenticacaoService;
import com.daca.qma.security.UserCredentials;

/**
 *
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class AuthenticationControllerTest {
	private final String ENDPOINT = "/logar";
	
	private User testUser;

	private UserCredentials testCredentials;
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Mock
	private AutenticacaoService authService;
	
	@InjectMocks
	private AutenticacaoController authController;

	/**
	 * 
	 */
	@BeforeAll
	void setUpAll() {
		this.mockMvc = standaloneSetup(authController).build();
	}

	@BeforeEach
	void setUp() {
		Set<GrantedAuthority> authorities  = new HashSet<GrantedAuthority>();
		
		this.testUser = new User("user", "password", authorities);
		
		this.testCredentials = new UserCredentials("thierry", "senha");
		
		
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	void testLoginSucessfully() throws Exception {
		when(authService.authenticate(testCredentials))
		.thenReturn(testUser);
		when(authService.tokenFor(testUser))
		.thenReturn(anyString());
		
		mockMvc.perform(post(ENDPOINT)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsString(testCredentials)))
				.andExpect(status().isOk());
	}
	
	@Test
	void testLoginSucessFailed() throws Exception {
		when(authService.authenticate(any()))
		.thenThrow(FailedAuthenticationException.class);
		
		mockMvc.perform(post(ENDPOINT)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsString(testCredentials)))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

}
