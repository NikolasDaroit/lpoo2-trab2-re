package br.edu.ifrs.canoas.jee.ProjetoTeam.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
//Utilizando a técnica de subclasse
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Diaria implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id 
	private Long idDiaria;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@ManyToOne
	private Quarto quarto;
	
	public Diaria() {}

	public Long getIdDiaria() {
		return idDiaria;
	}

	public Date getData() {
		return data;
	}

	public Quarto getQuarto() {
		return quarto;
	}
	
	public void setData(Date data) {
		this.data = data;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}	
}
