package com.daca.qma.avaliacao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.daca.qma.aluno.Aluno;


public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{

	List<Avaliacao> findByAluno(Aluno aluno);
	
	
	
}