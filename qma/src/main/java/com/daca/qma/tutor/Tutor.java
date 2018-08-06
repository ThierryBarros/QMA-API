package com.daca.qma.tutor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tutor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tutor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	
	@NotBlank
	@NotNull
	private String disciplina;
	

	@NotNull
	@Min(0)
	@Max(5)
	private int proficiencia;
	
	private float notaTutor;
	
	@Column
	@JsonIgnore
	private Integer cont_aval;
	
	@Column
	private float qtdDinheiro;
	
	
	
	public Tutor(@NotBlank @NotNull String disciplina, @NotNull @Min(0) @Max(5) int proficiencia) {
		super();
		this.disciplina = disciplina;
		this.proficiencia = proficiencia;
		this.qtdDinheiro = 0;
		this.notaTutor = 4;
		this.cont_aval = 1;
		this.proficiencia = 0;
	}

	public Tutor() {
		this.qtdDinheiro = 0;
		this.notaTutor = 4;
		this.cont_aval = 1;
		this.proficiencia = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public int getProficiencia() {
		return proficiencia;
	}

	public void setProficiencia(int proficiencia) {
		this.proficiencia = proficiencia;
	}

	public float getNotaTutor() {
		return notaTutor;
	}

	public void setNotaTutor(float notaTutor) {
		this.notaTutor = notaTutor;
	}

	public Integer getCont_aval() {
		return cont_aval;
	}

	public void setCont_aval(Integer cont_aval) {
		this.cont_aval = cont_aval;
	}

	public float getQtdDinheiro() {
		return qtdDinheiro;
	}

	public void setQtdDinheiro(float qtdDinheiro) {
		this.qtdDinheiro = qtdDinheiro;
	}
	
	
}
