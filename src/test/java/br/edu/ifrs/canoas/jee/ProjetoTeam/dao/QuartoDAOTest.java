package br.edu.ifrs.canoas.jee.ProjetoTeam.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Quarto;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.TipoDeQuarto;

public class QuartoDAOTest {
	
	Quarto quarto;
	QuartoDAO quartoDAO;
	
	@Before
	public void setUp(){
		quarto = new Quarto("1",TipoDeQuarto.DUPLO);
		quartoDAO = new QuartoDAO();
	}
	@Test
	public void testaSalvaQuarto() {
		assertNull(quarto.getIdQuarto());
		quartoDAO.salva(quarto);
		assertNotNull(quartoDAO.buscaId(quarto.getIdQuarto()));
	}
	@Test
	public void testaAtualizaQuarto() {
		quartoDAO.salva(quarto);
		assertThat(quartoDAO.buscaId(quarto.getIdQuarto()).getNumero()).isEqualTo("1");		
		quarto.setNumero("6");
		quartoDAO.atualiza(quarto);
		assertThat(quartoDAO.buscaId(quarto.getIdQuarto()).getNumero()).isEqualTo("6");	
	}
	@Test
	public void testaRemocaoDeQuarto() {
		quartoDAO.salva(quarto);
		assertNotNull(quarto.getIdQuarto());
		
		quartoDAO.remove(quarto.getIdQuarto());
		assertNull(quartoDAO.buscaId(quarto.getIdQuarto()));
	}
	@Test
	public void testaBuscaTodosOsQuartos() {
		Quarto quarto2 = new Quarto("2",TipoDeQuarto.MEGA_ULTRA);
		Quarto quarto3 = new Quarto("3",TipoDeQuarto.MASTER);
		Quarto quarto4 = new Quarto("4",TipoDeQuarto.PRESIDENCIAL);
		
		quartoDAO.salva(quarto);
		quartoDAO.salva(quarto2);
		quartoDAO.salva(quarto3);
		quartoDAO.salva(quarto4);

		List<Quarto> quartos = quartoDAO.busca();

		assertEquals(4,quartos.size());
		}
	@Test
	public void testaBuscaPorId() {
		quartoDAO.salva(quarto);
		
		assertThat(quartoDAO.buscaId(quarto.getIdQuarto())).isNotNull();
	}
	@Test
	public void testaBuscaPorNumero() {
		Quarto quarto2 = new Quarto("7",TipoDeQuarto.STANDARD);
		Quarto quarto3 = new Quarto("8",TipoDeQuarto.MASTER);
		Quarto quarto4 = new Quarto("9",TipoDeQuarto.PRESIDENCIAL);
		
		quartoDAO.salva(quarto);
		quartoDAO.salva(quarto2);
		quartoDAO.salva(quarto3);
		quartoDAO.salva(quarto4);
		
		List<Quarto> quartos = quartoDAO.buscaPorNumero(quarto3.getNumero());
		
		assertEquals(1,quartos.size());
	}
	@Test
	public void testaBuscaPorTipo(){
		Quarto quarto2 = new Quarto("11",TipoDeQuarto.TRIPLO);
		Quarto quarto3 = new Quarto("12",TipoDeQuarto.MASTER);
		Quarto quarto4 = new Quarto("13",TipoDeQuarto.PRESIDENCIAL);
		
		quartoDAO.salva(quarto);
		quartoDAO.salva(quarto2);
		quartoDAO.salva(quarto3);
		quartoDAO.salva(quarto4);
		
		List<Quarto> quartos = quartoDAO.buscaPorTipo(quarto2.getTipo());
		
		assertEquals(1,quartos.size());
	}
}
