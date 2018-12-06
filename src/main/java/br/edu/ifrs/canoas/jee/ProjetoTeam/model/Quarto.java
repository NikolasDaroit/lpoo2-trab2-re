package br.edu.ifrs.canoas.jee.ProjetoTeam.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quarto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long idQuarto;
	private String numero;
	
	@Enumerated(EnumType.STRING)
	private TipoDeQuarto tipo;
	
	public Quarto() {}
	
	public Quarto(String numero, TipoDeQuarto tipo) { 
		this.numero = numero;
		this.tipo = tipo; 
	}
	
	public Long getIdQuarto() {
		return idQuarto;
	}

	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public TipoDeQuarto getTipo() {
		return tipo;
	}
}
