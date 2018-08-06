package com.daca.qma.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.daca.qma.aluno.Aluno;
import com.daca.qma.aluno.AlunoController;
import com.daca.qma.aluno.AlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class AlunoControllerTest {

private final String ENDPOINT = "/alunos";
	
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Mock
    private AlunoService service;
	
	@InjectMocks
	private AlunoController alunoController;
	
	private Aluno ralph;

	/**
	 * 
	 */
	@BeforeAll
	void setUpAll() {
		this.mvc = standaloneSetup(alunoController).build();
	}

	@BeforeEach
	void setUp() {
		ralph = new Aluno("242424244","ralph","babaca","2424","codigo","rayff@gmail.com","24242424");
		service.salvar(ralph);
	}
	
	/**
	 * @throws Exception 
	 */
	@Test
	void CadastrarAluno() throws Exception {
		when(alunoController.cadastrarAluno(ralph))
			.thenReturn(ralph);
		when(alunoController.listarAlunos())
		.thenReturn(new ArrayList<>());
		
		mvc.perform(post(ENDPOINT)
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsString(ralph)))
				.andExpect(status().isOk());

		
	}
	
	@Test
	void ListarAlunos() throws Exception {
		
		mvc.perform(get("/alunos"))
			.andExpect(status().isOk())
			.andExpect(content().string(new ArrayList().toString()));
		
		mvc.perform(get("/alunos/123123123/nome"))
		.andExpect(status().isOk())
		.andExpect(content().string(""));
		
	}
	
	@Test
	void RecuperarAtributoAluno() throws Exception {
		
		mvc.perform(get("/alunos/123123123/nome"))
		.andExpect(status().isOk())
		.andExpect(content().string(""));
		
	}
	
	@Test
	void RecuperarAlunoPorMatricula() throws Exception {
		
		mvc.perform(get("/alunos/123123123"))
				.andExpect(status().isOk())
				.andExpect(content().string(""));
	}
	
	


}
