package br.edu.ifrs.canoas.jee.ProjetoTeam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Sexo;

@Entity
@PrimaryKeyJoinColumn(name="idPessoa")
public class PessoaFisica extends Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private String rg;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@ManyToMany
	private List<Diaria> diarias;
	
	public PessoaFisica() {}
	
	public PessoaFisica(String nome, String telefone, String email, Endereco endereco, String cpf, String rg,
			Date dataNascimento, Sexo sexo, List<Diaria> diarias) {
		super();
		this.setEmail(email);
		this.setNome(nome);
		this.setEndereco(endereco);
		this.setTelefone(telefone);
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.diarias = diarias;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public List<Diaria> getDiarias() {
		return diarias;
	}
}
