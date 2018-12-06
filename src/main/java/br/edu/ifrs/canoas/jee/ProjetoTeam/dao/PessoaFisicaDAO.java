package br.edu.ifrs.canoas.jee.ProjetoTeam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrs.canoas.jee.ProjetoTeam.model.PessoaFisica;
import br.edu.ifrs.canoas.jee.ProjetoTeam.util.EntityManagerUtil;

public class PessoaFisicaDAO {

	private EntityManager em;

	public void salva(PessoaFisica pf) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(pf);
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(PessoaFisica pf) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.merge(pf);
		em.getTransaction().commit();
		em.close();
	}

	public void remove(long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		PessoaFisica pf = em.find(PessoaFisica.class, id);
		em.remove(pf);
		em.getTransaction().commit();
		em.close();
	}
	
	public PessoaFisica buscaId(long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		PessoaFisica pf = em.find(PessoaFisica.class, id);
		em.close();
		return pf;
	}
	
	public List<PessoaFisica> busca(){
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaFisica> query = em.createQuery("SELECT pefi FROM PessoaFisica pefi",PessoaFisica.class);
		List<PessoaFisica> pf = query.getResultList();
		em.close();
		return pf;
	}
	
	public List<PessoaFisica> buscaPorNome(String nome){
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaFisica> query = em.createQuery("SELECT pefi FROM PessoaFisica pefi where pefi.nome = :nome",PessoaFisica.class);
		query.setParameter("nome", nome);
		List<PessoaFisica> pf = query.getResultList();
		em.close();
		return pf;
	}
	
	public List<PessoaFisica> buscaPorEmail(String email){
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaFisica> query = em.createQuery("SELECT pefi FROM PessoaFisica pefi where pefi.email = :email",PessoaFisica.class);
		query.setParameter("email", email);
		List<PessoaFisica> pf = query.getResultList();
		em.close();
		return pf;
	}
}
