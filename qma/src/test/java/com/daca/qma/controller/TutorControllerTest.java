package com.daca.qma.controller;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.daca.qma.tutor.TutorController;
import com.daca.qma.tutor.TutorService;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)

class TutorControllerTest {

	private MockMvc mvc;
	
	@Mock
    private TutorService service;
	
	@InjectMocks
	private TutorController tutorController;
	
	

	/**
	 * 
	 */
	@BeforeAll
	void setUpAll() {
		this.mvc = standaloneSetup(tutorController).build();
	}

	/**
	 * @throws Exception 
	 */
	@Test
	void ListarTutores() throws Exception {
		when(tutorController.listar())
			.thenReturn(new ArrayList<>());

		mvc.perform(get("/tutores"))
		.andExpect(status().isOk())
		.andExpect(content().string(new ArrayList().toString()));
	}
	
	

}
