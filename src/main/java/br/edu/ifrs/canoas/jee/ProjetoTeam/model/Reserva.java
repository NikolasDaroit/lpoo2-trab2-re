package br.edu.ifrs.canoas.jee.ProjetoTeam.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reserva implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long idReserva;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	private double valor; 
	
	@ManyToOne
	private Pessoa pessoa;

	public Reserva() {}
	
	public Reserva(Date data, double valor, Pessoa pessoa) {
		this.data = data;
		this.valor = valor;
		this.pessoa = pessoa;
	}

	public Long getIdReserva() {
		return idReserva;
	}

	public Date getData() {
		return data;
	}

	public double getValor() {
		return valor;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public Date setData(Date data) {
		return this.data = data;
	}
	
	public double setValor(double valor){
		return this.valor = valor;
	}
	
	public Pessoa setPessoa(Pessoa pessoa) {
		return this.pessoa = pessoa;
	}
}
