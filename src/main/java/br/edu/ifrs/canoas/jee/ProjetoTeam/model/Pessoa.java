package br.edu.ifrs.canoas.jee.ProjetoTeam.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
//Utilizando a técnica de subclasse
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long idPessoa;
	private String nome;
	private String telefone;
	private String email;
	
	//garantir que só pode haver um endereço para cada pessoa 
	@JoinColumn(unique=true) 
	@OneToOne
	private Endereco endereco;
	
	public Pessoa() {}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
