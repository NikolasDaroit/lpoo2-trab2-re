package br.edu.ifrs.canoas.jee.ProjetoTeam.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Pessoa;
import br.edu.ifrs.canoas.jee.ProjetoTeam.model.Reserva;
import br.edu.ifrs.canoas.jee.ProjetoTeam.util.EntityManagerUtil;

public class ReservaDAO {

	private EntityManager em;

	public void salva(Reserva reserva) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(reserva);
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(Reserva reserva) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.merge(reserva);
		em.getTransaction().commit();
		em.close();
	}

	public void remove(long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		Reserva reserva = em.find(Reserva.class, id);
		em.remove(reserva);
		em.getTransaction().commit();
		em.close();
	}
	
	public Reserva buscaId(long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		Reserva reserva = em.find(Reserva.class, id);
		em.close();
		return reserva;
	}
	
	public List<Reserva> busca(){
		em = EntityManagerUtil.getEM();
		TypedQuery<Reserva> query = em.createQuery("SELECT re FROM Reserva re",Reserva.class);
		List<Reserva> reservas = query.getResultList();
		em.close();
		return reservas;
	}
	
	public List<Reserva> buscaPorPessoa(Pessoa pessoa){
		em = EntityManagerUtil.getEM();
		TypedQuery<Reserva> query = em.createQuery("SELECT re FROM Reserva re where re.pessoa = :pessoa",Reserva.class);
		query.setParameter("pessoa", pessoa);
		List<Reserva> reservas = query.getResultList();
		em.close();
		return reservas;
	}
	
	public List<Reserva> buscaPorData(Date data){
		em = EntityManagerUtil.getEM();
		TypedQuery<Reserva> query = em.createQuery("SELECT re FROM Reserva re where re.data = :data",Reserva.class);
		query.setParameter("data", data);
		List<Reserva> reservas = query.getResultList();
		em.close();
		return reservas;
	}
}
