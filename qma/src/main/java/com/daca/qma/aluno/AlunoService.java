package com.daca.qma.aluno;


import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.daca.qma.exceptions.UserNotFoundException;
import com.daca.qma.role.Role;
import com.daca.qma.role.RoleRepository;


@Service()
public class AlunoService{
	
	@Autowired
	private AlunoRepository alunorepository;
	
	@Autowired
	private RoleRepository roleRepository;

	
	
	@Transactional
	public Aluno salvar(Aluno aluno) {
		aluno.setPassword(BCrypt.hashpw(aluno.getPassword(), BCrypt.gensalt()));  
		Role role  = createRoleIfNotFound("ALUNO");
		aluno.addRole(role);
		return alunorepository.save(aluno);
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
	
	@Transactional
	public Aluno atualizar(Long id, Aluno aluno) {
		return alunorepository.save(aluno);
	}

	public Aluno recuperarAluno(String matricula) {
		Aluno aluno = alunorepository.findByMatricula(matricula);
		if( aluno!=null) return aluno;
		throw new UserNotFoundException();	
		
	}

	public List<Aluno> listarTodos() {
		return alunorepository.findAll();
	}
	
	@Transactional
	public void deletar(Long id) {
		alunorepository.deleteById(id);
	}
	
	public Aluno recuperarId(Long id) {
		return alunorepository.findById(id).get();		
	}

	public String getInfoAluno(String matricula, String atributo) {
		switch(atributo){
			case "email":
				return alunorepository.findByMatricula(matricula).getEmail();
			case "nome":
				return alunorepository.findByMatricula(matricula).getNome();
			case "codigo":
				return alunorepository.findByMatricula(matricula).getCodigo();
			case "matricula":
				return alunorepository.findByMatricula(matricula).getMatricula();
			case "username":
				return alunorepository.findByMatricula(matricula).getUsername();
			case "telefone":
				return alunorepository.findByMatricula(matricula).getTelefone();
			default:
				return "atributo não encontrado";
		}
	}
	
	public Aluno encontrarAluno(String username) {
		return alunorepository.findByUsername(username);
	}

	

	
}
