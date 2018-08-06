package com.daca.qma.avaliacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.daca.qma.aluno.Aluno;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "avaliacoes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Avaliacao {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	
	@Min(0)
	@Max(5)
	@Column(nullable = false)
	private Integer avaliacao;
	

	private String comentario;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;
	
	
	

	public Avaliacao() {
		
	}

	public Avaliacao(@Min(0) @Max(5) Integer avaliacao, String comentario) {
		super();
		this.avaliacao = avaliacao;
		this.comentario = comentario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Integer avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	
}
