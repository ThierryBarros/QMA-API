package com.daca.qma.tutor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daca.qma.aluno.Aluno;

@RestController
@RequestMapping(value = "/tutores")
@PreAuthorize("hasRole('TUTOR')")
public class TutorController {
	
	@Autowired
	private TutorService tutorservice;
	
	
	@GetMapping()
	public List<Aluno> listar(){
		return tutorservice.listar();
	}
	
	
	
}
