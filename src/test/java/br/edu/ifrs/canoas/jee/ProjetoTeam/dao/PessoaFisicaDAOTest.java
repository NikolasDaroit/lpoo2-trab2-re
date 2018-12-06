package br.edu.ifrs.canoas.jee.ProjetoTeam.dao;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Diaria;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.PessoaFisica;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Endereco;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Sexo;

public class PessoaFisicaDAOTest {

	EnderecoDAO enderecoDAO = new EnderecoDAO();
	PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
	PessoaFisica pf1;
	PessoaFisica pf2;
	PessoaFisica pf3;
	PessoaFisica pf4;
	Endereco endereco1;
	Endereco endereco2;
	Endereco endereco3;
	Endereco endereco4;
	Date dt1;
	Date dt2;
	Date dt3;
	Date dt4;
	List<Diaria> diarias;

	@Before
	public void setUp() {
		endereco1 = new Endereco();
		endereco2 = new Endereco();
		endereco3 = new Endereco();
		endereco4 = new Endereco();
		dt1 = new Date(13, 06, 1993);
		dt2 = new Date(10, 11, 1984);
		dt3 = new Date(9, 01, 1969);
		dt4 = new Date(18,03,1999);
		diarias = new ArrayList<>();

		pf1 = new PessoaFisica("Fulano", "12345-6789", "ful@email.com", endereco1, "987.321.645-11", "111111111", dt1, Sexo.M,
				diarias);
		pf2 = new PessoaFisica("Joao", "98765-4321", "jm@email.com", endereco2, "123.456.789-10", "22222222", dt2,
				Sexo.M, diarias);
		pf3 = new PessoaFisica("Maria", "9638-5274", "jm@email.com", endereco3, "963.852.741-02", "33333333", dt3,
				Sexo.F, diarias);
		pf4 = new PessoaFisica("Lara", "9638-5274", "jm@email.com", endereco4, "045.094.050-06", "44444444", dt4,
				Sexo.F, diarias);
	}

	@Test
	public void testaSalvaPessoaFisica() {
		enderecoDAO.salva(endereco1);
		assertNull(pf1.getIdPessoa());
		pfDAO.salva(pf1);
		assertNotNull(pfDAO.buscaId(pf1.getIdPessoa()));
	}

	@Test
	public void testaAtualizaPessoaFisica() {
		enderecoDAO.salva(endereco1);
		pfDAO.salva(pf1);
		assertThat(pfDAO.buscaId(pf1.getIdPessoa()).getNome()).isEqualTo("Fulano");
		pf1.setNome("Jose");
		pfDAO.atualiza(pf1);
		assertThat(pfDAO.buscaId(pf1.getIdPessoa()).getNome()).isEqualTo("Jose");
	}

	@Test
	public void testaRemocaoDePessoaFisica() {
		PessoaFisica pessoa = new PessoaFisica();
		pfDAO.salva(pessoa);
		assertNotNull(pessoa.getIdPessoa());

		pfDAO.remove(pessoa.getIdPessoa());
		assertNull(pfDAO.buscaId(pessoa.getIdPessoa()));
	}

	@Test
	public void testaBuscaTodasPessoasFisicas() {
		enderecoDAO.salva(endereco1);
		enderecoDAO.salva(endereco2);
		enderecoDAO.salva(endereco3);
		pfDAO.salva(pf1);
		pfDAO.salva(pf2);
		pfDAO.salva(pf3);

		List<PessoaFisica> lista = pfDAO.busca();

		assertThat(lista.size()).isGreaterThan(3);

	}

	@Test
	public void testaBuscaPorId() {
		enderecoDAO.salva(endereco1);
		enderecoDAO.salva(endereco2);
		pfDAO.salva(pf1);
		pfDAO.salva(pf2);
		
		assertThat(pfDAO.buscaId(pf1.getIdPessoa())).isNotNull();
	}

	@Test
	public void testaBuscaPorNome() {
		PessoaFisicaDAO pfDAO2 = new PessoaFisicaDAO();
		enderecoDAO.salva(endereco1);
		enderecoDAO.salva(endereco4);
		pfDAO2.salva(pf1);
		pfDAO2.salva(pf4);

		List<PessoaFisica> pessoasFisicas = pfDAO2.buscaPorNome("Lara");

		assertThat(pessoasFisicas).hasSize(1);
	}

	@Test
	public void testaBuscaPorEmail() {
		enderecoDAO.salva(endereco1);
		enderecoDAO.salva(endereco2);
		enderecoDAO.salva(endereco3);
		pfDAO.salva(pf1);
		pfDAO.salva(pf2);
		pfDAO.salva(pf3);

		List<PessoaFisica> lista = pfDAO.buscaPorEmail("jm@email.com");

		assertThat(lista).hasSize(2);
	}
}
