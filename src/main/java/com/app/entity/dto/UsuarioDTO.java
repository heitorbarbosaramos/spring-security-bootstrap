package com.app.entity.dto;

import java.util.ArrayList;
import java.util.List;

import com.app.entity.Contato;
import com.app.entity.Endereco;
import com.app.entity.Role;
import com.app.entity.Usuario;

public class UsuarioDTO implements Comparable<UsuarioDTO>{

	private Integer id;
	private String username;
	private String nomeCompleto;
	
	private List<Role> roles = new ArrayList<>();
	private List<Endereco> enderecos = new ArrayList<>();
	private List<Contato> contatos = new ArrayList<>();
	
	public UsuarioDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.username = usuario.getUsername();
		this.nomeCompleto = usuario.getNomeCompleto();
		
		this.roles = usuario.getRoles();
		this.enderecos = usuario.getEnderecos();
		this.contatos = usuario.getContatos();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles.add(roles);
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Endereco enderecos) {
		this.enderecos.add(enderecos);
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(Contato contatos) {
		this.contatos.add(contatos);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsuarioDTO [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", nomeCompleto=");
		builder.append(nomeCompleto);
		builder.append(", roles=");
		builder.append(roles);
		builder.append(", enderecos=");
		builder.append(enderecos);
		builder.append(", contatos=");
		builder.append(contatos);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(UsuarioDTO outro) {
		return nomeCompleto.compareTo(outro.getNomeCompleto());
	}
	
	
	
	
	
	
}
