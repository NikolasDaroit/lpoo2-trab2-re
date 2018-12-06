package br.edu.ifrs.canoas.jee.ProjetoTeam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrs.canoas.jee.ProjetoTeam.model.DiariaAvulsa;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Pessoa;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Quarto;
import br.edu.ifrs.canoas.jee.ProjetoTeam.util.EntityManagerUtil;

public class DiariaAvulsaDAO {

	private EntityManager em;

	public void salva(DiariaAvulsa da) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(da);
		em.getTransaction().commit(); 
		em.close();
	}

	public void atualiza(DiariaAvulsa da) { 
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.merge(da);
		em.getTransaction().commit();
		em.close();
	}
 
	public void remove(Long id) {
		em = EntityManagerUtil.getEM(); 
		em.getTransaction().begin();
		DiariaAvulsa da = em.find(DiariaAvulsa.class,id);
		em.remove(da);
		em.getTransaction().commit();
		em.close();
	}

	public DiariaAvulsa buscaId(Long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		DiariaAvulsa da = em.find(DiariaAvulsa.class,id);
		em.close();
		return da;
	}
 
	public List<DiariaAvulsa> busca() {
		em = EntityManagerUtil.getEM();
		TypedQuery<DiariaAvulsa> query = em.createQuery("SELECT da FROM  DiariaAvulsa da",DiariaAvulsa.class);
		List<DiariaAvulsa> diariasAvulsas = query.getResultList();
		em.close();
		return diariasAvulsas;
	}

	public List<DiariaAvulsa> buscaPorPessoa(Pessoa pessoa) {
		em = EntityManagerUtil.getEM(); 
		TypedQuery<DiariaAvulsa> query = em.createQuery("SELECT da FROM  DiariaAvulsa da where da.pessoa =:pessoa",DiariaAvulsa.class);
		query.setParameter("pessoa", pessoa);
		List<DiariaAvulsa> diariasAvulsas = query.getResultList();
		em.close();
		return diariasAvulsas;
	}

	public List<DiariaAvulsa> buscaPorQuarto(Quarto quarto) {
		em = EntityManagerUtil.getEM();
		TypedQuery<DiariaAvulsa> query = em.createQuery("SELECT da FROM  DiariaAvulsa da where da.quarto = :quarto",DiariaAvulsa.class);
		query.setParameter("quarto", quarto);
		List<DiariaAvulsa> diariasAvulsas = query.getResultList();
		em.close();
		return diariasAvulsas;
	}
}
