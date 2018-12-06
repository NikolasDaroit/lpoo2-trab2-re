package br.edu.ifrs.canoas.jee.ProjetoTeam.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrs.canoas.jee.ProjetoTeam.model.DiariaReservada;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Quarto;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Reserva;
import br.edu.ifrs.canoas.jee.ProjetoTeam.util.EntityManagerUtil;

public class DiariaReservadaDAO {

	private EntityManager em;

	public void salva(DiariaReservada dr) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(dr);
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(DiariaReservada dr) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.merge(dr);
		em.getTransaction().commit();
		em.close();
	}

	public void remove(long id) {  
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		DiariaReservada dr = em.find(DiariaReservada.class, id);
		em.remove(dr);
		em.getTransaction().commit();
		em.close();
	}

	public DiariaReservada buscaId(long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		DiariaReservada dr = em.find(DiariaReservada.class, id);
		em.close();
		return dr;
	}

	public List<DiariaReservada> busca() {
		em = EntityManagerUtil.getEM();
		TypedQuery<DiariaReservada> query = em.createQuery("SELECT dr FROM  DiariaReservada dr", DiariaReservada.class);
		List<DiariaReservada> diariasReservadas = query.getResultList();
		em.close();
		return diariasReservadas;
	}

	public List<DiariaReservada> buscaPorData(Date data) {
		em = EntityManagerUtil.getEM();
		TypedQuery<DiariaReservada> query = em
				.createQuery("SELECT dr FROM  DiariaReservada dr where dr.data = :data", DiariaReservada.class);
		query.setParameter("data", data);
		List<DiariaReservada> diariasReservadas = query.getResultList();
		em.close();
		return diariasReservadas;
	}

	public List<DiariaReservada> buscaPorQuarto(Quarto quarto) {
		em = EntityManagerUtil.getEM();
		TypedQuery<DiariaReservada> query = em
				.createQuery("SELECT dr FROM  DiariaReservada dr where dr.quarto = :quarto", DiariaReservada.class);
		query.setParameter("quarto", quarto);
		List<DiariaReservada> diariasReservadas = query.getResultList();
		em.close();
		return diariasReservadas;
	}
}
