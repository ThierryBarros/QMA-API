package com.daca.qma.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.daca.qma.aluno.Aluno;
import com.daca.qma.aluno.AlunoController;
import com.daca.qma.aluno.AlunoService;
import com.daca.qma.tutor.Tutor;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AlunoServiceTest {
	
	@TestConfiguration
    static class AlunoServiceImplTestContextConfiguration {
  
        @Bean
        public AlunoService AlunoService() {
            return new AlunoService();
        }
    }
	
	private MockMvc mvc;
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private AlunoService alunoservice;
    
    private Aluno jaun;
    private Aluno ralph;
    
    @Mock
    private AlunoService service;
	
	@InjectMocks
	private AlunoController alunoController;
    
	@BeforeAll
	void setUpAll() {
		this.mvc = standaloneSetup(alunoController).build();
	}
    
    @Before
    public void Setup() {
    	jaun = new Aluno("123123123","pilipe","jaun","123","codigo","joaodopao@gmail.com","40028922");
    	ralph = new Aluno("242424244","ralph","babaca","2424","sddsjuju","babacaman@gmail.com","24242424");
    	Tutor tutor = new Tutor("Humanas",5);
    	ralph.setTutor(tutor);
    }
    
    @Test
    public void whenSalvarAluno() {
    	when(service.salvar(ralph))
		.thenReturn(ralph);
    }
    
    @Test
    public void whenListarAlunos() {
    	when(service.listarTodos())
		.thenReturn(new ArrayList<>());
    }
    
    @Test
    public void whenEncontrarAluno() {
    	when(service.encontrarAluno("ralph"))
		.thenReturn(ralph);
    }
    
    @Test
    public void FindByUsername() {
        
        entityManager.persist(jaun);
        entityManager.flush();
     
        Aluno aluno = alunoservice.encontrarAluno(jaun.getUsername());
   
        assertThat(aluno.getNome())
        .isEqualTo(jaun.getNome());

    }
    
    @Test 
    public void FindByMatricula() {
    	entityManager.persist(jaun);
        entityManager.flush();
     
        Aluno aluno = alunoservice.recuperarAluno(jaun.getMatricula());
       System.out.println(aluno.getId());
        assertThat(aluno.getNome())
        .isEqualTo(jaun.getNome());
    }
    
    @Test
    public void ListarAlunos() {
    	entityManager.persist(jaun);
        entityManager.flush();
     
        List<Aluno> aluno = alunoservice.listarTodos();
        assertThat(aluno.size())
        .isEqualTo(1);
    }
    
    @Test
    public void DeletarAluno() {
    	entityManager.persist(jaun);
        entityManager.flush();
     
        alunoservice.deletar((long) 3);;
        List<Aluno> aluno = alunoservice.listarTodos();
        assertThat(aluno.size())
        .isEqualTo(0);
    }  
    
    
   
}
