package br.edu.ifrs.canoas.jee.ProjetoTeam.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Diaria;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.DiariaAvulsa;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Endereco;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.PessoaFisica;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.PessoaJuridica;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Quarto;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Sexo;

public class DiariaAvulsaDAOTest {

	DiariaAvulsaDAO daDAO = new DiariaAvulsaDAO();
	PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
	PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO();
	EnderecoDAO endDAO = new EnderecoDAO();
	QuartoDAO quartoDao = new QuartoDAO();
	PessoaFisica pessoa;
	DiariaAvulsa da; 
	Quarto quarto;
	Date data;

	@Before
	public void setUp() {
		quarto = new Quarto();
		pessoa = new PessoaFisica();
		data = new Date(20, 04, 2018);
		da = new DiariaAvulsa(data, quarto, pessoa);
	}

	@Test
	public void testaSalvarDiariaAvulsa() {
		assertNull(da.getIdDiaria());

		quartoDao.salva(quarto);
		pfDAO.salva(pessoa);
		daDAO.salva(da);

		assertNotNull(daDAO.buscaId(da.getIdDiaria()));
	}

	@Test
	public void testaAtualizaDiariaAvulsa() {
		Endereco endereco = new Endereco();
		List<Diaria> diarias = new ArrayList<>();
		Date dataNa = new Date(20, 04, 1996);

		pessoa = new PessoaFisica("Fulano", "98765-4321", "fulano@email.com", endereco, "123.456.789-10", "111111111",
				dataNa, Sexo.M, diarias);

		endDAO.salva(endereco);
		quartoDao.salva(quarto);
		pfDAO.salva(pessoa);

		da = new DiariaAvulsa(data, quarto, pessoa);

		daDAO.salva(da);
		assertThat(da.getIdDiaria()).isEqualTo(daDAO.buscaId(da.getIdDiaria()).getIdDiaria());
		assertThat(daDAO.buscaId(da.getIdDiaria()).getPessoa().getNome()).as("Fulano");

		da.getPessoa().setNome("Ciclano");
		pfDAO.atualiza(pessoa);
		daDAO.atualiza(da);

		DiariaAvulsa daComNomeAtualizado = daDAO.buscaId(da.getIdDiaria());

		assertEquals("Ciclano", daComNomeAtualizado.getPessoa().getNome());

	}

	@Test
	public void testaRemocaoDeDiariaAvulsa() {
		quartoDao.salva(quarto);
		pfDAO.salva(pessoa);
		daDAO.salva(da);

		assertNotNull(da.getIdDiaria());

		daDAO.remove(da.getIdDiaria());

		assertNull(daDAO.buscaId(da.getIdDiaria()));
	}

	@Test
	public void testaBuscaTodasDiariasAvulsas() {
		DiariaAvulsa da1 = new DiariaAvulsa(data, quarto, pessoa);
		DiariaAvulsa da2 = new DiariaAvulsa();
		DiariaAvulsa da3 = new DiariaAvulsa();
		DiariaAvulsa da4 = new DiariaAvulsa();

		quartoDao.salva(quarto);
		pfDAO.salva(pessoa);
		daDAO.salva(da1);
		daDAO.salva(da2);
		daDAO.salva(da3);
		daDAO.salva(da4);

		List<DiariaAvulsa> diariasAvulsas = daDAO.busca();

		assertThat(diariasAvulsas.size()).isGreaterThan(4);
	}

	@Test
	public void testaBuscaPorId() {
		quartoDao.salva(quarto);
		pfDAO.salva(pessoa);
		daDAO.salva(da);

		assertThat(daDAO.buscaId(da.getIdDiaria())).isNotNull();
	}
	
	@Test
	public void testaBuscaPorPessoa() {
		PessoaJuridica pessoa2 = new PessoaJuridica(); 
		
		quartoDao.salva(quarto);
		pfDAO.salva(pessoa);
		pjDAO.salva(pessoa2);
		
		DiariaAvulsa da1 = new DiariaAvulsa(data, quarto,pessoa);
		DiariaAvulsa da2 = new DiariaAvulsa(data, quarto,pessoa);
		DiariaAvulsa da3 = new DiariaAvulsa(data,quarto,pessoa2);
		
		daDAO.salva(da1);
		daDAO.salva(da2);
		daDAO.salva(da3);
		
		List<DiariaAvulsa> diariasAvulsas = daDAO.buscaPorPessoa(pessoa);
		
		assertEquals(2,diariasAvulsas.size());
	}
	
	@Test
	public void testaBuscaPorQuarto() {
		Quarto q2 = new Quarto();
		
		quartoDao.salva(q2);
		quartoDao.salva(quarto);
		pfDAO.salva(pessoa);
		
		DiariaAvulsa da1 = new DiariaAvulsa(data, quarto,pessoa);
		DiariaAvulsa da2 = new DiariaAvulsa(data, quarto,pessoa);
		DiariaAvulsa da3 = new DiariaAvulsa(data,q2,pessoa);
		
		daDAO.salva(da1);
		daDAO.salva(da2);
		daDAO.salva(da3);
		
		List<DiariaAvulsa> diariasAvulsas = daDAO.buscaPorQuarto(q2);
		
		assertEquals(1,diariasAvulsas.size());
	}

}
