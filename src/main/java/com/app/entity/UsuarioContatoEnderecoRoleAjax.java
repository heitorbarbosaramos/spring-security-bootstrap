package com.app.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


//Essa classe serve para receber as requisições do Ajax para cadastro de um novo usuario
//Achar uma soução para passar varios arrays por ajax
//Arquivo do ajax: usuario.js
public class UsuarioContatoEnderecoRoleAjax {
	
	private Integer id;
	@NotBlank(message = "Usuario requirido")
	private String username;
	private String password;
	@NotBlank(message = "Nome requirido")
	private String nomeCompleto;
	
	@Email(message = "Campo de Email")
	@NotBlank(message = "Email requirido")
	private String email;
	private String telefone;
	@NotBlank(message = "Celular requirido")
	private String celular;
	
	
	private String cep;
	private String logradouro;
	@NotBlank(message = "Numero endereço requirido")
	private String numero;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String unidade;
	private String ibge;
	private String gia;
	
	@NotBlank(message = "Nivel usuario requirido")
	private String nomeRole;
	
	
	
	public UsuarioContatoEnderecoRoleAjax() {
		
	}

	public UsuarioContatoEnderecoRoleAjax(String username, String password, String nomeCompleto, String email,
			String telefone, String celular, String cep, String logradouro, String numero, String complemento,
			String bairro, String localidade, String uf, String unidade, String ibge, String gia, String nomeRole) {
		super();
		this.username = username;
		this.password = password;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.telefone = telefone;
		this.celular = celular;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.unidade = unidade;
		this.ibge = ibge;
		this.gia = gia;
		this.nomeRole = nomeRole;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password.length()> 0){	
			this.password = password;
		}
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsuarioContatoEnderecoRoleAjax [username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", nomeCompleto=");
		builder.append(nomeCompleto);
		builder.append(", email=");
		builder.append(email);
		builder.append(", telefone=");
		builder.append(telefone);
		builder.append(", celular=");
		builder.append(celular);
		builder.append(", cep=");
		builder.append(cep);
		builder.append(", logradouro=");
		builder.append(logradouro);
		builder.append(", numero=");
		builder.append(numero);
		builder.append(", complemento=");
		builder.append(complemento);
		builder.append(", bairro=");
		builder.append(bairro);
		builder.append(", localidade=");
		builder.append(localidade);
		builder.append(", uf=");
		builder.append(uf);
		builder.append(", unidade=");
		builder.append(unidade);
		builder.append(", ibge=");
		builder.append(ibge);
		builder.append(", gia=");
		builder.append(gia);
		builder.append(", nomeRole=");
		builder.append(nomeRole);
		builder.append("]");
		return builder.toString();
	}

	
	
}
