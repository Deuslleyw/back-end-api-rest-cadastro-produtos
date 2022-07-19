package com.deusley.novoProjeto.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.deusley.novoProjeto.domain.enums.Perfil;
import com.deusley.novoProjeto.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente  implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@Column(unique=true)
	private String email;
	private String cpfOucnpj;
	private Integer tipo;
	
	@JsonIgnore
	private String Senha;
	
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)  
    private List<Endereco> enderecos = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "Telefone")
    private Set<String> telefones = new HashSet<>();
	
	@ElementCollection(fetch = FetchType.EAGER)                        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();
	
    public Cliente() {
    	addPerfil(Perfil.CLIENTE);
	
}

public Cliente(Integer id, String nome, String email, String cpfOucnpj, TipoCliente tipo, String Senha) {
	super();
	this.id = id;
	this.nome = nome;
	this.email = email;
	this.cpfOucnpj = cpfOucnpj;
	this.tipo =(tipo == null) ? null : tipo.getCod();      //<<<<<<Opn>>>>>>>>
	this.Senha = Senha;
	addPerfil(Perfil.CLIENTE);
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getCpfOucnpj() {
	return cpfOucnpj;
}

public void setCpfOucnpj(String cpfOucnpj) {
	this.cpfOucnpj = cpfOucnpj;
}

public TipoCliente getTipo() {
	return TipoCliente.toEnum(tipo);
}

public void setTipo(TipoCliente tipo) {
	this.tipo = tipo.getCod();
}

public List<Endereco> getEnderecos() {
	return enderecos;
}

public void setEnderecos(List<Endereco> enderecos) {
	this.enderecos = enderecos;
}

public Set<String> getTelefones() {
	return telefones;
}

public void setTelefones(Set<String> telefones) {
	this.telefones = telefones;
}

public List<Pedido> getPedidos() {
	return pedidos;
}

public String getSenha() {
	return Senha;
}

public void setSenha(String senha) {
	Senha = senha;
}

public Set<Perfil> getPerfis(){
	return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
}

public void addPerfil(Perfil perfil) {
	perfis.add(perfil.getCod());
}


public void setPedidos(List<Pedido> pedidos) {
	this.pedidos = pedidos;
}

@Override
public int hashCode() {
	return Objects.hash(id);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Cliente other = (Cliente) obj;
	return Objects.equals(id, other.id);
}

}
