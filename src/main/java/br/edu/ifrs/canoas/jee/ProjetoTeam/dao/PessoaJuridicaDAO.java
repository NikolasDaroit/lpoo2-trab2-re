package br.edu.ifrs.canoas.jee.ProjetoTeam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrs.canoas.jee.ProjetoTeam.model.PessoaJuridica;
import br.edu.ifrs.canoas.jee.ProjetoTeam.util.EntityManagerUtil;

public class PessoaJuridicaDAO {

	private EntityManager em;

	public void salva(PessoaJuridica pj) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(pj);
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(PessoaJuridica pj) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.merge(pj);
		em.getTransaction().commit();
		em.close();
	}

	public void remove(long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		PessoaJuridica pj= em.find(PessoaJuridica.class, id);
		em.remove(pj);
		em.getTransaction().commit();
		em.close();
	}
	
	public PessoaJuridica buscaId(long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		PessoaJuridica pj = em.find(PessoaJuridica.class, id);
		em.close();
		return pj;
	}
	
	public List<PessoaJuridica> busca(){
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaJuridica> query = em.createQuery("SELECT pj FROM PessoaJuridica pj",PessoaJuridica.class);
		List<PessoaJuridica> pj = query.getResultList();
		em.close();
		return pj;
	}
	
	public List<PessoaJuridica> buscaPorNome(String nome){
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaJuridica> query = em.createQuery("SELECT pj FROM PessoaJuridica pj where pj.nome = :nome",PessoaJuridica.class);
		query.setParameter("nome", nome);
		List<PessoaJuridica> pj = query.getResultList();
		em.close();
		return pj;
	}
	
	public List<PessoaJuridica> buscaPorCnpj(String cnpj){
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaJuridica> query = em.createQuery("SELECT pj FROM PessoaJuridica pj where pj.cnpj = :cnpj",PessoaJuridica.class);
		query.setParameter("cnpj", cnpj);
		List<PessoaJuridica> pj = query.getResultList();
		em.close();
		return pj;
	}
}
