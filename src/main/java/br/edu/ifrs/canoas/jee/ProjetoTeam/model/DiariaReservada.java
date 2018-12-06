package br.edu.ifrs.canoas.jee.ProjetoTeam.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idDiaria")
public class DiariaReservada extends Diaria implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(unique = true)
	@OneToOne
	private Reserva reserva;
	
	public DiariaReservada() {}

	public DiariaReservada(Date data, Quarto quarto, Reserva reserva) {
		super();
		this.setData(data);
		this.setQuarto(quarto); 
		this.reserva = reserva;
	}

	public Reserva getReserva() {
		return reserva;
	}
}
