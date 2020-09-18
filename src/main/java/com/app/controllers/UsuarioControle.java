package com.app.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.Contato;
import com.app.entity.Endereco;
import com.app.entity.Role;
import com.app.entity.Usuario;
import com.app.entity.UsuarioContatoEnderecoRoleAjax;
import com.app.entity.dto.RoleDTO;
import com.app.entity.dto.UsuarioDTO;
import com.app.enums.RoleUsuario;
import com.app.service.ContatoService;
import com.app.service.EnderecoService;
import com.app.service.UsuarioService;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioControle {
	
	@Autowired
	private UsuarioService useService;
	@Autowired
	private EnderecoService enderecoService;
	@Autowired
	private ContatoService contatoService;
	
	private final Logger LOG = LoggerFactory.getLogger(UsuarioControle.class);
	
	@RequestMapping(value = "/centralUsuario", method = RequestMethod.GET)
	public String centralUsuario(ModelMap model) {		
		model.addAttribute("usuariosDTO", usuarioDto());
		model.addAttribute("listRoles", findRole());
		return "admin_web/centralUsuario";
	}
		
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> saveUsuario(@Valid UsuarioContatoEnderecoRoleAjax cadastro, BindingResult result, ModelMap model){
		LOG.info("CADASTRO NOVO USUARIO");
		
		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			LOG.info("ERRORS: " + errors.toString());
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		
		Usuario usuario = new Usuario(null, cadastro.getUsername(), new BCryptPasswordEncoder().encode(cadastro.getPassword()), cadastro.getNomeCompleto());
		Endereco endereco = new Endereco(null, cadastro.getCep(), cadastro.getLogradouro(), cadastro.getNumero(), cadastro.getComplemento(), cadastro.getBairro(), cadastro.getLocalidade(), cadastro.getUf(), cadastro.getUnidade(), cadastro.getIbge(), cadastro.getGia());
		Contato contato = new Contato(null, cadastro.getEmail(), cadastro.getTelefone(), cadastro.getCelular());
		
		String roles[] = cadastro.getNomeRole().split(",");
		
		enderecoService.save(endereco);
		contatoService.save(contato);
		useService.save(usuario);
		
		usuario.setContatos(contato);
		usuario.setEnderecos(endereco);
		useService.save(usuario);
		List<Role> listaRole = new ArrayList<>();
		for(String x : roles) {
			listaRole.add(new Role(x));			
		}
		System.out.println(listaRole.toString());
		
		usuario.setRoles(listaRole);
		useService.save(usuario);
		
		
		LOG.info(usuario.toString());
		LOG.info(cadastro.toString());
		model.addAttribute("usuariosDTO", usuarioDto());
		return ResponseEntity.ok(usuario);
	}
	
	@RequestMapping(value = "/meuUsuario", method = RequestMethod.POST)
	public ModelAndView meuUsuario(@PathParam(value = "nomeUsuario") String nomeUsuario) {
		UsuarioDTO usuario = new UsuarioDTO(useService.findByUsername(nomeUsuario));
		List<String> descricaoRoles = new ArrayList<>();
		descricaoRoles = RoleUsuario.descricaoRole(usuario.getRoles());
		List<String> listaCompletaRole = new ArrayList<>();
		listaCompletaRole = RoleUsuario.listaRole();
		
		List<RoleDTO> roleDto = new ArrayList<>();
		
		List<String> mapDesc = new ArrayList<>();
		int i;
		for(String x : listaCompletaRole) {
			i = 0;
			for(String y : descricaoRoles) {
				if(x.equals(y)) {
					mapDesc.add(y);
					roleDto.add(new RoleDTO(y, 1));
					i = 1;
				}
			}
			if(i == 0) {				
				roleDto.add(new RoleDTO(x, 0));
			}
		}
		
		ModelAndView mv = new ModelAndView("admin_web/meuUsuario");
		mv.addObject("usuarioDadosDTO", usuario);
		mv.addObject("descricaoRoles", descricaoRoles);
		mv.addObject("listaCompletaRole", listaCompletaRole);
		mv.addObject("roleDto", roleDto);
		mv.addObject("roleDtoNormal", mapDesc);
		LOG.info("PG MEU USUARIO: "+usuario.toString());
		LOG.info("ROLE MEU USUARIO: "+listaCompletaRole);
		LOG.info("ROLE DTO: "+roleDto);
		LOG.info("ROLE DTO Normal: "+mapDesc);
		return mv;
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/meuUsuario/atualizar", method = RequestMethod.POST)
	public ResponseEntity<?> atualizar(@Valid UsuarioContatoEnderecoRoleAjax cadastro, BindingResult result){
	
		LOG.info("ATUALIZAR USUARIO: "+cadastro);
		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			LOG.info("ERRORS: " + errors.toString());
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		
		Usuario usuario = useService.findById(cadastro.getId());
		usuario.setNomeCompleto(cadastro.getNomeCompleto());
		
		if(cadastro.getPassword()!= null) {
			usuario.setPassword(cadastro.getPassword());
		}
		
		List<Contato> listaContato = usuario.getContatos();
		Contato contato = null;	
		for(Contato x : listaContato) {
			x.setEmail(cadastro.getEmail());
			x.setCelular(cadastro.getCelular());
			x.setTelefone(cadastro.getTelefone());
			contato = x;
		}
		LOG.info("CONTATO UPDATE USUARIO: "+contato);
		contatoService.update(contato);
		
		List<Endereco> listaEndereco = usuario.getEnderecos();
		Endereco endereco = null;
		for(Endereco x : listaEndereco){
			x.setBairro(cadastro.getBairro());
			x.setLogradouro(cadastro.getLogradouro());
			x.setCep(cadastro.getCep());
			x.setComplemento(cadastro.getComplemento());
			x.setGia(cadastro.getGia());
			x.setIbge(cadastro.getIbge());
			x.setLocalidade(cadastro.getLocalidade());
			x.setNumero(cadastro.getNumero());
			x.setUf(cadastro.getUf());
			x.setUnidade(cadastro.getUnidade());
			endereco = x;
		}
		LOG.info("ENDERECO UPDATE USUARIO: "+endereco);
		enderecoService.update(endereco);

		LOG.info("ATUALIZAR USUARIO: "+usuario.getNomeCompleto());
		
		if(usuario == null) {return ResponseEntity.unprocessableEntity().build();}
		
		String roles[] = cadastro.getNomeRole().split(",");

		List<Role> listaRole = new ArrayList<>();
		for(String x : roles) {
			listaRole.add(new Role(x));			
		}
		
		usuario.setRoles(listaRole);
		
		useService.update(usuario);
	
		UsuarioDTO usuarioDTO = useService.fromDTO(usuario);
		
		LOG.info("USUARIO FOI ATUALIZADO");
		LOG.info(useService.fromDTO(usuario).toString());
		LOG.info(endereco.toString());
		return ResponseEntity.ok(usuarioDTO);
	}
	
	
	//============================================================================================
	
	@RequestMapping(value = "/usuarioDto", method = RequestMethod.GET)
	public List<UsuarioDTO> usuarioDto(){
		List<UsuarioDTO> listaUsuarioDto =  useService.findAll().stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
		Collections.sort(listaUsuarioDto);
		return listaUsuarioDto;
	}
	
	@RequestMapping(value = "/rolesString", method = RequestMethod.GET)
	public List<String> findRole(){
		List<String> listRoles = RoleUsuario.listaRole();
		LOG.info("LISTA ROLES USUARIO: "+listRoles);
		return listRoles;
	}

}
