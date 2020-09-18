package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Endereco;
import com.app.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repo;
	
	public List<Endereco> save(List<Endereco> endereco) {
		return repo.saveAll(endereco);
	}
	
	public Endereco save(Endereco endereco) {
//		if(findById(endereco) != null) {
//			return null;
//		}
		return repo.save(endereco);
	}
	
	public Endereco findById(Long id) {
		Optional<Endereco> opt = repo.findById(id);
		Endereco end = new Endereco(opt.get().getId(), opt.get().getCep(), opt.get().getLogradouro(), opt.get().getNumero(), opt.get().getComplemento(), opt.get().getBairro(), opt.get().getLocalidade(), opt.get().getUf(), opt.get().getUnidade(), opt.get().getIbge(), opt.get().getGia());
		return end;	
	}
	
	public Endereco update(Endereco endereco) {
//		if(findById(endereco) == null) {
//			return null;
//		}
		return repo.save(endereco);		
	}
	
	public void delete(Endereco endereco) {
		 repo.delete(endereco);
	}
}
