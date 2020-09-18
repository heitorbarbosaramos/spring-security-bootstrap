package com.app.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.service.util.BuscaPorCep;

@Entity
public class Usuario implements UserDetails, Comparable<Usuario> {
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true)
	@NotBlank(message = "Nome de usuario requirido")
	private String username;
	@NotBlank(message = "Senha Requerido")
	private String password;
	@NotBlank(message = "Nome Completo Requerido")
	private String nomeCompleto;
	
	@ManyToMany
	@JoinTable(name = "usuariosRoles", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
			inverseJoinColumns =  @JoinColumn(name = "id_role", referencedColumnName = "nomeRole"))
	private List<Role> roles = new ArrayList<>();
	
	@OneToMany
	@JoinTable(name = "enderecoUsuarios", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "id_endereco", referencedColumnName = "id"))
	private List<Endereco> enderecos = new ArrayList<>();
	
	@OneToMany
	@JoinTable(name = "contatosUsuarios", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "id_contato", referencedColumnName = "id"))
	private List<Contato> contatos = new ArrayList<>();
	
	public Usuario() {
		
	}
	public Usuario(Integer id, String username, String password, String nomeCompleto) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nomeCompleto = nomeCompleto;
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
		this.password = new BCryptPasswordEncoder().encode(password);
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(Role roles) {
		this.roles.add(roles);
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
		
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(Endereco endereco) {
		this.enderecos.add(endereco);
	}
	
	public void setEnderecosViaCEP(Long id,String cepBuscar, String complemento, String numero) {
		this.enderecos.remove(0);
		Endereco endereco = BuscaPorCep.buscarCep(cepBuscar);
		endereco.setComplemento(complemento);
		endereco.setNumero(numero);
		endereco.setId(id);
		
		this.enderecos.add(endereco);
	}
	
	public List<Contato> getContatos() {
		return contatos;
	}
	public void setContatos(Contato contatos) {
		this.contatos.add(contatos);
	}
		
	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}
	
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", nomeCompleto=");
		builder.append(nomeCompleto);
		builder.append(", roles=");
		builder.append(roles);
		builder.append(", enderecos=");
		builder.append(enderecos);
		builder.append(", contatos=");
		builder.append(contatos);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int compareTo(Usuario outro) {
		return this.getUsername().compareTo(outro.getUsername());
	}
	
	
	
	
	
}