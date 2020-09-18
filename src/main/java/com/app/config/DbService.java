package com.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entity.Contato;
import com.app.entity.Endereco;
import com.app.entity.Role;
import com.app.entity.Usuario;
import com.app.enums.RoleUsuario;
import com.app.service.ContatoService;
import com.app.service.EnderecoService;
import com.app.service.UsuarioRoleService;
import com.app.service.UsuarioService;
import com.app.service.util.BuscaPorCep;

@Service
public class DbService {

	@Autowired
	private UsuarioService userService;
	@Autowired
	private UsuarioRoleService userRoleService;
	@Autowired
	private ContatoService contatoService;
	@Autowired
	private EnderecoService enderecoService;
	
	private final Logger LOG = LoggerFactory.getLogger(DbService.class);
	
	private String senha(String senha) {
		return new BCryptPasswordEncoder().encode(senha);
	}
	
	public void dbInstatiationTest() {
		
		LOG.info("INSTANCIANDO BANCOS DE DADOS DE TESTE");
		
		Usuario usu1 = new Usuario(null, "heitor.ramos", senha("123"), "Heitor Fernando Barbosa Ramos");
		Usuario usu2 = new Usuario(null, "joao.ramos", senha("123"), "João Fernando Barbosa Ramos");
		
		userService.save(usu1);
		userService.save(usu2);
		
		Role role1 = new Role(RoleUsuario.ROLE_ADMIN.toString());
		Role role2 = new Role(RoleUsuario.ROLE_USER.toString());
		Role role3 = new Role(RoleUsuario.ROLE_CONVIDADO.toString());
		Role role4 = new Role(RoleUsuario.ROLE_AUXILIAR.toString());
		
		userRoleService.save(role1);
		userRoleService.save(role2);
		userRoleService.save(role3);
		userRoleService.save(role4);
		
		usu1.setRoles(role1);
		usu1.setRoles(role2);
		usu1.setRoles(role3);
		
		role1.setUsuarios(usu1);
		role2.setUsuarios(usu1);
		role3.setUsuarios(usu1);
		
		usu2.setRoles(role2);
		role2.setUsuarios(usu2);
		
		userService.save(usu1);
		userService.save(usu2);
		
		userRoleService.save(role1);
		userRoleService.save(role2);
		userRoleService.save(role3);
		
		Contato contato1 = new Contato(null, "heitorhfbr@gmail.com", "1198981515", "1198982211");
		Contato contato2 = new Contato(null, "joaobarbosa@gmail.com", "1128981556", "1148567910");
		
		contatoService.save(contato1);
		contatoService.save(contato2);
		
		Endereco end1 = BuscaPorCep.buscarCep("06725050"); 
		Endereco end2 = BuscaPorCep.buscarCep("02187110");
		
		end1.setNumero("295");
		end2.setNumero("2");
		
		enderecoService.save(end1);
		enderecoService.save(end2);
		
		usu1.setContatos(contato1);
		usu2.setContatos(contato2);
		
		usu1.setEnderecos(end1);
		usu2.setEnderecos(end2);
		
		userService.save(usu1);
		userService.save(usu2);
		
		LOG.info("USUARIO CADASTRADOS");
		LOG.info(usu1.toString());
		LOG.info(usu2.toString());
		LOG.info("ROLES USUARIOS CADASTRADO");
		LOG.info(role1.toString());
		LOG.info(role2.toString());
		LOG.info(role3.toString());
		LOG.info("CONTATOS CADASTRADO");
		LOG.info(contato1.toString());
		LOG.info(contato2.toString());
		LOG.info("ENDERECOS CADASTRADO");
		LOG.info(end1.toString());
		LOG.info(end2.toString());
	}
}
