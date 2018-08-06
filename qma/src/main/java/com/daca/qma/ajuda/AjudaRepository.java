package com.daca.qma.ajuda;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daca.qma.aluno.Aluno;


public interface  AjudaRepository extends JpaRepository<Ajuda, Long>{

	List<Ajuda> findAllByAluno(Aluno aluno);


}