package com.daca.qma.aluno;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	List<Aluno> findByTutorNotNull();

	Aluno findByMatricula(String matricula);

	Aluno findByUsername(String username);

}


