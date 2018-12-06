package br.edu.ifrs.canoas.jee.ProjetoTeam.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idDiaria")
public class DiariaAvulsa extends Diaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Pessoa pessoa;

	public DiariaAvulsa() {
	}

	public DiariaAvulsa(Date data, Quarto quarto, Pessoa pessoa) {
		super();
		this.setData(data);
		this.setQuarto(quarto);
		this.pessoa = pessoa;
	}

	public Pessoa getPessoa() { 
		return pessoa;
	}

}
