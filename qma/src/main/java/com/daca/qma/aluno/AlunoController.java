package com.daca.qma.aluno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daca.qma.tutor.TutorService;


@RestController()
@RequestMapping(value = "/alunos")

public class AlunoController {
	
	@Autowired
	private AlunoService alunoservice;
	
	@Autowired
	private TutorService tutorservice;
	
	@PostMapping()
	public Aluno cadastrarAluno(@RequestBody Aluno aluno) {
		return alunoservice.salvar(aluno);
	}
	
	@PreAuthorize("hasRole('ALUNO')")
	@GetMapping(value = "/{matricula}",
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Aluno recuperaAluno(@PathVariable("matricula") String matricula) {
		return alunoservice.recuperarAluno(matricula);
	}
	
	@PreAuthorize("hasRole('ALUNO')")
	@GetMapping()
	public List<Aluno> listarAlunos(){
		return alunoservice.listarTodos();
	}

	
	@PreAuthorize("hasRole('ALUNO')")
	@GetMapping("/{matricula}/{atributo}")
	public String getInfoAluno(@PathVariable("matricula") String matricula, @PathVariable("atributo") String atributo){
		return alunoservice.getInfoAluno(matricula,atributo);
	}
	
	@PreAuthorize("hasRole('ALUNO')")
	@PostMapping("/tutores")
	public Aluno tornarTutor(@RequestParam String matricula,   @RequestParam String disciplina,   @RequestParam int proficiencia) {
		return tutorservice.tornarTutor(matricula,disciplina,proficiencia);
	}
	
	
}

