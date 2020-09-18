package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Usuario;
import com.app.entity.dto.UsuarioDTO;
import com.app.repository.UsuarioRepository;
@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	public Usuario findById(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		Usuario usuario = new Usuario(obj.get().getId(), obj.get().getUsername(), obj.get().getPassword(), obj.get().getNomeCompleto());
		usuario.setContatos(obj.get().getContatos());
		usuario.setEnderecos(obj.get().getEnderecos());
		usuario.setRoles(obj.get().getRoles());
		return usuario;
	}
	
	public Usuario save(Usuario usuario) {
//		if(findById(usuario.getId()) != null) {
//			//return null;
//		}
		return repo.save(usuario);
	}
	
	public Usuario update(Usuario usuario){
		if(findById(usuario.getId()) == null) {
			return null;
		}
		return repo.save(usuario);
	}
	
	public void  delete(Usuario usuario) {
		repo.delete(usuario);
	}
	
	public Usuario findByUsername(String username) {
		return repo.findByUsername(username);
	}
	
	public UsuarioDTO fromDTO(Usuario usuario) {
		return new UsuarioDTO(usuario);
	}
	
	public List<Usuario> findAll() {
		return repo.findAll();
	}
}
