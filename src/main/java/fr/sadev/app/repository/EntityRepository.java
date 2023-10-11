package fr.sadev.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;


public class EntityRepository<T,ID> {
	
	protected EntityManagerFactory emf;
	private Class<T> clazz;
	
	public EntityRepository(EntityManagerFactory emf,Class<T> clazz) {
		this.emf = emf;
		this.clazz = clazz;
	}

	public Boolean create(T entity) {
		Boolean allGood = false;
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			em.persist(entity);// Création

			tx.commit();

			em.close();
			allGood = true;
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {// s'éxecute dans tout les cas (erreur ou non)
			if (em != null)
				em.close();
		}
		return allGood;
	}
	
	public void update(T entity) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.merge(entity);

		tx.commit();

		em.close();
	}

	public List<T> findAll() {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<T> tq = em.createQuery("SELECT u FROM "+this.clazz.getName()+" u", this.clazz);
			List<T> users = tq.getResultList();
			tx.commit();
			em.close();
			return users;
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {// s'éxecute dans tout les cas (erreur ou non)
			if (em != null)
				em.close();
		}
		return null;
	}

	public T findById(ID id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			T entity = em.find(clazz, id);

			tx.commit();
			em.close();

			return entity;
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {// s'éxecute dans tout les cas (erreur ou non)
			if (em != null)
				em.close();
		}
		return null;
	}
	
	public Boolean deleteById(ID id) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			T entity = em.find(clazz, id);
			em.remove(entity);

			tx.commit();
			em.close();
			return true;
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {// s'éxecute dans tout les cas (erreur ou non)
			if (em != null)
				em.close();
		}

		return false;

	}
}
