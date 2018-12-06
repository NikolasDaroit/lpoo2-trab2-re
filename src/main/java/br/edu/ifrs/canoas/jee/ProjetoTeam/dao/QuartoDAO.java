package br.edu.ifrs.canoas.jee.ProjetoTeam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Quarto;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.TipoDeQuarto;
import br.edu.ifrs.canoas.jee.ProjetoTeam.util.EntityManagerUtil;

public class QuartoDAO {

	private EntityManager em;

	public void salva(Quarto quarto) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(quarto);
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(Quarto quarto) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.merge(quarto);
		em.getTransaction().commit();
		em.close();
	}

	public void remove(long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		Quarto quarto = em.find(Quarto.class, id);
		em.remove(quarto);
		em.getTransaction().commit();
		em.close();
	}
	
	public Quarto buscaId(long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		Quarto quarto = em.find(Quarto.class, id);
		em.close();
		return quarto;
	}
	
	public List<Quarto> busca(){
		em = EntityManagerUtil.getEM();
		TypedQuery<Quarto> query = em.createQuery("SELECT qua FROM Quarto qua",Quarto.class);
		List<Quarto> quartos = query.getResultList();
		em.close();
		return quartos;
	}
	
	public List<Quarto> buscaPorNumero(String numero){
		em = EntityManagerUtil.getEM();
		TypedQuery<Quarto>  query = em.createQuery("SELECT qua FROM Quarto qua where qua.numero = :numero",Quarto.class);
		query.setParameter("numero", numero);
		List<Quarto> quartos = query.getResultList();
		em.close();
		return quartos;
	}
	
	public List<Quarto> buscaPorTipo(TipoDeQuarto tipo){
		em = EntityManagerUtil.getEM();
		TypedQuery<Quarto>  query = em.createQuery("SELECT qua FROM Quarto qua where qua.tipo = :tipo",Quarto.class);
		query.setParameter("tipo", tipo);
		List<Quarto> quartos = query.getResultList();
		em.close();
		return quartos;
	}
}
