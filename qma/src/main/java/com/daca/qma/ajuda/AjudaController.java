package com.daca.qma.ajuda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController()
@RequestMapping(value = "/ajudas")
@PreAuthorize("hasRole('ALUNO')")
public class AjudaController {
	
	@Autowired
	private AjudaService ajudaservice;
	
	@PostMapping("/{id}")
	public Ajuda cadastrarAjuda(@RequestBody Ajuda ajuda,@PathVariable("id") Long id) {
		return ajudaservice.salvar(ajuda,id);
	}
	
	@GetMapping("/{id}")
	public List<Ajuda> listar(@PathVariable("id") Long id) {
		return ajudaservice.listar(id);
	}
	
	@GetMapping()
	public List<Ajuda> listarTodos(){
		return ajudaservice.listarTodos();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<HttpStatus> deletar(@PathVariable Long id) {
		ajudaservice.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
