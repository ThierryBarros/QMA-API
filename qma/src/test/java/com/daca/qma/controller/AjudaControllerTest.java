package com.daca.qma.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.daca.qma.ajuda.Ajuda;
import com.daca.qma.ajuda.AjudaRepository;
import com.daca.qma.ajuda.AjudaService;
import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class AjudaControllerTest {
	
	private MockMvc mvc;
	@Mock
    private AjudaService service;
	
	@Mock
    private AjudaRepository repository;
	
	@InjectMocks
	private AjudaControllerTest controller;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private Ajuda ajuda;

	/**
	 * 
	 */
	@BeforeAll
	void setUpAll() {
		this.mvc = standaloneSetup(controller).build();
	}
	
	@BeforeEach
	void setUp() {

		this.ajuda = new Ajuda("Matemática", "UFCG");
	}

	/**
	 * @throws Exception 
	 */
	@Test
	void ListarAjudas() throws Exception {
		
		mvc.perform(get("/ajudas"))
		.andExpect(status().isNotFound())
		.andExpect(content().string(""));
	}
	
	
	@Test
	void ListarAjudarPorId() throws Exception {
		
		mvc.perform(get("/ajudas/1")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsString(ajuda)))
				.andExpect(status().isNotFound())
				.andExpect(content().string(""));
	}
	
	@Test
	void DeletarAjuda() throws Exception {
		
		mvc.perform(delete("/ajudas/1")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsString(ajuda)))
				.andExpect(status().isNotFound())
				.andExpect(content().string(""));
		
	}
	
	@Test
	void CadastrarAjuda() throws Exception {
		
		mvc.perform(post("/ajudas")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsString(ajuda)))
				.andExpect(status().isNotFound())
				.andExpect(content().string(""));
	}


}
