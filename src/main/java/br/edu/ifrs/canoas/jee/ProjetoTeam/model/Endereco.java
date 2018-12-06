package br.edu.ifrs.canoas.jee.ProjetoTeam.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long idEndereco;
	private String logradouro;
	private int numero;
	private String complemento;
	private String cep;
	private String bairro;
	private String uf;
	
	public Endereco() {}
	
	public Endereco(String logradouro, int numero, String complemento, 
			String cep, String bairro, String uf) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
		this.bairro = bairro;
		this.uf = uf;
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String l) {
		this.logradouro = l;
	}

	public int getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getCep() {
		return cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getUf() {
		return uf;
	}
}
