package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Role;
import com.app.repository.UsuarioRoleRepository;

@Service
public class UsuarioRoleService {

	@Autowired
	private UsuarioRoleRepository repo;
	
	public Role findById(String nomeRole) {
		return repo.findById(nomeRole).orElse(null);
	}
	
	public Role save(Role role) {
//		if(findById(role.getNomeRole()) !=null) {
//			return null;
//		}
		return repo.save(role);
	}
	
	public Role update(Role usuarioRole){
		if(findById(usuarioRole.getNomeRole()) == null) {
			return null;
		}
		return repo.save(usuarioRole);
	}
	
	public void  delete(Role usuarioRole) {
		repo.delete(usuarioRole);
	}
}
