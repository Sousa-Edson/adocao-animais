package com.belval.adocaoanimais.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true)
	private String role;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private Collection<Usuario> users;

	public Role() {

	}

	public Role(long id, String role) {

		this.id = id;
		this.role = role;
	}

	public Role(String role) {
		this.role = role;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Collection<Usuario> getUsers() {
		return users;
	}

	public void setUsers(Collection<Usuario> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return role;
	}

}
