package br.edu.ifrs.canoas.jee.ProjetoTeam.dao;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Endereco;

public class EnderecoDAOTest {
	
	EnderecoDAO endDAO = new EnderecoDAO();
	Endereco end1;
	Endereco end2;
	Endereco end3;
	Endereco end4;
	
	@Before
	public void setUp() {
		end1 = new Endereco("logradouro", 01, "complemento", "01234567891", "bairro", "uf");
	}
	
	@Test
	public void testaSalvarEndereco() {
		assertNull(end1.getIdEndereco());
		endDAO.salva(end1);
		assertNotNull(endDAO.buscaId(end1.getIdEndereco()));
	}
	
	@Test
	public void testaAtualizaEndereco() {
		endDAO.salva(end1);
		assertThat(endDAO.buscaId(end1.getIdEndereco()).getBairro()).as("bairro");
		
		end1.setLogradouro("logradouro2");
		end1.setBairro("bairro02");
		endDAO.atualiza(end1);
		
		Endereco enderecoAtualizado = endDAO.buscaId(end1.getIdEndereco());
		
		assertThat(enderecoAtualizado.getBairro()).doesNotEndWith("rro");
		assertThat(enderecoAtualizado.getLogradouro()).isEqualTo("logradouro2");
	}
	
	@Test
	public void testaRemoveEndereco() {
		endDAO.salva(end1);
		assertNotNull(end1.getIdEndereco());

		endDAO.remove(end1.getIdEndereco());
		assertNull(endDAO.buscaId(end1.getIdEndereco()));
	}

	@Test
	public void testaBuscaTodosEnderecos() {
		end2 = new Endereco("logradouro2", 02, "complemento02", "12345678912", "bairro02", "rs");
		end3 = new Endereco("logradouro3", 03, "complemento03", "12345678913", "bairro03", "sp");
		end4 = new Endereco("logradouro4", 04, "complemento04", "12345678914", "bairro04", "sc");
		
		endDAO.salva(end1);
		endDAO.salva(end2);
		endDAO.salva(end3);
		endDAO.salva(end4);
		
		List<Endereco> enderecos = endDAO.busca();
		
		assertThat(enderecos.size()).isGreaterThan(4);
	}
	
	@Test
	public void testaBuscaPorId() {
		endDAO.salva(end1);

		assertThat(endDAO.buscaId(end1.getIdEndereco())).isNotNull();
	}
	
	@Test
	public void testaBuscaPorCep() {
		end2 = new Endereco("logradouro2", 02, "complemento02", "12345678912", "bairro02", "rs");
		end3 = new Endereco("logradouro3", 03, "complemento03", "12345678913", "bairro03", "sp");
		end4 = new Endereco("logradouro4", 04, "complemento04", "12345678912", "bairro04", "sc");
		
		endDAO.salva(end1);
		endDAO.salva(end2);
		endDAO.salva(end3);
		endDAO.salva(end4);
		
		List<Endereco> ListaEnderecos = endDAO.buscaPorCep("12345678912");
		
		assertThat(ListaEnderecos).hasSize(2);
	}
	
	@Test
	public void testaBuscaPorBairro() {
		end2 = new Endereco("logradouro2", 02, "complemento02", "12345678912", "b2", "rs");
		end3 = new Endereco("logradouro3", 03, "complemento03", "12345678913", "b2", "rs");
		end4 = new Endereco("logradouro4", 04, "complemento04", "12345678914", "b2", "rs");
		
		endDAO.salva(end2);
		endDAO.salva(end3);
		endDAO.salva(end4);
		
		List<Endereco> enderecos = endDAO.buscaPorBairro("b2");
		
		assertThat(enderecos).hasSize(3);
	}
	
	
}
