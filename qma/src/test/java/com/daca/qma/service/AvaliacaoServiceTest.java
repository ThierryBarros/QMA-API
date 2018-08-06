package com.daca.qma.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.daca.qma.aluno.Aluno;
import com.daca.qma.aluno.AlunoRepository;
import com.daca.qma.avaliacao.Avaliacao;
import com.daca.qma.avaliacao.AvaliacaoService;
import com.daca.qma.tutor.Tutor;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AvaliacaoServiceTest {
	
	@TestConfiguration
    static class AvaliacaoServiceTestContextConfiguration {
  
        @Bean
        public AvaliacaoService AvaliacaoService() {
            return new AvaliacaoService();
        }
    }
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private AvaliacaoService avaliacaoservice;
    
    @Autowired
	private AlunoRepository alunorepository;
    
   
    private Aluno ralph;
    
    private Avaliacao aval;
    
    @Before
    public void Setup() {
    	
    	ralph = new Aluno("242424244","ralph","babaca","2424","codigo","rayff@gmail.com","24242424");
    	Tutor tutor = new Tutor("Humanas",5);
    	ralph.setTutor(tutor);
    	
    	aval = new Avaliacao(4, "Ótimo tutor");
    	
    }
    
    @Test
    public void whenSalvarAvaliacao() {
        
        entityManager.persist(ralph);
        entityManager.persist(aval);
        entityManager.flush();
        
        Aluno aluno = alunorepository.findByUsername(ralph.getUsername());
        
        Avaliacao avaliacao = avaliacaoservice.salvar(aval,aluno.getId());
        
        assertThat(aval.getAluno())
        .isEqualTo(avaliacao.getAluno());

    }
    
    @Test
    public void whenListarAvaliacoes() {
        
        entityManager.persist(ralph);
        entityManager.persist(aval);
        entityManager.flush();
        
        Aluno aluno = alunorepository.findByUsername(ralph.getUsername());
        
        avaliacaoservice.salvar(aval,aluno.getId());
        
        List<Avaliacao> avaliacoes = avaliacaoservice.listar(aluno.getId());
        
        assertThat(avaliacoes.size())
        .isEqualTo(1);

    }
    
    @Test
    public void whenDeletarAvaliacoes() {
        
        entityManager.persist(ralph);
        entityManager.persist(aval);
        entityManager.flush();
        
        Aluno aluno = alunorepository.findByUsername(ralph.getUsername());
        
        Avaliacao avaliacao = avaliacaoservice.salvar(aval,aluno.getId());

        avaliacaoservice.deletar(avaliacao.getId());
        
        List<Avaliacao> avaliacoes = avaliacaoservice.listar(aluno.getId());
        
        assertThat(avaliacoes.size())
        .isEqualTo(0);

    }
}
