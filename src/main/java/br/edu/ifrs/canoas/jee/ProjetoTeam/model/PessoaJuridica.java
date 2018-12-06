package br.edu.ifrs.canoas.jee.ProjetoTeam.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="idPessoa")
public class PessoaJuridica extends Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String razaoSocial;
	private String cnpj;
	private String inscricaoEstadual;
	private String inscricaoMunicipal;
	
	public PessoaJuridica() {}
	
	public PessoaJuridica(String nome, String telefone, String email, Endereco endereco, String razaoSocial,
			String cnpj, String inscricaoEstadual, String inscricaoMunicipal) {
		super();
		this.setEmail(email);
		this.setNome(nome);
		this.setEndereco(endereco);
		this.setTelefone(telefone);
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}
	
}
