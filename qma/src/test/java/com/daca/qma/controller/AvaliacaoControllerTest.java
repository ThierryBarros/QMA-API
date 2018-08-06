package com.daca.qma.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;

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

import com.daca.qma.avaliacao.Avaliacao;
import com.daca.qma.avaliacao.AvaliacaoController;
import com.daca.qma.avaliacao.AvaliacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)

class AvaliacaoControllerTest {

	private MockMvc mvc;
	
	@Mock
    private AvaliacaoService service;
	
	@InjectMocks
	private AvaliacaoController controller;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private Avaliacao avaliacao;

	/**
	 * 
	 */
	@BeforeAll
	void setUpAll() {
		this.mvc = standaloneSetup(controller).build();
	}
	
	@BeforeEach
	void setUp() {

		this.avaliacao = new Avaliacao(5, "Ótimo tutor!");
	}

	/**
	 * @throws Exception 
	 */
	@Test
	void ListarAvaliacoes() throws Exception {
		
		mvc.perform(get("/avaliacoes/1"))
		.andExpect(status().isOk())
		.andExpect(content().string(new ArrayList().toString()));
	}
	
	@Test
	void SalvarAvaliacao() throws Exception {
		
		mvc.perform(post("/avaliacoes/1/")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsString(avaliacao)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(""));
	}
	
	@Test
	void EditarAvaliacao() throws Exception {
		
		mvc.perform(put("/avaliacoes/1/")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsString(avaliacao)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("false"));
	}
	
	@Test
	void DeletarAvaliacao() throws Exception {
		
		mvc.perform(delete("/avaliacoes/1/")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsString(avaliacao)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(""));
		
	}
	
	

}
