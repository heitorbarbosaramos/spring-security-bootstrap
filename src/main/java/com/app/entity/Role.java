package com.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	
	@Id
	@NotBlank(message = "Nivel usuario requerido")
	private String nomeRole;
	
	@JsonIgnore
	@ManyToMany
	private List<Usuario> usuarios = new ArrayList<>();
	
	public Role() {
		
	}
	
	public Role(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuario usuarios) {
		this.usuarios.add(usuarios);
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return nomeRole;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [nomeRole=");
		builder.append(nomeRole);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
