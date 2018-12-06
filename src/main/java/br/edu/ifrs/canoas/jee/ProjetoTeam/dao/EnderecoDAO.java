package br.edu.ifrs.canoas.jee.ProjetoTeam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Endereco;
import br.edu.ifrs.canoas.jee.ProjetoTeam.util.EntityManagerUtil;

public class EnderecoDAO {

	private EntityManager em;

	public void salva(Endereco endereco) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(endereco);
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(Endereco endereco) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.merge(endereco);
		em.getTransaction().commit();
		em.close();
	}

	public void remove(long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		Endereco endereco = em.find(Endereco.class, id);
		em.remove(endereco);
		em.getTransaction().commit();
		em.close();
	}
	
	public Endereco buscaId(long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		Endereco endereco = em.find(Endereco.class, id);
		em.close();
		return endereco;
	}
	public List<Endereco> busca(){
		em = EntityManagerUtil.getEM();
		TypedQuery<Endereco> query = em.createQuery("SELECT endereco FROM Endereco endereco",Endereco.class);
		List<Endereco> enderecos = query.getResultList();
		em.close();
		return enderecos;
	}
	
	public List<Endereco> buscaPorCep(String cep){
		em = EntityManagerUtil.getEM();
		TypedQuery<Endereco> query = em.createQuery("SELECT endereco FROM Endereco endereco where endereco.cep = :cep",Endereco.class);
		query.setParameter("cep", cep);
		List<Endereco> enderecos = query.getResultList();
		em.close();
		return enderecos;
	}
	
	public List<Endereco> buscaPorBairro(String bairro){
		em = EntityManagerUtil.getEM();
		TypedQuery<Endereco> query = em.createQuery("SELECT endereco FROM Endereco endereco where endereco.bairro = :bairro",Endereco.class);
		query.setParameter("bairro", bairro);
		List<Endereco> enderecos = query.getResultList();
		return enderecos;
	}
}
