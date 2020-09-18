package com.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.entity.Endereco;
import com.app.service.util.BuscaPorCep;

@Controller
public class ThymeleafViewController {

	private final Logger LOG = LoggerFactory.getLogger(ThymeleafViewController.class);
	
	@GetMapping("/login")
	public String login() {
		return "admin_web/login";
	}
	
	@RequestMapping("/")
	public String viewDashboard() {
		return "admin_web/dashboard";
	}

	@RequestMapping("/about")
	public String viewAbout() {
		return "admin_web/about";
	}

	@RequestMapping("/contact")
	public String viewContact() {
		return "admin_web/contact";
	}
	
	@RequestMapping("/vazio")
	public String viewVazio() {
		return "admin_web/modeloCorpoVazio";
	}
	
	@RequestMapping("/buscaPorCep/{cep}")
	public ResponseEntity<?> buscarCEP(@PathVariable String cep){
		LOG.info("BUSCA NOVO ENDERECO: " + cep);
		Endereco endereco = BuscaPorCep.buscarCep(cep);
		if(endereco==null) {
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.ok(endereco);
	}

}
