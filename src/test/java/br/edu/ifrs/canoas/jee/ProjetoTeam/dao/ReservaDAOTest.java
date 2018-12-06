package br.edu.ifrs.canoas.jee.ProjetoTeam.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Diaria;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Endereco;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Pessoa;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.PessoaFisica;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Reserva;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Sexo;

public class ReservaDAOTest {

	ReservaDAO reservaDAO = new ReservaDAO();
	Reserva reserva;
	Reserva reserva2;
	Reserva reserva3;
	Reserva reserva4;
	Reserva r;
	Endereco endereco;
	EnderecoDAO endDAO;
	List<Diaria> diarias;
	PessoaFisicaDAO pfDAO;
	Pessoa pessoa;
	Pessoa pessoa2;
	Pessoa pessoa3;
	Pessoa pessoa4;
	PessoaFisica pf;
	Date dt1;
	Date dt2;
	Date dt3;
	Date dt4;
	List<Reserva> reservas;
	
	@Before
	public void setUp() {
		diarias = new ArrayList<>();
		endDAO = new EnderecoDAO();
		pfDAO = new PessoaFisicaDAO();
		dt1 = new Date(13, 06, 1993);
		dt2 = new Date(10, 11, 1984);
		dt3 = new Date(9, 01, 1969);
		dt4 = new Date(18,03,1999);
		reserva = new Reserva(dt1,15.0, pessoa);
		reserva2 = new Reserva(dt2,18.0, pessoa2);
		reserva3 = new Reserva(dt3,12.0, pessoa3);
		reserva4 = new Reserva(dt4,25.0, pessoa4);
		reservas = new ArrayList<>();
	}
	
	@Test
	public void testaSalvaReserva() {
		assertNull(reserva.getIdReserva());
		reservaDAO.salva(reserva);
		assertNotNull(reservaDAO.buscaId(reserva.getIdReserva()));
	}
	@Test
	public void testaAtualizaReserva() {
		reservaDAO.salva(reserva);
		assertThat(reservaDAO.buscaId(reserva.getIdReserva()).getValor()).isEqualTo(15.0);
		reserva.setValor(60.0);
		reservaDAO.atualiza(reserva);
		assertThat(reservaDAO.buscaId(reserva.getIdReserva()).getValor()).isEqualTo(60.0);
	}
	@Test
	public void testaRemocaoDeReserva() {
		reservaDAO.salva(reserva);
		assertNotNull(reserva.getIdReserva());

		reservaDAO.remove(reserva.getIdReserva());
		assertNull(reservaDAO.buscaId(reserva.getIdReserva()));
	}
	@Test
	public void testaBuscaPorId() {
		reservaDAO.salva(reserva);
	
		assertThat(reservaDAO.buscaId(reserva.getIdReserva())).isNotNull();
	}
	@Test
	public void testaBuscaTodasReservas() {
		reservaDAO.salva(reserva);
		reservaDAO.salva(reserva2);
		reservaDAO.salva(reserva3);

		reservas = reservaDAO.busca();

		assertThat(reservas.size()).isGreaterThan(3);
	}
	@Test
	public void testaBuscaPorPessoa() {
		endereco = new Endereco();
		pf = new PessoaFisica("Fulano", "98765-4321", "fulano@email.com", endereco, "123.456.789-10", "111111111",
				new Date(20, 04, 1996), Sexo.M, diarias);
		endDAO.salva(endereco);
		pfDAO.salva(pf);
		
		r = new Reserva(dt1,15.0, pf);
		reservaDAO.salva(r);

		reservas = reservaDAO.buscaPorPessoa(pf);

		assertThat(reservas).hasSize(1);
	}
	@Test
	public void testaBuscaPorData() {
		reservaDAO.salva(reserva);
		reservaDAO.salva(reserva4);

		reservas = reservaDAO.buscaPorData(dt4);

		assertThat(reservas).hasSize(1);
	}
}
