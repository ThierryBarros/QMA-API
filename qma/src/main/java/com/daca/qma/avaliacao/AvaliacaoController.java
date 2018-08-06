package com.daca.qma.avaliacao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/avaliacoes")
@PreAuthorize("hasRole('ALUNO')")
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoService avaliacaoservice;
	
	
	@PostMapping(value = "/{id}")
	public Avaliacao salvar(@Valid @RequestBody Avaliacao avaliacao,@PathVariable Long id) {
		return avaliacaoservice.salvar(avaliacao,id);
	}
	
	
	@GetMapping(value = "/{id}")
	public List<Avaliacao> listar(@PathVariable("id") Long id) {
		return  avaliacaoservice.listar(id);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Boolean> editar(@PathVariable Long id, @Valid @RequestBody Avaliacao avaliacao) {
		return ResponseEntity.ok(avaliacaoservice.editar(id, avaliacao));
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
		avaliacaoservice.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
