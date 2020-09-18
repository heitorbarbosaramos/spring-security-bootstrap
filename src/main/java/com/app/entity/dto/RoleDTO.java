package com.app.entity.dto;

public class RoleDTO {

	private String nomeRole;
	private Integer ativo;
	
	public RoleDTO() {
	}

	public RoleDTO(String nomeRole, Integer ativo) {
		super();
		this.nomeRole = nomeRole;
		this.ativo = ativo;
	}

	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoleDTO [nomeRole=");
		builder.append(nomeRole);
		builder.append(", ativo=");
		builder.append(ativo);
		builder.append("]");
		return builder.toString();
	}
	
	
}
