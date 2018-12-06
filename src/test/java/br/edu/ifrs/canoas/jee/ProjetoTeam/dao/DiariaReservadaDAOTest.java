package br.edu.ifrs.canoas.jee.ProjetoTeam.dao;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assert.*;
import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.jee.ProjetoTeam.model.DiariaReservada;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Quarto;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Reserva;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.TipoDeQuarto;

public class DiariaReservadaDAOTest {
	
	DiariaReservadaDAO drDAO = new DiariaReservadaDAO();
	ReservaDAO reservaDAO = new ReservaDAO();
	EnderecoDAO endDAO = new EnderecoDAO();
	QuartoDAO quartoDao = new QuartoDAO();
	Reserva reserva;
	DiariaReservada dr;
	Quarto quarto;
	Date data;
	
	@Before
	public void setUp() {
		quarto = new Quarto();
		reserva = new Reserva();
		data = new Date(30, 11, 2018);
		dr = new DiariaReservada(data, quarto, reserva);
	}
	
	@Test
	public void testaSalvarDiariaReservada() {
		assertNull(dr.getIdDiaria());

		quartoDao.salva(quarto);
		reservaDAO.salva(reserva);
		drDAO.salva(dr);

		assertThat(drDAO.buscaId(dr.getIdDiaria())).isNotNull();
	}
	
	@Test
	public void testaAtualizaDiariaReservada() {
		Quarto quarto2 = new Quarto("505",TipoDeQuarto.DUPLO);
		dr = new DiariaReservada(data,quarto2,reserva);
		
		quartoDao.salva(quarto2);
		reservaDAO.salva(reserva);
		drDAO.salva(dr);
		
		assertEquals("505",drDAO.buscaId(dr.getIdDiaria()).getQuarto().getNumero());
		
		dr.getQuarto().setNumero("303");
		quartoDao.atualiza(quarto2);
		drDAO.atualiza(dr);
		
		assertThat(drDAO.buscaId(dr.getIdDiaria()).getQuarto().getNumero()).as("303");
	}
	
	@Test
	public void testaRemocaoDiariaReservada() {
		quartoDao.salva(quarto);
		reservaDAO.salva(reserva);
		drDAO.salva(dr);
		
		assertNotNull(drDAO.buscaId(dr.getIdDiaria()));
		
		drDAO.remove(dr.getIdDiaria());
		
		assertThat(drDAO.buscaId(dr.getIdDiaria())).isNull();
	}
	
	@Test
	public void testaBuscaTodasDiariasReservadas() {
		DiariaReservada dr1 = new DiariaReservada();
		DiariaReservada dr2 = new DiariaReservada();
		DiariaReservada dr3 = new DiariaReservada();
		DiariaReservada dr4 = new DiariaReservada();
		DiariaReservada dr5 = new DiariaReservada();
		
		drDAO.salva(dr1);
		drDAO.salva(dr2);
		drDAO.salva(dr3);
		drDAO.salva(dr4);
		drDAO.salva(dr5);
		
		List<DiariaReservada> diariasReservadas = drDAO.busca();
		
		assertThat(diariasReservadas.size()).isGreaterThan(5);
	}
	
	@Test
	public void testaBuscaDiariasReservadasPorQuarto() {
		Reserva r1 = new Reserva();
		Reserva r2 = new Reserva();
		Reserva r3 = new Reserva();
		DiariaReservada dr1 = new DiariaReservada(data,quarto,r1);
		DiariaReservada dr2 = new DiariaReservada(data,quarto,r2);
		DiariaReservada dr3 = new DiariaReservada(data,quarto,r3);
		
		quartoDao.salva(quarto);
		reservaDAO.salva(r1);
		reservaDAO.salva(r2);
		reservaDAO.salva(r3);
		drDAO.salva(dr1);
		drDAO.salva(dr2);
		drDAO.salva(dr3);
		
		List<DiariaReservada> diariasReservadas = drDAO.buscaPorQuarto(quarto);
		
		assertEquals(3,diariasReservadas.size());
	}
	
	@Test
	public void testaBuscaDiariasReservadasPorId() {
		quartoDao.salva(quarto);
		reservaDAO.salva(reserva);
		drDAO.salva(dr);
		
		long id = drDAO.buscaId(dr.getIdDiaria()).getIdDiaria();
		
		assertThat(dr.getIdDiaria()).isEqualTo(id);
	}

	@Test
	public void testaBuscaDiariasReservadasPorData() {
		Date d1 = new Date(17,06,2018);
		Reserva r1 = new Reserva();
		Reserva r2 = new Reserva();
		Reserva r3 = new Reserva();
		DiariaReservada dr1 = new DiariaReservada(d1,quarto,r1);
		DiariaReservada dr2 = new DiariaReservada(d1,quarto,r2);
		DiariaReservada dr3 = new DiariaReservada(data,quarto,r3);
		
		quartoDao.salva(quarto);
		reservaDAO.salva(r1);
		reservaDAO.salva(r2);
		reservaDAO.salva(r3);
		drDAO.salva(dr1);
		drDAO.salva(dr2);
		drDAO.salva(dr3);
		
		List<DiariaReservada> diariasReservadas = drDAO.buscaPorData(d1);
		
		assertEquals(2,diariasReservadas.size());
	}
}
