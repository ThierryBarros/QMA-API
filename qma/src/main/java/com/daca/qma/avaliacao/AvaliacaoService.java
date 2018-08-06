package com.daca.qma.avaliacao;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daca.qma.aluno.Aluno;
import com.daca.qma.aluno.AlunoRepository;
import com.daca.qma.exceptions.EntityNotFoundException;
import com.daca.qma.tutor.Tutor;

@Service
public class AvaliacaoService {
	
	@Autowired
	private AvaliacaoRepository avaliacaorepository;
	
	@Autowired
	private AlunoRepository alunorepository;
	
	@Transactional
	public Avaliacao salvar(@Valid Avaliacao avaliacao, Long id) {
		Aluno aluno = alunorepository.findById(id).get();
		
		if(aluno==null) throw new EntityNotFoundException();
		
		Tutor tutor = aluno.getTutor();
		
		int cont = tutor.getCont_aval()+1;
		
		tutor.setNotaTutor((tutor.getNotaTutor()*tutor.getCont_aval()+avaliacao.getAvaliacao())/(cont));
		
		tutor.setCont_aval(cont);
		
		avaliacao.setAluno(aluno);
		return avaliacaorepository.save(avaliacao);
	}
	
	public List<Avaliacao> listar(Long id) {
		Aluno aluno = alunorepository.findById(id).get();
		return avaliacaorepository.findByAluno(aluno);
	}

	public Avaliacao recuperar(Long id) {
		return avaliacaorepository.findById(id).get();
	}
	
	@Transactional
	public Boolean editar(Long id, Avaliacao avaliacao) {
		if(avaliacaorepository.findById(id).isPresent()) {
			avaliacaorepository.save(avaliacao);
			return true;
		}
		return false;
	}
	@Transactional
	public void deletar(Long id) {
		this.avaliacaorepository.deleteById(id);
	}

}
