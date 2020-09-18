package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Contato;
import com.app.repository.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository repo;
	
	public Contato save(Contato contato) {
//		if(findById(contato) != null) {
//			return null;
//		}
		return repo.save(contato);
	}
	
	public Contato findById(Contato contato) {
		Optional<Contato> opt = repo.findById(contato.getId());
		Contato cont = new Contato(opt.get().getId(), opt.get().getEmail(), opt.get().getTelefone(), opt.get().getCelular());
		return cont;	
	}
	
	public Contato update(Contato contato) {
//		if(findById(contato) == null) {
//			return null;
//		}
		return repo.save(contato);		
	}
	
	public void delete(Contato contato) {
		repo.delete(contato);
	}
}
