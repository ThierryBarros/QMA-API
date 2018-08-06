package com.daca.qma.ajuda;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daca.qma.aluno.Aluno;
import com.daca.qma.aluno.AlunoRepository;
import com.daca.qma.exceptions.EntityNotFoundException;
@Service
public class AjudaService {

	@Autowired
	private AlunoRepository alunorepository;
	
	@Autowired
	private AjudaRepository ajudarepository;
	
	@Transactional
	public Ajuda salvar(Ajuda ajuda, Long id) {
		Aluno aluno = alunorepository.findById(id).get();
		ajuda.setAluno(aluno);
		return ajudarepository.save(ajuda);
	}

	public List<Ajuda> listarTodos() {
		
		return ajudarepository.findAll();
	}

	public List<Ajuda> listar(Long id) {
		Aluno aluno = alunorepository.findById(id).get();
		return ajudarepository.findAllByAluno(aluno);
	}
	
	@Transactional
	public void deletar(Long id) {
		ajudarepository.deleteById(id);
		
	}

	

}
