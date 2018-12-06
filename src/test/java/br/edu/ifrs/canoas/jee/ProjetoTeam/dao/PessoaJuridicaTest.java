package br.edu.ifrs.canoas.jee.ProjetoTeam.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Endereco;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.PessoaJuridica;

public class PessoaJuridicaTest {

	EnderecoDAO enderecoDAO;
	PessoaJuridicaDAO pjDAO;
	PessoaJuridica pj1;
	PessoaJuridica pj2;
	PessoaJuridica pj3;
	PessoaJuridica pj4;
	PessoaJuridica pj5;
	Endereco endereco1;
	Endereco endereco2;
	Endereco endereco3;
	Endereco endereco4;
	Endereco endereco5;

	@Before
	public void setUp() {
		enderecoDAO = new EnderecoDAO();
		pjDAO = new PessoaJuridicaDAO();
		endereco1 = new Endereco();
		endereco2 = new Endereco();
		endereco3 = new Endereco();
		endereco4 = new Endereco();
		endereco5 = new Endereco();

		pj1 = new PessoaJuridica("nome", "98765-4321", "email@email.com", endereco1, "rs1", "23.736.180/0001-70", "inscricaoEstadual1",
				"inscricaoMunicipal1");
		pj2 = new PessoaJuridica("Jonas", "telefone", "email", endereco2, "razaoSocial", "52.338.878/0001-35", "inscricaoEstadual",
				"inscricaoMunicipal");
		pj3 = new PessoaJuridica("Ana", "telefone", "email", endereco3, "razaoSocial", "82.333.457/0001-82", "inscricaoEstadual",
				"inscricaoMunicipal");
		pj4 = new PessoaJuridica("Thiago", "telefone", "email", endereco4, "razaoSocial", "92.343.467/0001-92", "inscricaoEstadual",
				"inscricaoMunicipal");
		pj5 = new PessoaJuridica("Gustavo", "telefone", "email", endereco5, "razaoSocial", "62.348.469/0001-42", "inscricaoEstadual",
				"inscricaoMunicipal");
	}

	@Test
	public void testaSalvaPessoaJuridica() {
		assertNull(pj1.getIdPessoa());
		enderecoDAO.salva(endereco1);
		pjDAO.salva(pj1);
		assertNotNull(pjDAO.buscaId(pj1.getIdPessoa()));
	}

	@Test
	public void testaAtualizaPessoaJuridica() {
		enderecoDAO.salva(endereco1);
		pjDAO.salva(pj1);
		assertThat(pjDAO.buscaId(pj1.getIdPessoa()).getNome()).isEqualTo("nome");// as("Nome");
		pj1.setNome("Fulano");
		pjDAO.atualiza(pj1);
		assertThat(pjDAO.buscaId(pj1.getIdPessoa()).getNome()).isEqualTo("Fulano");
	}

	@Test
	public void testaRemocaoDePessoaJuridica() {
		enderecoDAO.salva(endereco1);
		pjDAO.salva(pj1);
		assertNotNull(pj1.getIdPessoa());
		pjDAO.remove(pj1.getIdPessoa());
		assertNull(pjDAO.buscaId(pj1.getIdPessoa()));
	}

	@Test
	public void testaBuscaTodasPessoasJuridicas() {
		enderecoDAO.salva(endereco1);
		enderecoDAO.salva(endereco2);
		enderecoDAO.salva(endereco3);
		
		pjDAO.salva(pj1);
		pjDAO.salva(pj2);
		pjDAO.salva(pj3);
		
		List<PessoaJuridica> lista = pjDAO.busca();
		
		assertThat(lista.size()).isGreaterThan(3);

	}

	@Test
	public void testaBuscaPorId() {
		enderecoDAO.salva(endereco1);
		pjDAO.salva(pj1);
		assertThat(pjDAO.buscaId(pj1.getIdPessoa())).isNotNull();
	}

	@Test
	public void testaBuscaPorNome() {
		enderecoDAO.salva(endereco5);
		pjDAO.salva(pj5);

		List<PessoaJuridica> lista = pjDAO.buscaPorNome("Gustavo");

		assertThat(lista).hasSize(1);
	}

	@Test
	public void testaBuscaPorCnpj() {
		
		enderecoDAO.salva(endereco4);
		pjDAO.salva(pj4);

		List<PessoaJuridica> lista = pjDAO.buscaPorCnpj("92.343.467/0001-92");

		assertThat(lista).hasSize(1);
	}

}
