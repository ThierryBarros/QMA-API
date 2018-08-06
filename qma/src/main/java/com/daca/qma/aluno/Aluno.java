package com.daca.qma.aluno;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.daca.qma.role.Role;
import com.daca.qma.tutor.Tutor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
@Table(name = "Aluno")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Aluno{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	

	@Column(name = "matricula", nullable = false, unique = true, length = 9)
	@NotBlank
	@Size(min = 9, max = 9)
	private String matricula;
	
	@NotBlank
	@NotNull
	private String nome;
	
	@Column(name = "username", nullable = false, unique = true)
	@NotBlank
	@NotNull
	private String username;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "password", length=60)
	@NotBlank
	@NotNull
	private String password;
	
	
	@Column(name = "codigo")
	@NotBlank
	private String codigo;
	
	@Column(name = "email", unique=true)
	@Email(message="{register.email.invalid}")
	@NotBlank
	private String email;
	
	@Column(name="nota")
	private double nota = 5.0;
	
	private String telefone;
	
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tutor_id")
	private Tutor tutor;
	

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	 @JoinTable(name = "ROLES_ID", joinColumns = {
	            @JoinColumn(name = "ALUNO_ID") }, inverseJoinColumns = {
	            @JoinColumn(name = "ROLE_ID") })
	    private Set<Role> roles;


	



	public Aluno(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	

	public Aluno(@NotBlank @Size(min = 9, max = 9) String matricula, @NotBlank @NotNull String nome,
			@NotBlank @NotNull String username, String password, @NotBlank String codigo,
			@Email(message = "{register.email.invalid}") @NotBlank String email, String telefone) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.username = username;
		this.password = password;
		this.codigo = codigo;
		this.email = email;
		this.telefone = telefone;
	}



	public Aluno() {
	
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		if(this.getRoles()==null) {
			roles = new HashSet<Role>();
		}
		roles.add(role);
	}

}