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
import com.daca.qma.tutor.TutorService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TutorServiceTest {
	
	@TestConfiguration
    static class TutorServiceTestContextConfiguration {
  
        @Bean
        public TutorService tutorService() {
            return new TutorService();
        }
    }
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private TutorService tutorservice;
    
    @Autowired 
    private AlunoRepository alunorepository;
    
    
    private Aluno jaun;
    
    @Before
    public void Setup() {
    	jaun = new Aluno("123123123","pilipe","jaun","123","codigo","joaodopao@gmail.com","40028922");
    }
    
    @Test
    public void whenTornarTutor() {
        
        entityManager.persist(jaun);
        entityManager.flush();
     
        alunorepository.save(jaun);
        
        Aluno tutor = tutorservice.tornarTutor("123123123", "Jogos", 5);
   
        assertThat(tutor.getNome())
        .isEqualTo(jaun.getNome());

    }
    
    @Test
    public void whenListarTutores() {
    	entityManager.persist(jaun);
        entityManager.flush();
        
        alunorepository.save(jaun);
        
        tutorservice.tornarTutor("123123123", "Jogos", 5);
     
        List<Aluno> tutores = tutorservice.listar();
        
        assertThat(tutores.size())
        .isEqualTo(1);
    }
    
    
    

  
    
}
