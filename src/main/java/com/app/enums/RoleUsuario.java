package com.app.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.entity.Role;

public enum RoleUsuario {
	
	ROLE_ADMIN(1,"Administrador"),
	ROLE_USER(2,"Usuario"),
	ROLE_AUXILIAR(3,"Auxiliar"),
	ROLE_CONVIDADO(4,"Convidado");
	
	private Integer id;
	private String descricao;
	
	RoleUsuario(int id, String descricao) {
		this.id = id;
		this.descricao =descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static List<String> listaRole(){
		List<String> list = new ArrayList<>();
		for(RoleUsuario x : RoleUsuario.values()) {
			list.add(x.descricao.trim());
		}
		return list;
	}
	
	public static Map<String, String> listRole(){
		Map<String, String> listMapRoles = new HashMap<>();
		for(RoleUsuario x : RoleUsuario.values()) {
			listMapRoles.put(x.toString(), x.descricao);
		}
		return listMapRoles;
	}

	public static List<String> descricaoRole(List<Role> role){
		List<String> listaRoles = new ArrayList<>();
		for(RoleUsuario x : RoleUsuario.values()) {
			role.forEach(l->{
				if(x.toString().equals(l.getNomeRole())) {
					listaRoles.add(x.getDescricao());
				}
			});
		}
		return listaRoles;
	}
	
	public static RoleUsuario toEnum(Integer id) {
		if(id == null) {return null;}
		for(RoleUsuario x : RoleUsuario.values()) {
			if(id == x.getId()) {
				return x;
			}
		}
		throw new IllegalArgumentException("Código Role Usuario Invalid: " + id);
	}	
}