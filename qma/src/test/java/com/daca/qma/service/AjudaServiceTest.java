package com.daca.qma.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.daca.qma.ajuda.Ajuda;
import com.daca.qma.ajuda.AjudaService;
import com.daca.qma.aluno.Aluno;
import com.daca.qma.aluno.AlunoController;
import com.daca.qma.aluno.AlunoRepository;
import com.daca.qma.aluno.AlunoService;



@RunWith(SpringRunner.class)
@DataJpaTest
public class AjudaServiceTest {
	
	@TestConfiguration
    static class AjudaServiceTestContextConfiguration {
  
        @Bean
        public AjudaService ajudaService() {
            return new AjudaService();
        }
    }
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private AjudaService ajudaservice;
    
    @Autowired 
    private AlunoRepository alunorepository;
    
    private Ajuda ajuda;
    
    private Aluno ralph;
    
   
    
    @Before
    public void Setup() {
    	
    	ralph = new Aluno("242424244","ralph","babaca","2424","codigo","rayff@gmail.com","24242424");
    	
    	ajuda = new Ajuda("Matemática","UFCG");
    	
    }
    
  
    
    @Test
    public void salvarAjuda() {
        
        entityManager.persist(ralph);
        entityManager.persist(ajuda);
        entityManager.flush();
        
        Aluno aluno = alunorepository.findByUsername(ralph.getUsername());
        
        Ajuda aju = ajudaservice.salvar(ajuda,aluno.getId());
        
        assertThat(aju.getAluno())
        .isEqualTo(ajuda.getAluno());

    }
    
    @Test
    public void whenListarAvaliacoes() {
        
        entityManager.persist(ralph);
        entityManager.persist(ajuda);
        entityManager.flush();
        
        Aluno aluno = alunorepository.findByUsername(ralph.getUsername());
        
        ajudaservice.salvar(ajuda,aluno.getId());
        
        List<Ajuda> ajudas = ajudaservice.listar(aluno.getId());
        
        assertThat(ajudas.size())
        .isEqualTo(1);

    }
    
   
    
}
