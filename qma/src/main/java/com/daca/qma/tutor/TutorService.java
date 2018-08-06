package com.daca.qma.tutor;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daca.qma.aluno.Aluno;
import com.daca.qma.aluno.AlunoRepository;
import com.daca.qma.role.Role;
import com.daca.qma.role.RoleRepository;

@Service
public class TutorService {

	@Autowired
	private TutorRepository tutorrepository;
	
	@Autowired
	private AlunoRepository alunorepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Aluno> listar() {
		
		return alunorepository.findByTutorNotNull();
	}

	
	@Transactional
	public Aluno tornarTutor(String matricula, String disciplina, int proficiencia) {
		Tutor tutor = new Tutor();
		Aluno aluno = alunorepository.findByMatricula(matricula);
		
		tutor.setDisciplina(disciplina);
		tutor.setProficiencia(proficiencia);
		Role role  = createRoleIfNotFound("TUTOR");
		aluno.setTutor(tutor);
		aluno.addRole(role);
		
		tutorrepository.save(tutor);
		
		return aluno;
	}
	
	@Transactional
    private Role createRoleIfNotFound(String name){
  
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
	
}
