package com.daca.qma.role;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {


	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;
    
	public Role() {
		
	}

	public Role(String name) {
		super();
		this.name = name;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
